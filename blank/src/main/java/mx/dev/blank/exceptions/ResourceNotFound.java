package mx.dev.blank.exceptions;

public class ResourceNotFound extends RuntimeException{

	 private static final long serialVersionUID = 1L;

	  public ResourceNotFound(final String message) {
	    super(message);
	  }

	  public ResourceNotFound(final String message, final Throwable t) {
	    super(message, t);
	  }
}
