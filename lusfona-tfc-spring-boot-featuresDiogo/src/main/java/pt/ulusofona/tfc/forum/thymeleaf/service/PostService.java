package pt.ulusofona.tfc.forum.thymeleaf.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.ulusofona.tfc.forum.thymeleaf.entity.Post;
import pt.ulusofona.tfc.forum.thymeleaf.exception.PostNotFoundException;
import pt.ulusofona.tfc.forum.thymeleaf.exception.TopicNotFoundException;
import pt.ulusofona.tfc.forum.thymeleaf.repository.PostJpaRepository;


@Service
public class PostService {


    private final PostJpaRepository postJpaRepository;


    public PostService(PostJpaRepository postJpaRepository) {
        this.postJpaRepository = postJpaRepository;
    }


    @Transactional(readOnly = true)
    public Post getPost(Long id) throws PostNotFoundException {
        return postJpaRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public Post edit(Long id, String text) throws TopicNotFoundException {

        // avoid null pointer exception by throwing service specific exception.
        Post post = postJpaRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));

        post.setText(text);
        

        return postJpaRepository.save(post);

    }
    
    
}
