package be.yapock.restaurant.pl.config.security;

import be.yapock.restaurant.dal.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
public class JWTProvider {
    private static final String JWT_SECRET = "82pK.W335:4WNYH,D64gzg9F*(i#vdNx;%DD+uU^]gw}h5H~539g";
    private static final long EXPIRE_AT = 900_000;
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";
    private final UserDetailsService userDetailsService;

    public JWTProvider(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String login){
        return TOKEN_PREFIX + JWT.create()
                .withSubject(login)
                .withExpiresAt(Instant.now().plusMillis(EXPIRE_AT))
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }

    public String extractToken(HttpServletRequest request){
        String header = request.getHeader(AUTH_HEADER);

        if (header==null || !header.startsWith(TOKEN_PREFIX)) return null;

        return header.replaceFirst(TOKEN_PREFIX,"");
    }

    public boolean validateToken(String token){
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .acceptExpiresAt(EXPIRE_AT)
                    .withClaimPresence("sub")
                    .build().verify(token);

            String login = jwt.getSubject();
            User user = (User) userDetailsService.loadUserByUsername(login);
            return user.isEnabled();
        }catch (JWTVerificationException | UsernameNotFoundException exception){
            return false;
        }
    }

    public Authentication createAuthentication(String token){
        DecodedJWT jwt = JWT.decode(token);

        String login = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}
