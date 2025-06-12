package com.appmedic.medic_app.test_mockito;

import com.appmedic.medic_app.aplication.ports.in.dto.logOutDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.loginDTO;
import com.appmedic.medic_app.aplication.security.auth.jwtGenerator;
import com.appmedic.medic_app.aplication.service.loginService;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util._CONST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class loginServiceTest {
    @Mock
    private tUsuarioRepository usuarioRepository;

    @Mock
    private jwtGenerator jwtGenerator;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private loginService service;

    @Test
    void testLoginUsuario(){
        String password = "TEST_PASS";
        String username = "TEST_NICK_NAME";
        String token = "TOKEN_SSSS_DDDD_PPP";
        Date fecha = new Date();
        loginDTO dto = new loginDTO(username, password, "samsung s20", "RED CORP", "TEST");
        TSEGUSUARIO mockUsuario = new TSEGUSUARIO();
        mockUsuario.setActivo(true);
        mockUsuario.setUsuario(username);
        mockUsuario.setSesion(false);
        mockUsuario.setId(1);
        mockUsuario.setClave(password);
        mockUsuario.setRol(new TSEGROL(1, "ADMIN", "USER ADMIN", true));
        mockUsuario.setPersona(new TPERPERSONA(1, "JOHN", "DOE", new Date(), new Date(), new Date(), "test@gmail.com", "097222222" , "02322"));

        when(usuarioRepository.findByUsuario(username)).thenReturn(Optional.of(mockUsuario));

        Authentication mockAuth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(mockAuth);
        when(jwtGenerator.generateToken(mockAuth)).thenReturn(token);

        Response response = service.logeoUsuario(dto);
        Assertions.assertEquals(_CONST.COD_OK, response.getCode());

    }

    @Test
    void logOut(){
        String token = "TOKENSDASD";
        logOutDTO dtoDatos = new logOutDTO("ADMIN", token, "samsung s20", "WIFI NAME", "EXT");
       Response response =  service.logOut(dtoDatos);

       Assertions.assertTrue(loginService.isBlackList(token));
       Assertions.assertNotNull(response.getData());
       Assertions.assertEquals("TOKEN INVALIDADO", response.getData());
    }

}
