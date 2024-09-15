package com.toki.socialmedia.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtValidator extends OncePerRequestFilter {

    //Check token
    //1. get token from header
    //2. tạo UsernamePasswordAuthenticationToken(email, null, authorities)  => lấy function từ jwtProvider (provider lấy từ utils)
    //3. save zô Security context
    private final Environment env;

    @Autowired
    public JwtValidator(Environment env) {
        this.env = env;
    }
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if ("/auth/signup".equals(request.getRequestURI())) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        String jwt = request.getHeader(env.getProperty("JWT_HEADER"));
        if(jwt != null){
            try{
                String email = jwtProvider.getEmailFromToken(jwt);
                List<GrantedAuthority> authorities=new ArrayList<>();
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Log the invalid token and continue the filter chain without setting the authentication
                logger.warn("Invalid Token: " + e.getMessage());
            }
        }
//        else{
//            throw new BadCredentialsException("Token not found");
//        }
        filterChain.doFilter(request, response);
    }
}