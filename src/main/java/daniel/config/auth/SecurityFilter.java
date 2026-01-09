package daniel.config.auth;

import daniel.entities.UserEntity;
import daniel.repositories.UserRepository;
import daniel.services.TokenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService service;
    private final UserRepository repository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        var token =  this.recoverToken(request);
        var login = service.validateToken(token);

        if (login != null) {
            UserEntity user = repository.findByName(login).orElseThrow(
                    () -> new EntityNotFoundException("User Not Found")
            );
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) return authHeader.replace("Bearer ", "");
        return null;
    }
}
