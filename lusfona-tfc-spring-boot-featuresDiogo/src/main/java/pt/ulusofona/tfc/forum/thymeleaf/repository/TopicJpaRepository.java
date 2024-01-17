package pt.ulusofona.tfc.forum.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ulusofona.tfc.forum.thymeleaf.entity.Topic;

@Repository
public interface TopicJpaRepository extends JpaRepository<Topic, Long> {

}
