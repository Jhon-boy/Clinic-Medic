package com.appmedic.medic_app.test_mockito;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarRolDTO;
import com.appmedic.medic_app.aplication.service.rolService;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tRolRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util._CONST;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.bcel.Const;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class rolServiceTest {

    @Mock
    private  tRolRepository repository;

    @InjectMocks
    private rolService service;

    @Test
    void testRegisterSucces(){
        registrarRolDTO dto = new registrarRolDTO("TEST", "ROL", true);

        TSEGROL rolMock = new TSEGROL();
        rolMock.setNombre("TEST");
        rolMock.setDescripcion("ROL");
        rolMock.setActivo(true);
        TSEGROL entitySaved = new TSEGROL();
        rolMock.setId(1);
        entitySaved.setNombre("TEST");
        entitySaved.setDescripcion("ROL");
        entitySaved.setActivo(true);

        when(repository.save(argThat(rol ->
                rol.getNombre().equals("TEST") &&
                        rol.getDescripcion().equals("ROL") &&
                        rol.isActivo()
        ))).thenReturn(entitySaved);

        Response response = service.registrarRol(dto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(_CONST.COD_OK, response.getCode());
    }

    @Test
    void testFindById() {
        final int ID_MOCK  = 1;
        TSEGROL rolMock = new TSEGROL();
        rolMock.setId(ID_MOCK);
        rolMock.setNombre("TEST");
        rolMock.setDescripcion("DESCRIPCION");
        rolMock.setActivo(true);
        when(repository.findById(ID_MOCK)).thenReturn(Optional.of(rolMock));

        Response response = service.findById(ID_MOCK);
        Assertions.assertEquals(_CONST.COD_OK, response.getCode());
        Assertions.assertNotNull(response.getData());

    }

}
