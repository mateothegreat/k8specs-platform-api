package k8specs.api.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import k8specs.api.tags.Tag;
import k8specs.api.users.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime stampCreated;

    private String name;
    private String description;
    private String value;

    private Boolean visibility;

    private int countViews;

}
