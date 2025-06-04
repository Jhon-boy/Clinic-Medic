package com.appmedic.medic_app.test_junit;

import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase que prueba cada metodo unitario de la clase Util
 * */
public class unitTest {

    @Test
   public void testGenerateResponse(){
        Response response = Utils.generateResponse("200", "Success", "Something");
        assertEquals("200", response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("Something", response.getData());
    }

}
