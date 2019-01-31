package k8specs.api.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "supersecret@!";

    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse res, String username) {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

        try {

            res.setContentType("application/json");

            PrintWriter out = res.getWriter();

            out.println("{");
            out.println("\"jwt\":" + "\"" + JWT + "\"");
            out.println("}");

            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;

        }

        return null;

    }

}
