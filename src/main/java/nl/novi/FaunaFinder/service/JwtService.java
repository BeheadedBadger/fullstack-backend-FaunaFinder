package nl.novi.FaunaFinder.service;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.novi.FaunaFinder.models.User;
import nl.novi.FaunaFinder.repositories.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nl.novi.FaunaFinder.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import static nl.novi.FaunaFinder.utils.RandomStringGenerator.getGeneratedString;

@Service
public class JwtService {

    private final static String SECRET_KEY = getGeneratedString();

    private long refreshTokenExpire = 1000 * 60 * 60 * 24 * 7; // 7 days;

    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean validToken = tokenRepository
                .findByAccessToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    }

    public boolean isValidRefreshToken(String token, User user) {
        String username = extractUsername(token);

        boolean validRefreshToken = tokenRepository
                .findByRefreshToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validRefreshToken;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token.replace("{", "").replace("}",""))
                .getBody();
    }

    public String generateAccessToken(User user) {
        long accessTokenExpire = 1000 * 60 * 60 * 24; // 1 day in ms;
        System.out.println(accessTokenExpire);
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, user.getUsername(), accessTokenExpire);
    }

    public String generateRefreshToken(User user) {
        System.out.println(refreshTokenExpire);
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, user.getUsername(), refreshTokenExpire);
    }

    /*private String generateToken(User user, long expireTime) {
        String token = Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime ))
                .signWith(getSigningKey())
                .compact();

        return token;
    }*/

    private String generateToken(Map<String, Object> claims, String subject, Long expireTime) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000))
                .signWith(SignatureAlgorithm.HS512,
                        SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void setRefreshTokenExpire(long refreshTokenExpire) {
        this.refreshTokenExpire = refreshTokenExpire;
    }
}