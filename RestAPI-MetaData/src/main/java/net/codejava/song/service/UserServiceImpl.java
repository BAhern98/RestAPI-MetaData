package net.codejava.song.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.codejava.song.model.User;
import net.codejava.song.model.UserDto;
import net.codejava.song.repo.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final VerificationCodeGenerator codeGenerator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, VerificationCodeGenerator codeGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User(passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());
        return userRepository.save(user);
    }
  

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean verifyUser(String email, String password) {
        User user = findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean verifyUser(String email, String password, String code) {
        User user = findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user.getVerificationCode().equals(code);
        }
        return false;
    }

    @Override
    public void sendVerificationCode(String email) {
        User user = findByEmail(email);
        if (user != null) {
            String code = codeGenerator.generateCode();
            user.setVerificationCode(code);
            userRepository.save(user);
            emailService.sendVerificationCode(user.getEmail(), code);
        }
    }
    
//    @Override
//    public String generateAccessToken(String email) {
//        String secretKey = "mySecretKey"; // Change this to your actual secret key
//        Date expiryDate = new Date(System.currentTimeMillis() + 3600000); // Set the token to expire after an hour
//        
//        String token = Jwts.builder()
//            .setSubject(email)
//            .setExpiration(expiryDate)
//            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
//            .compact();
//
//        return token;
////    }
//    public String generateAccessToken(String email) {
//        String secretKey = "mySecretKey";
//        String issuer = "myIssuer";
//        String subject = "accessToken";
//        long currentTimeMillis = System.currentTimeMillis();
//        long expirationInMillis = 86400000L; // one day
//        Date issuedAt = new Date(currentTimeMillis);
//        Date expiresAt = new Date(currentTimeMillis + expirationInMillis);
//        String token = Jwts.builder()
//                .setIssuer(issuer)
//                .setSubject(subject)
//                .setIssuedAt(issuedAt)
//                .setExpiration(expiresAt)
//                .claim("email", email)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//        return token;
//    }
//    @Override
//    public String generateAccessToken(String email) {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // generate a secure key for HMAC-SHA256
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + 3600000); // token will expire after 1 hour
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("email", email);
//
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .addClaims(claims)
//                .signWith(key)
//                .compact();
//    }
    
    @Override
    public String generateAccessToken() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // generate a secure key for HMAC-SHA256
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // token will expire after 1 hour

        return Jwts.builder()
                .setIssuer("myApplication") // set the issuer claim to identify the application that generated the token
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }






    
}
