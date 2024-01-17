package pt.ulusofona.tfc.forum.thymeleaf.exception;

import java.util.List;

import org.springframework.validation.ObjectError;

public class UnexpectedFieldErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<ObjectError> errors;
	
	public UnexpectedFieldErrorException(List<ObjectError> errors) {
		super();
		this.errors = errors;
	}
	
	public List<ObjectError> getErrors() {
		return errors;
	}
	
	public boolean hasErrors() {
		return this.errors != null && !errors.isEmpty();
	}

}
