package com.appmedic.medic_app.test_mockito;

import com.appmedic.medic_app.aplication.service.UsuarioService;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TrolRepository;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TusuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Const;
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
class UsuarioServiceTest {
    @Mock
    private TusuarioRepository userRepository;

    @Mock
    private TrolRepository rolRepository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void testGetUser() {
        TSEGUSUARIO usuarioMock = new TSEGUSUARIO();
        when(userRepository.findByUsuario("userTest")).thenReturn(Optional.of(usuarioMock));

        Response response = service.obtenerUsuarioByUser("userTest");
        assertEquals("Usuario Existe", response.getData());
        assertEquals(Const.COD_OK, response.getCode());

    }

}
