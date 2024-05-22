package com.ndb.bankingApp.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    private int statusCode;
    private LocalDateTime timeStamp;
    private String errorMessage;
    private String description;

}
