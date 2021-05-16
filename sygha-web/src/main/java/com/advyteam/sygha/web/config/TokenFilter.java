package com.advyteam.sygha.web.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class TokenFilter extends GenericFilterBean{

    private final static Logger LOGGER = Logger.getLogger(TokenFilter.class.getName());

    private TokenProvider jwtTokenProvider;

    public TokenFilter(TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletResponse response = (HttpServletResponse) res;
        try{
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (ExpiredJwtException exception){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.expired");
            LOGGER.warning(exception.toString());
        } catch (JwtException exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.invalid");
            LOGGER.warning(exception.toString());
            return;
        }
        filterChain.doFilter(req, res);
    }

}
