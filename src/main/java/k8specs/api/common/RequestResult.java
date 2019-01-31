package k8specs.api.common;

import lombok.Data;

@Data
public class RequestResult<T> {

    public static final String RESULT_ERROR = "error";
    public static final String RESULT_OK = "ok";

    public String result;
    public String message;
    public T data;

    public RequestResult(String result) {

        this.result = result;

    }

    public RequestResult(String result, String message) {

        this.result = result;
        this.message = message;

    }

    public RequestResult(String result, T data) {

        this.result = result;
        this.data = data;

    }

    public RequestResult(String result, String message, T data) {

        this.result = result;
        this.message = message;
        this.data = data;

    }

}
