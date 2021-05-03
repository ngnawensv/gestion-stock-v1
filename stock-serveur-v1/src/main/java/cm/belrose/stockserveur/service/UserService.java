package cm.belrose.stockserveur.service;

import cm.belrose.stockserveur.model.User;
import cm.belrose.stockserveur.payload.request.LoginRequest;
import cm.belrose.stockserveur.payload.request.SignupRequest;
import cm.belrose.stockserveur.payload.response.JwtResponse;
import cm.belrose.stockserveur.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    public JwtResponse authenticateUser(LoginRequest loginRequest);

    public MessageResponse registerUser(SignupRequest signUpRequest);

}
