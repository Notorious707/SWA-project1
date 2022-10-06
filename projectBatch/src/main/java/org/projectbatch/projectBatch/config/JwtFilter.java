package org.projectbatch.projectBatch.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.projectbatch.projectBatch.dto.ResponseDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtHelper jwtHelper;
    private final MyUserDetailsService myUserDetailsService;
    private final ResponseDto responseDto;

    public byte[] convertObjectToBytes(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object).getBytes();
    }

    public void errorInject(HttpServletResponse response, String message, int code) throws IOException {
        responseDto.setCode(code);
        responseDto.setMessage(message);
        responseDto.setData(null);
        byte[] responseToSend = convertObjectToBytes(responseDto);
        ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
        ((HttpServletResponse) response).setStatus(code);
        response.getOutputStream().write(responseToSend);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication() == null) {
            token = authorizationHeader.substring(7);
            try {
                username = jwtHelper.getUsernameFromToken(token);
                var userDetails = myUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                return;
            } catch (ExpiredJwtException e) {
                errorInject(response, "Provided token is expired!", 401);
                return;
            } catch (Exception e) {
                errorInject(response, e.getMessage(), 401);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
