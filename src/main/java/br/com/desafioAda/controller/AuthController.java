package br.com.desafioAda.controller;

import br.com.desafioAda.dto.response.JwtResponse;

import br.com.desafioAda.dto.response.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest, loginRequest) {
        JwtResponse jwtResponse = authService.autenticarUsuario(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/validar-token")
    public ResponseEntity<Boolean> validarToken(@RequestHeader("Authorization") String token) {
        boolean isValid = authService.validarToken(token);
        return ResponseEntity.ok(isValid);
    }
}