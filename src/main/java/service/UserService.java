package service;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(String username, String password) {
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setHash(hashedPassword);
        return userRepository.save(newUser);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, String username, String password) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(username);
                    user.setHash(bCryptPasswordEncoder.encode(password));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean validateUserCredentials(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> bCryptPasswordEncoder.matches(password, user.getHash()))
                .orElse(false);
    }

    // Additional service methods
}
