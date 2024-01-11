package com.workintech.twitter.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class TwitterExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException){
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                twitterException.getHttpStatus().value(),twitterException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(twitterErrorResponse,twitterException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception){
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(twitterErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
