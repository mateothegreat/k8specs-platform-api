package k8specs.api.authentication;

import lombok.Data;

@Data
public class LoginCredentials {

    private String email;
    private String password;

}
