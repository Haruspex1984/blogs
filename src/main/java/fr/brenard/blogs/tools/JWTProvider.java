package fr.brenard.blogs.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTProvider {

    private final UserDetailsService userDetailsService;


    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(Authentication auth) {
        return JWT.create()
                .withSubject(auth.getName())
                .withExpiresAt(Instant.now().plusMillis(600000))
                .sign(Algorithm.HMAC256(Constants.JWT_SECRET_KEY));
    }

    public String extractToken(HttpServletRequest request) {
        String authHeader = extractHeaderFromRequest(request);
        if (headerIsValid(authHeader)) {
            return formatHeader(authHeader);
        } else {
            return null;
        }
    }

    private String extractHeaderFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean headerIsValid(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    private String formatHeader(String header) {
        //Remove "Bearer" from the header
        return header.substring(7);
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(Constants.JWT_SECRET_KEY))
                    .acceptExpiresAt(600000)
                    .build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public Authentication createAuth(String token){
        String username = extractUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
    }

    public String extractUsernameFromToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }


}
