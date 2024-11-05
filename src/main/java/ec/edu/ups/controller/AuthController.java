package ec.edu.ups.controller;

import ec.edu.ups.model.User;
import ec.edu.ups.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
    	logger.info("Iniciando proceso de login para el usuario: {}", loginRequest.getName());
        logger.debug("Datos del usuario recibido en el request: {}", loginRequest);

        try {
            String token = authService.authenticate(loginRequest.getName(), loginRequest.getClave());
            logger.info("Autenticación exitosa para el usuario: {}", loginRequest.getName());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
        	logger.error("Error en la autenticación: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrectos");
        }
    }
}
