package com.publicis.sapient.InvestorHoldingGraph.Controller;

import com.publicis.sapient.InvestorHoldingGraph.Exception.BrokenPayloadException;
import com.publicis.sapient.InvestorHoldingGraph.Exception.FundIdNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BrokenPayloadController {

    @ExceptionHandler(value = BrokenPayloadException.class)
    public ResponseEntity<Object> exception(BrokenPayloadException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FundIdNotPresentException.class)
    public ResponseEntity<Object> FundIdException(FundIdNotPresentException e) {
        return new ResponseEntity<>("Fund Id not found", HttpStatus.BAD_REQUEST);
    }
}
