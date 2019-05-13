package com.hrovina.onlinestore.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
            if (auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req, resp);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.validToken(token)){
            String userName = jwtUtil.getUSerName(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return null;
    }


}
