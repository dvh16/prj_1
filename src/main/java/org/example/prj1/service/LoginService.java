package org.example.prj1.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.example.prj1.dto.request.LoginRequest;
import org.example.prj1.entity.User;
import org.example.prj1.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserRepository userRepository;
    @NonFinal
    private static final String SIGNER_KEY = "Kuezs6rQaEq0mSjSoRE6NCXKUEE+OAzdbF9p1q7oxpVGqdz3neJXlgt48+v2+Pwm\\n";


    @Autowired
    PasswordEncoder passwordEncoder;
    
    public ResponseEntity<?> login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String rawPassword = loginRequest.getPassword();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        User userfromDB = optionalUser.get();
         String encodedPassword = userfromDB.getPassword();

        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
            String token = generateToken(userfromDB);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("token", token);
            return  ResponseEntity.ok(response);

        }



    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("prj1.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                        )
                )
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token");
            throw new RuntimeException(e);
        }
    }
    private String buildScope(User user)
    {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
        {
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}




