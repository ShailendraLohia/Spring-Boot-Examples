package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private HttpStatus status;
    private List<String> errorMessages;
    private UUID uuid;
    private Date timestamp;

    public Error(Date timestamp, HttpStatus status, List<String> errorMessages) {
        this.timestamp= timestamp;
        this.errorMessages=errorMessages;
        this.status=status;
        this.uuid=UUID.randomUUID();
    }

}
