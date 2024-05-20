package com.example.sbnzproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";

    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;

    public AuthFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(AUTH_HEADER);

        if (token != null) {
            String username = tokenUtils.getUsername(token);

            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (tokenUtils.validateToken(userDetails, token)) {
                    Token authentication = new Token(userDetails);
                    authentication.setToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}