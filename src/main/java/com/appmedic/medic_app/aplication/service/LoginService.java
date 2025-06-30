package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.LogOutDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.LoginDTO;
import com.appmedic.medic_app.aplication.ports.out.dto.LoginResponseDTO;
import com.appmedic.medic_app.aplication.security.auth.JwtGenerator;
import com.appmedic.medic_app.aplication.service.mappers.UsuarioMappers;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TusuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.domain.enums.TokenVerify;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service()
@Transactional(readOnly=true)
public class LoginService implements UserDetailsService {
    private static final Loggers logs = new Loggers();
    private final TusuarioRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TusuarioRepository usuarioRepository;
    private final JwtGenerator jwtGenerator;
    private static Set<String> tokenBlackList = ConcurrentHashMap.newKeySet();


    public LoginService(TusuarioRepository repository,
                        @Lazy AuthenticationManager authenticationManager, JwtGenerator jwtGenerator,
                        TusuarioRepository usuarioRepository){
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.usuarioRepository = usuarioRepository;
         logs.setLogger(LoginService.class);
    }

    /**
     * Funcion que se encarga de buscar un usuario para
     * generar el Token
     * */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TSEGUSUARIO usuario = repository.findByUsuario(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuario no existe"));
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));

        return new User(usuario.getUsuario(), usuario.getClave(), roles);
    }


    @Transactional(rollbackFor = Exception.class)
    public Response logeoUsuario(LoginDTO dto){
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response =  Utils.generateBadResponseDefault();
        try{
            Optional<TSEGUSUARIO> usuario = usuarioRepository.findByUsuario(dto.user());
            if(usuario.isPresent()){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.user(),
                                dto.password()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
                LoginResponseDTO dtoResponse;
                dtoResponse = UsuarioMappers.toEntityToLogiinDto(usuario.get());
                dtoResponse.setToken(token);
                response =  Utils.generateOKResponse(dtoResponse);

            } else {
                response.setMessage("USUARIO NO REGISTRADO");
            }

        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(e.getMessage());
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }

    public Response logOut(LogOutDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        blackListToken(dto.token());
        logs.info(Const.ML_FIN + "Login Out App");
        return  Utils.generateOKResponse(TokenVerify.LOG_OUT.getMessage());
    }
    /**
     * Metodo que permite invalidar un token
     * */
    private void blackListToken(String token) {
        tokenBlackList.add(token);
        logs.info(Const.ML_FIN + "TOKEN INVALIDADO :" + token);
    }

    /**
     * Metodo que verifica si un token ya fue invalidado
     * */
    public static boolean isBlackList(String token){
        logs.info(Const.ML_FIN + "RESULTADO VALIDACION TOKEN:" + tokenBlackList.contains(token));
        return tokenBlackList.contains(token);
    }
}
