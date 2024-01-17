package pt.ulusofona.tfc.forum.thymeleaf.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostModel implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Long id;
	
	@NotEmpty(message = "Text is mandatory.")
    @Size(min=1, max=256, message = "Text needs to be between 1 and 256 chars.")
    private String text;

    private Long topicId;

    @NotEmpty(message = "Username is mandatory.")
    @Size(min=1, max=32, message = "Username needs to be between 1 and 32 chars.")
    private String userName;



    /*Diogo Column Addition */
    @NotEmpty(message = "Username is mandatory.")
    private String sex;
    /*Diogo Column Addition Till here*/

    private String dateCreate;

}
