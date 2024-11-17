package bits.project.bookexchangeplatform.service;

import bits.project.bookexchangeplatform.dto.UserToken;
import bits.project.bookexchangeplatform.helpers.JwtUtil;
import bits.project.bookexchangeplatform.database.UserRepository;
import bits.project.bookexchangeplatform.dto.LoginDto;
import bits.project.bookexchangeplatform.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.time.Duration;
import java.time.LocalDateTime;

import bits.project.bookexchangeplatform.dto.UserDto;

@Service
public class AuthService {
    private static final long EXPIRE_TOKEN=30;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Transactional
    public void register(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + userDto.getEmail());
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public String login(LoginDto loginDto) throws Exception{
        Long userId = isValidUser(loginDto);
        if (userId != 0) {
            // Generate JWT
            String token = jwtUtil.generateToken(loginDto.getEmail());
            //return UserToken.builder().userId(userId).token(token).build();
            return token;
        }else {
            return "UserToken.builder().build()";
        }
    }

    private Long isValidUser(LoginDto loginDto) throws Exception{
        boolean isValidUser = true;
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new Exception("User not found with email: " + loginDto.getEmail()));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            isValidUser = false;
        }
        return user.getId();
    }

    public String logout(String token) {
        String jwt = token.substring(7);  // Remove "Bearer " prefix
        tokenBlacklistService.blacklistToken(jwt);
        return "Logged out successfully";
    }

    public String requestPasswordReset(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        User user=userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user=userRepository.save(user);
        return user.getToken();
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    public String resetPassword(String token, String password){
        Optional<User> userOptional= userRepository.findByToken(token);

        if(!userOptional.isPresent()){
            return "Invalid token";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);
        return "Your password successfully updated.";
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >=EXPIRE_TOKEN;
    }

    public String getUserDetails(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }
        User user=userOptional.get();
        return user.toString();
    }

}
