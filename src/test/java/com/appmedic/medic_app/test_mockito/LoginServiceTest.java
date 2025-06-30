package com.appmedic.medic_app.test_mockito;

import com.appmedic.medic_app.aplication.ports.in.dto.LogOutDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.LoginDTO;
import com.appmedic.medic_app.aplication.security.auth.JwtGenerator;
import com.appmedic.medic_app.aplication.service.LoginService;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TusuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Const;
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
class LoginServiceTest {
    @Mock
    private TusuarioRepository usuarioRepository;

    @Mock
    private JwtGenerator jwtGenerator;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginService service;

    @Test
    void testLoginUsuario(){
        String password = "TEST_PASS";
        String username = "TEST_NICK_NAME";
        String token = "TOKEN_SSSS_DDDD_PPP";
        LoginDTO dto = new LoginDTO(username, password, "samsung s20", "RED CORP", "TEST");
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
        Assertions.assertEquals(Const.COD_OK, response.getCode());

    }

    @Test
    void logOut(){
        String token = "TOKENSDASD";
        LogOutDTO dtoDatos = new LogOutDTO("ADMIN", token, "samsung s20", "WIFI NAME", "EXT");
       Response response =  service.logOut(dtoDatos);

       Assertions.assertTrue(LoginService.isBlackList(token));
       Assertions.assertNotNull(response.getData());
       Assertions.assertEquals("TOKEN INVALIDADO", response.getData());
    }

}
