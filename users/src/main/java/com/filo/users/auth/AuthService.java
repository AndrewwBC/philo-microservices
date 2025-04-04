package com.filo.users.auth;

import com.filo.users.dtos.SigninDTO;
import com.filo.users.model.UserModel;
import com.filo.users.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authManager , TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }
    public Map<String, String> signIn(SigninDTO signInDTO) {
        var usernamePass = new UsernamePasswordAuthenticationToken(signInDTO.email(), signInDTO.password());
        System.out.println(usernamePass);
        var auth = this.authManager.authenticate(usernamePass);
        System.out.println(auth);
        String token = this.tokenService.generateToken((UserModel) auth.getPrincipal());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}
