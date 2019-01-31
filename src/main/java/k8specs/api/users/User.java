package k8specs.api.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    @JsonIgnore
    private Long id;

    private String displayName;

    private String email;

    @JsonIgnore
    private String password;

    private String socialGithub;
    private String socialTwitter;

    private int countPosts;

    @CreationTimestamp
    private LocalDateTime stampCreated;

}
