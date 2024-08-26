package com.iridium.security.filter;

import com.iridium.security.service.JwtService;
import com.iridium.security.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String HEADER_NAME = "Authorization";

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected final void doFilterInternal(@NonNull final HttpServletRequest request,
                                          @NonNull final HttpServletResponse response,
                                          @NonNull final FilterChain filterChain) throws ServletException, IOException {
        final var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final var jwt = authHeader.substring(BEARER_PREFIX.length());
        final var username = jwtService.extractUsername(jwt);

        if (StringUtils.isNotEmpty(username) && null == SecurityContextHolder.getContext().getAuthentication()) {
            final var userDetails = userService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                final var context = SecurityContextHolder.createEmptyContext();
                final var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
