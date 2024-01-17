package pt.ulusofona.tfc.forum.thymeleaf.service;

import org.springframework.stereotype.Service;
import pt.ulusofona.tfc.forum.thymeleaf.entity.User;
import pt.ulusofona.tfc.forum.thymeleaf.repository.UserJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public List<User> getAllUsers() {
        return userJpaRepository.findAllByOrderByPostsDesc();
    }


    public Optional<User> getUserById(Long id) {
        return userJpaRepository.findById(id);
    }

    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
    }

    // Any other service methods as needed
}
