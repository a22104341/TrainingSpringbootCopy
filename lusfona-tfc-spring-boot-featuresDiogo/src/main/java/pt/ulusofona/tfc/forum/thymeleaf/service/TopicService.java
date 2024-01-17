package pt.ulusofona.tfc.forum.thymeleaf.service;

import pt.ulusofona.tfc.forum.thymeleaf.entity.Post;
import pt.ulusofona.tfc.forum.thymeleaf.entity.Topic;
import pt.ulusofona.tfc.forum.thymeleaf.entity.User;
import pt.ulusofona.tfc.forum.thymeleaf.exception.TopicNotFoundException;
import pt.ulusofona.tfc.forum.thymeleaf.model.PostModel;
import pt.ulusofona.tfc.forum.thymeleaf.model.TopicModel;
import pt.ulusofona.tfc.forum.thymeleaf.repository.TopicJpaRepository;
import pt.ulusofona.tfc.forum.thymeleaf.repository.UserJpaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TopicService {

    private final TopicJpaRepository topicJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public TopicService(TopicJpaRepository topicJpaRepository, UserJpaRepository userJpaRepository) {
        this.topicJpaRepository = topicJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    public Topic addTopic(TopicModel topicModel) {
        Topic topic = new Topic();
        topic.setTitle(topicModel.getTitle());

        PostModel postModel = topicModel.getPosts().get(0);
        User user = userJpaRepository.findByName(postModel.getUserName())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setName(postModel.getUserName());
                    newUser.setSex(postModel.getSex());
                    return newUser;
                });

        Post post = new Post();
        post.setText(postModel.getText());
        post.setDateCreate(LocalDateTime.now());
        post.setTopic(topic);
        post.setUser(user);

        topic.setPosts(Collections.singletonList(post));
        return topicJpaRepository.save(topic);
    }

    @Transactional
    public Topic addReply(Long id, PostModel postModel) throws TopicNotFoundException {
        Topic topic = topicJpaRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));

        User user = userJpaRepository.findByName(postModel.getUserName())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setName(postModel.getUserName());
                    newUser.setSex(postModel.getSex());
                    return newUser;
                });

        Post post = new Post();
        post.setTopic(topic);
        post.setUser(user);
        post.setDateCreate(LocalDateTime.now());
        post.setText(postModel.getText());

        topic.getPosts().add(post);
        return topicJpaRepository.save(topic);
    }

    @Transactional
    public Topic editTopic(Long id, String title) throws TopicNotFoundException {
        Topic topic = topicJpaRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
        topic.setTitle(title);
        return topicJpaRepository.save(topic);
    }

    @Transactional(readOnly = true)
    public List<Topic> getTopics() {
        List<Topic> topics = topicJpaRepository.findAll();
        topics.sort((object, other) -> {
            Post objectLastPost = object.getPosts().get(object.getPosts().size() - 1);
            Post otherLastPost = other.getPosts().get(other.getPosts().size() - 1);
            return -(objectLastPost.getDateCreate().compareTo(otherLastPost.getDateCreate()));
        });
        return topics;
    }

    @Transactional(readOnly = true)
    public Topic getTopic(Long id) throws TopicNotFoundException {
        return topicJpaRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
    }

    @Transactional
    public void deleteTopic(Long id) throws TopicNotFoundException {
        if (!topicJpaRepository.existsById(id)) {
            throw new TopicNotFoundException(id);
        }
        topicJpaRepository.deleteById(id);
    }
}
