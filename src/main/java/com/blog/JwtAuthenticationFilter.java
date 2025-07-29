package com.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    // @Override
    // protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, 
    //                               @NonNull FilterChain filterChain) throws ServletException, IOException {
        
    //     final String authHeader = request.getHeader("Authorization");
    //     String token = null;
    //     String username = null;
        
    //     if (authHeader != null && authHeader.startsWith("Bearer ")) {
    //         token = authHeader.substring(7);
    //         try {
    //             username = jwtUtil.getUsernameFromToken(token);
    //         } catch (Exception e) {
    //             logger.debug("Invalid JWT token: " + e.getMessage());
    //         }
    //     }
        
    //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //         try {
    //             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
    //             if (token != null && jwtUtil.validateToken(token)) {
    //                 UsernamePasswordAuthenticationToken authToken = 
    //                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    //                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //                 SecurityContextHolder.getContext().setAuthentication(authToken);
    //             }
    //         } catch (Exception e) {
    //             logger.debug("Authentication failed: " + e.getMessage());
    //         }
    //     }
        
    //     filterChain.doFilter(request, response);
    // }


    @Override
protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull FilterChain filterChain) throws ServletException, IOException {

    String path = request.getRequestURI();

    // 跳过 Swagger 相关路径和你的公开接口
    if (path.startsWith("/swagger-ui")
            || path.equals("/swagger-ui.html")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/swagger-resources")
            || path.startsWith("/webjars")
            || path.startsWith("/api/auth")) {
        filterChain.doFilter(request, response);
        return;
    }

    final String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        try {
            username = jwtUtil.getUsernameFromToken(token);
        } catch (Exception e) {
            logger.debug("Invalid JWT token: " + e.getMessage());
        }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (token != null && jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            logger.debug("Authentication failed: " + e.getMessage());
        }
    }

    filterChain.doFilter(request, response);
}

}


