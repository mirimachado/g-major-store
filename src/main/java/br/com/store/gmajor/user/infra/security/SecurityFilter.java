package br.com.store.gmajor.user.infra.security;

import br.com.store.gmajor.user.domain.User;
import br.com.store.gmajor.user.infra.services.TokenService;
import br.com.store.gmajor.user.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            User user = userRepository.findByEmail(login).orElseThrow(()-> new RuntimeException("User Not Found"));
            var authoritiesUser = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authoritiesAdmin = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));

            var authenticationAdmin = new UsernamePasswordAuthenticationToken(user, null, authoritiesAdmin);
            var authenticationUser = new UsernamePasswordAuthenticationToken(user, null, authoritiesUser);

            SecurityContextHolder.getContext().setAuthentication(authenticationAdmin);
            SecurityContextHolder.getContext().setAuthentication(authenticationUser);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        } return authHeader.replace("Bearer ", "");
    }

}
