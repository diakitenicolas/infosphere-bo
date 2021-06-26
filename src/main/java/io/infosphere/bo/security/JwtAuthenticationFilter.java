package io.infosphere.bo.security;

import com.auth0.jwt.JWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static io.infosphere.bo.security.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        User principal = (User) auth.getPrincipal();
        List<String> authorities = principal.getAuthorities().stream()
                .map(authority -> authority.getAuthority()).collect(Collectors.toList());
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("rol", authorities)
                .withIssuer(TOKEN_ISSUER)
                .withHeader(TOKEN_HEADER_PARAMS)
                .withAudience(TOKEN_AUDIENCE)
                .withExpiresAt(new Date(System.currentTimeMillis() + ONE_MONTH_MILLISECONDS))
                .sign(HMAC512(JWT_SECRET.getBytes()));
        res.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token);
    }
}