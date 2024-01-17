package pt.ulusofona.tfc.forum.thymeleaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pt.ulusofona.tfc.forum.thymeleaf.entity.Topic;
import pt.ulusofona.tfc.forum.thymeleaf.exception.UnexpectedFieldErrorException;
import pt.ulusofona.tfc.forum.thymeleaf.mapper.Mapper;
import pt.ulusofona.tfc.forum.thymeleaf.model.PostModel;
import pt.ulusofona.tfc.forum.thymeleaf.model.TopicModel;
import pt.ulusofona.tfc.forum.thymeleaf.service.TopicService;


@Controller
@RequestMapping("/topics")
public class TopicController {

	
    private final TopicService topicService;
    private final Mapper mapper;

    
    public TopicController(TopicService topicService, Mapper mapper) {
        this.topicService = topicService;
        this.mapper = mapper;
    }
    

    @GetMapping("/all")
    public String all(Model model) {

    	
        /*
         * Service Invocation
         */

        List<Topic> topics = topicService.getTopics();

        
        /*
         * Mapper & Result
         */

        List<TopicModel> topicModels = mapper.topicEntityListToModelList(topics);

        model.addAttribute("topics", topicModels);

        
        return "topics";
        
    }    

    @GetMapping("/add-form")
    public String addForm(Model model) {
    	
    	model.addAttribute("topic", new TopicModel());
    	
    	return "add-topic";
    	
    }

    @PostMapping("/add")
    public String addTopic(@Valid TopicModel topicModel, BindingResult bindingResult, Model model) {
    	
    	
    	// check errors
    	
    	if(bindingResult.hasErrors()) {
    		// unsuccess
    		throw new UnexpectedFieldErrorException(bindingResult.getAllErrors());
    	}
    	
    	
    	/*
    	 * Service invocation
    	 */
    	topicService.addTopic(topicModel);
    	

        
        /*
         * Mapper & Result
         */
    	
    	List<Topic> topics = topicService.getTopics();
    	
        List<TopicModel> topicModels = mapper.topicEntityListToModelList(topics);

        model.addAttribute("topics", topicModels);

    	
    	return "topics";
    	
    }

    
    @GetMapping("/reply-form/{id}")
    public String replyForm(@PathVariable(value = "id") Long id, Model model) {
    	
    	
    	/*
    	 * Service invocation
    	 */
    	
    	Topic topic = topicService.getTopic(id);
    	
    	
    	/*
    	 * Mapper & Result
    	 */
    	
    	model.addAttribute("topic", mapper.topicEntityToModel(topic));

    	
    	return "add-reply";
    	
    }

    
    @PostMapping("/reply/{id}")
    public String reply(@PathVariable(value = "id") Long id, @Valid PostModel postModel, BindingResult bindingResult, Model model) {
    	
    	
    	// check errors
    	
    	if(bindingResult.hasErrors()) {
    		// unsuccess
    		throw new UnexpectedFieldErrorException(bindingResult.getAllErrors());
    	}
    	
    	
    	/*
    	 * Service invocation
    	 */
    	
    	Topic topic = topicService.addReply(id, postModel);
    	
    	
    	/*
    	 * Mapper & Result
    	 */
    	
    	model.addAttribute("topic", mapper.topicEntityToModel(topic));
    	
    	
    	return "topic";
    	
    }


    @GetMapping("/{id}")
    public String topic(@PathVariable(value = "id") Long id, Model model) {
    	
    	
    	/*
    	 * Service Invocation
    	 */
    	
    	Topic topic = topicService.getTopic(id);
    	
    	
    	/*
    	 * Mapper & Result
    	 */
    	
    	model.addAttribute("topic", mapper.topicEntityToModel(topic));

    	
    	return "topic";
    	
    }

    
    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable(value = "id") Long id, Model model) {
    	
    	
    	/*
    	 * Service Invocation
    	 */
    	
    	Topic topic = topicService.getTopic(id);
    	
    	
    	/*
    	 * Mapper & Result
    	 */
    	
    	model.addAttribute("topic", mapper.topicEntityToModel(topic));

    	
    	return "edit-topic";
    	
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, String title, Model model) {
    	
    	
    	/*
    	 * Service invocation
    	 */
    	
    	Topic topic = topicService.editTopic(id, title);
    	
    	
    	/*
    	 * Mapper & Result
    	 */
    	
    	model.addAttribute("topic", mapper.topicEntityToModel(topic));

    	
    	return "topic";
    	
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, Model model) {
    	
    	
    	/*
    	 * Service invocation
    	 */
    	
    	topicService.deleteTopic(id);
    	
    	
        /*
         * Service Invocation
         */

        List<Topic> topics = topicService.getTopics();

        
        /*
         * Mapper & Result
         */

        List<TopicModel> topicModels = mapper.topicEntityListToModelList(topics);

        model.addAttribute("topics", topicModels);
    	
    	
    	return "topics";
    	
    }
    
}
