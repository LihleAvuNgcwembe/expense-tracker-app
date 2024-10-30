package net.javaguides.expense.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ErrorDetails {

    private LocalDate timestamp;
    private String message;
    private String details;
    private String errorCode;

}
