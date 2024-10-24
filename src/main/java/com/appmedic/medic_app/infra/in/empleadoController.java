package com.appmedic.medic_app.infra.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.mediapp.url_endpoint}/empleado")
public class empleadoController {
}
