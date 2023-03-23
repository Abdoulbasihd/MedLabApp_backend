package cm.abdev.medlapp.util;

import org.springframework.http.HttpHeaders;

public class Utils {

    public static boolean isPositiveStrictInteger(int toCheck) {
        return toCheck > 0;
    }

    public static HttpHeaders getErrorHttpHeaders(String code, String message, String description) {

        HttpHeaders responseErrorHeaders = new HttpHeaders();
        responseErrorHeaders.add("code", code);
        responseErrorHeaders.add("message", message);
        responseErrorHeaders.add("description", description);

        return responseErrorHeaders;

    }
}
