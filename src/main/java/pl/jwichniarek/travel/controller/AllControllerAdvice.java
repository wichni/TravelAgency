package pl.jwichniarek.travel.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice()
public class AllControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public void handleException() {
        System.out.println("----------CaughtRuntimeException-----------");
    }

    @ExceptionHandler(value = Exception.class)
    public void notFoundGlobal() {
        System.out.println("----------CaughtException-----------");
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public void nofFoundDateTime() {
        System.out.println("----------CaughtDateTimeParseException-----------");
    }
}
