package cm.belrose.stockserveur.controller;

import cm.belrose.stockserveur.Security.jwt.JwtUtils;
import cm.belrose.stockserveur.payload.request.LoginRequest;
import cm.belrose.stockserveur.payload.request.SignupRequest;
import cm.belrose.stockserveur.payload.response.JwtResponse;
import cm.belrose.stockserveur.payload.response.MessageResponse;
import cm.belrose.stockserveur.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       JwtResponse jwtResponse= userService.authenticateUser(loginRequest);
       return ResponseEntity.ok(jwtResponse);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        MessageResponse messageResponse=userService.registerUser(signUpRequest);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtils.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
