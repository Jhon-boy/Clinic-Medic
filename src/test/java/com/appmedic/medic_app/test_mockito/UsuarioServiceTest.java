package com.appmedic.medic_app.test_mockito;

import com.appmedic.medic_app.aplication.service.usuarioService;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tRolRepository;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util._CONST;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
/*
 *TEST a la CLASE service
 * JB
 * */
public class UsuarioServiceTest {
    @Mock
    private tUsuarioRepository userRepository;

    @Mock
    private tRolRepository rolRepository;

    @InjectMocks
    private usuarioService service;

    @Test
    public void testGetUser() throws Exception {
        TSEGUSUARIO usuarioMock = new TSEGUSUARIO();
        when(userRepository.findByUsuario("userTest")).thenReturn(Optional.of(usuarioMock));

        Response response = service.obtenerUsuarioByUser("userTest");
        assertEquals("Usuario Existe", response.getData());
        assertEquals(_CONST.COD_OK, response.getCode());

    }

    @Test
    void testLogin(){

    }
}
