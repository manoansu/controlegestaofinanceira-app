package pt.amane.controlegestaofinanceiraapp.services.exceptions;

public class DataBaseIntegrityViolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataBaseIntegrityViolationException(String message) {
		super(message);
	}
	
	

}
