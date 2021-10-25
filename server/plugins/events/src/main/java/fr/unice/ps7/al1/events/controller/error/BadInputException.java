package fr.unice.ps7.al1.events.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad input")
public class BadInputException extends RuntimeException {
	public BadInputException(){

	}

	public BadInputException(String errMsg){
		super(errMsg);
	}
}
