package pt.ulusofona.tfc.forum.thymeleaf.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotFoundException(Long id) {
        super(String.format("Post with id %d not found", id));
    }
	
}
