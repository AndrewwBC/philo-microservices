package com.filo.users.auth;

import com.filo.users.dtos.SigninDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> authSignin(@RequestBody @Valid SigninDTO signinDTO) {
        System.out.println(signinDTO);
        return ResponseEntity.ok(authService.signIn(signinDTO));
    }

}
