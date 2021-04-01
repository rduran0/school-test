package mx.dev.blank.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.exceptions.ResourceNotFound;

@ControllerAdvice(basePackages =  {"mx.dev.blank.web.controller"})
@Slf4j
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFound.class)
	public final ResponseEntity<String> handleError(final ResourceNotFound ex, final HttpServletRequest request){
		log.warn("NotFoundException: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Error");
	}
}
