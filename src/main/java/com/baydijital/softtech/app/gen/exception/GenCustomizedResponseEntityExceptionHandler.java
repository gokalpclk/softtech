package com.baydijital.softtech.app.gen.exception;

import com.baydijital.softtech.app.gen.dto.RestResponse;
import com.baydijital.softtech.app.gen.exceptions.GenBusinessException;
import com.baydijital.softtech.app.gen.exceptions.ItemNotFoundException;
import com.baydijital.softtech.app.kafka.dto.LogMessage;
import com.baydijital.softtech.app.log.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Gokalp on 7/19/22
 */
@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final LogService logService;


    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);
        RestResponse<Object> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        logError(genExceptionResponse);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(GenExceptionResponse genExceptionResponse) {
        String message = genExceptionResponse.getMessage();
        String description = genExceptionResponse.getDescription();
        LogMessage logMessage = LogMessage.builder()
                .message(message)
                .description(description)
                .dateTime(new Date())
                .build();
        logService.log(logMessage);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllItemNotFoundException(ItemNotFoundException ex, WebRequest webRequest) {

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        logError(genExceptionResponse);

        return new ResponseEntity<>(RestResponse.error(genExceptionResponse), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenBusinessException(GenBusinessException ex, WebRequest webRequest) {
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        logError(genExceptionResponse);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Date errorDate = new Date();
        String message = "Validation failed";
        String description = ex.getBindingResult().toString();

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        logError(genExceptionResponse);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }
}
