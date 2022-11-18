package br.atelieartessimoes.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amazonaws.services.chimesdkmessaging.model.NotFoundException;

import br.atelieartessimoes.dtos.ApiErrorDTO;

@ControllerAdvice
public class CustomRestException extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ DataIntegrityViolationException.class })
  public ResponseEntity<ApiErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
      WebRequest request) {

    String error = "Operação violou uma restrição de integridade.";

    String message = "Favor preencher todos os campos";

    ApiErrorDTO apiError = new ApiErrorDTO(message, error, HttpStatus.NOT_ACCEPTABLE);

    return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

  }

  @ExceptionHandler({ NotFoundException.class })
  public ResponseEntity<ApiErrorDTO> handleNotFoundException(NotFoundException ex,
      WebRequest request) {

    String error = "Product not found";

    String message = "Campo ID inválido";

    ApiErrorDTO apiError = new ApiErrorDTO(message, error, HttpStatus.NOT_FOUND);

    return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

  }

  @ExceptionHandler({ EmptyResultDataAccessException.class })
  public ResponseEntity<ApiErrorDTO> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
      WebRequest request) {

    String error = "Product not found";

    String message = "Campo ID inválido";

    ApiErrorDTO apiError = new ApiErrorDTO(message, error, HttpStatus.NOT_FOUND);

    return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

  }

}