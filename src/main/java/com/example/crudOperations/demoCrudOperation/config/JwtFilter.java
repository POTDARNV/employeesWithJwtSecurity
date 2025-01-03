package com.example.crudOperations.demoCrudOperation.config;

import com.example.crudOperations.demoCrudOperation.service.CustomUserDetailsService;
import com.example.crudOperations.demoCrudOperation.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
String header=request.getHeader("Authorization");
String  token=null;
String email=null;

if (header!=null&&header.startsWith("Bearer "))  {
      token=header.substring(7);
      email=jwtService.extractUsername(token);
        }
if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
    UserDetails userDetails=context.getBean(CustomUserDetailsService.class).loadUserByUsername(email);
if (jwtService.validateToken(token,userDetails)){
    UsernamePasswordAuthenticationToken token1=
            new UsernamePasswordAuthenticationToken
                    (userDetails,null,userDetails.getAuthorities());
    token1.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(token1);
}
}
filterChain.doFilter(request,response);
    }
}
