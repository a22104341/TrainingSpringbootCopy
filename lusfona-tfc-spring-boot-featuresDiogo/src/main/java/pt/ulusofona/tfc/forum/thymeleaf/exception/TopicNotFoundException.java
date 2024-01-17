package pt.ulusofona.tfc.forum.thymeleaf.exception;

public class TopicNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TopicNotFoundException(Long id) {
        super(String.format("Topic with id %d not found", id));
    }
	
}
