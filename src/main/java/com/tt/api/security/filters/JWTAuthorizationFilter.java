package com.tt.api.security.filters;

import com.tt.api.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
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

    private final UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager
            , UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            // parse the token.
            String username = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (username != null) {
                UserDetails userByUsername = userDetailsService.loadUserByUsername(username);
                return userByUsername == null
                        ? null
                        : new UsernamePasswordAuthenticationToken(userByUsername.getUsername()
                        , null
                        , userByUsername.getAuthorities());
            }
        }
        return null;
    }
}
