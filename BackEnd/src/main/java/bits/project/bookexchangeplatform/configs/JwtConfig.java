package bits.project.bookexchangeplatform.configs;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public SecretKey jwtSecretKey() {
        // Generates a 512-bit key for HS512
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
