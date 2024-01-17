package pt.ulusofona.tfc.forum.thymeleaf.repository;

import pt.ulusofona.tfc.forum.thymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    List<User> findAllByOrderByPostsDesc();
}
