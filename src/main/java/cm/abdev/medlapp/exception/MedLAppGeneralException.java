package cm.abdev.medlapp.exception;

import lombok.Getter;

@Getter
public class MedLAppGeneralException extends Exception {
    private int errorCode;
    private String errorMessage;

    public MedLAppGeneralException(int code, String message) {
        super(message);
        this.errorCode = code;
        this.errorMessage = message;
    }
}
