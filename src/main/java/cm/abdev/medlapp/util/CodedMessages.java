package cm.abdev.medlapp.util;

import lombok.Getter;

@Getter
public enum CodedMessages {

    PARAM_NULL_NOT_ALLOWED(400, "Param null not allowed"),
    REQUIRED_PARAM_NOT_SENT(400, "Required param(s) not filled"),
    NOT_FOUND(404, "Not found"),
    ALREADY_EXIST(401, "Already exist");

    private int code;
    private String message;

    CodedMessages(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
