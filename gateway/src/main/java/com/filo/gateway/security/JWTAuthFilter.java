package com.filo.gateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class JWTAuthFilter implements GatewayFilter {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.contains("/users/signin") || path.startsWith("/users/register")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange.getRequest());

        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String userId = validateTokenAndGetUserId(token);

        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("user-id", userId)
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    private String extractToken(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public String validateTokenAndGetUserId(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        try {
            String userId = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return userId;
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException(exception.getMessage());
        }
    }




}
