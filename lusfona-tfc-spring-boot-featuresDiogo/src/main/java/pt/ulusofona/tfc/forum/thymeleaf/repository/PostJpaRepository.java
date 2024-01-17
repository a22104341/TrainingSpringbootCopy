package pt.ulusofona.tfc.forum.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ulusofona.tfc.forum.thymeleaf.entity.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
