package pt.ulusofona.tfc.forum.thymeleaf.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pt.ulusofona.tfc.forum.thymeleaf.entity.Post;
import pt.ulusofona.tfc.forum.thymeleaf.entity.Topic;
import pt.ulusofona.tfc.forum.thymeleaf.model.PostModel;
import pt.ulusofona.tfc.forum.thymeleaf.model.TopicModel;


@Component
public class Mapper {

	
    public PostModel postEntityToModel(Post entity) {

        PostModel model = new PostModel();

        model.setId(entity.getId());
        model.setDateCreate(entity.getDateCreate().toString());
        model.setText(entity.getText());
        model.setTopicId(entity.getTopic().getId());
        model.setUserName(entity.getUser().getName());

        /*Diogo Column Addition */
        model.setSex(entity.getUser().getSex());
        /*Diogo Column Addition Till here*/




        return model;

    }

    
    public List<PostModel> postEntityListToModelList(List<Post> entities) {
		return entities.stream()
				.map(this::postEntityToModel)
				.collect(Collectors.toList());
    }

    public TopicModel topicEntityToModel(Topic entity) {

        TopicModel model = new TopicModel();

        model.setId(entity.getId());
        model.setTitle(entity.getTitle());

        model.setPosts(postEntityListToModelList(entity.getPosts()));

        return model;

    }

    public List<TopicModel> topicEntityListToModelList(List<Topic> entities) {
    	return entities.stream()
    			.map(this::topicEntityToModel)
    			.collect(Collectors.toList());
    }

    
}
