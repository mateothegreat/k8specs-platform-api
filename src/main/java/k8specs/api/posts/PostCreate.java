package k8specs.api.posts;

import lombok.Data;

import java.util.List;

@Data
public class PostCreate {

    private String name;
    private String description;
    private String value;
    private Boolean visibility;
    private List<String> tags;

}
