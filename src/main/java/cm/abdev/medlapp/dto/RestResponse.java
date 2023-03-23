package cm.abdev.medlapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private int code;
    private String status;
    private String message;
    private  T data;


    public RestResponse(int code, String status, String message) {
        this.code = code;
        this.message =message;
        this.status = status;
    }

}
