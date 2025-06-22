package com.appmedic.medic_app.test_junit;

import ch.qos.logback.core.testUtil.DummyEncoder;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase que TEST de la clase UTILS
 * */
public class UtilTest {


    @Test
     void generateOkResponseTest(){

        Response response =  Utils.generateOKResponse(new Object());
        Assert.assertEquals(_CONST.COD_OK, response.getCode());
        Assert.assertEquals(_CONST.MENSAJE_OK, response.getMessage());
        Assert.assertEquals(Object.class, response.getData().getClass());

    }

    @Test
    void generateBadResponseTest() {
        Response response = Utils.generateBadResponseDefault();
        Assertions.assertEquals(_CONST.COD_ERROR, response.getCode());
    }

    @Test
    void getClientIpTest(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        when((request.getHeader("X-Forwarded-For"))).thenReturn("192.168.17.23");

        String ip = Utils.getClientIP(request);
        Assertions.assertEquals("192.168.17.23", ip);
    }


    @Test
    void toJsonTest(){
        Response response = Utils.generateOKResponse(null);
        String obj = Utils.toJson(response);
        Assertions.assertEquals(String.class, obj.getClass());
    }

    @Test
    void getAllErros(){
        BindingResult bindingResult = mock(BindingResult.class);
        ObjectError error1 = new ObjectError("key1", "Key 1 is required");
        ObjectError error2 = new ObjectError("key2", "Key 2 is required");

        List<ObjectError> errorList = Arrays.asList(error1, error2);
        when(bindingResult.getAllErrors()).thenReturn(errorList);

        Response response = Utils.getAllErros(bindingResult, DummyClass.class);

        Assertions.assertEquals(_CONST.COD_ERROR, response.getCode());
        Assertions.assertEquals("Verifique que los campos esten completos", response.getMessage());

        List<String> expectErrors = Arrays.asList("Key 1 is required", "Key 2 is required");
        Assertions.assertEquals(expectErrors, response.getData());

    }

    @Test
    void getDateNowTest(){
//        long before = System.currentTimeMillis();
//        Date result = Utils.getDateNow();
//        long after = System.currentTimeMillis();
//
//        Assertions.assertTrue(result.getTime() >= before && result.getTime() <= after,
//                "Returned date is not within expected time range");

    }

    private static class DummyClass {}
}
