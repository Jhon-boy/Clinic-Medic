package com.appmedic.medic_app.test_mockito;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarRolDTO;
import com.appmedic.medic_app.aplication.service.RolService;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TrolRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Const;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @Mock
    private TrolRepository repository;

    @InjectMocks
    private RolService service;

    @Test
    void testRegisterSucces(){
        RegistrarRolDTO dto = new RegistrarRolDTO("TEST", "ROL", true);

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
        Assertions.assertEquals(Const.COD_OK, response.getCode());
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
        Assertions.assertEquals(Const.COD_OK, response.getCode());
        Assertions.assertNotNull(response.getData());

    }

}
