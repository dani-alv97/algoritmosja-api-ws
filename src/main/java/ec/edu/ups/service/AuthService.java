package ec.edu.ups.service;

import ec.edu.ups.model.User;
import ec.edu.ups.repository.UserRepository;
import ec.edu.ups.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(String username, String password) throws Exception {
        logger.info("Iniciando autenticación para el usuario: {}", username);

        Optional<User> usuarioOpt = usuarioRepository.findByName(username);
        if (usuarioOpt.isPresent()) {
            User usuario = usuarioOpt.get();
            logger.debug("Usuario encontrado: {}", usuario);

            if (passwordEncoder.matches(password, usuario.getClave()) && "Y".equals(usuario.getActivo())) {
                String token = jwtUtil.generateToken(username);
                logger.info("Autenticación exitosa para el usuario: {}", username);
                return token;
            } else {
                logger.warn("Contraseña incorrecta o usuario inactivo para: {}", username);
            }
        } else {
            logger.warn("Usuario no encontrado: {}", username);
        }
        
        throw new Exception("Usuario o contraseña incorrectos");
    }
}
