package k8specs.api.users;

import lombok.Data;

@Data
public class UserLogin {

    private String email;
    private String password;

}
