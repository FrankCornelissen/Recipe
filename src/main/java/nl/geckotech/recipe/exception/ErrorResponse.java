package nl.geckotech.recipe.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    @Getter
    private String errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;

    @Getter
    private List<String> errorMessages;

    public ErrorResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ErrorResponse(String errorCode, LocalDateTime timeStamp, List<String> errorMessages) {
        this.errorCode = errorCode;
        this.timeStamp = LocalDateTime.now();
        this.errorMessages = errorMessages;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    public void setErrorMessages(String errorMessages) {
        setErrorMessages(List.of(errorMessages));
    }
}
