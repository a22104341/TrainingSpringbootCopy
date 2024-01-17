package pt.ulusofona.tfc.forum.thymeleaf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TopicModel implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;

    @NotEmpty(message = "Title is mandatory.")
    @Size(min=1, max=64, message = "Title needs to be between 1 and 64 chars.")
    private String title;

    @NotEmpty(message = "Must have at least one post.")
    @Valid
    private List<PostModel> posts = new ArrayList<>();


}
