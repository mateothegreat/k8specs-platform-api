package k8specs.api.tags;

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
@Table(name = "tags", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    @JsonIgnore
    private Long id;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime stampCreated;

    private String name;

    private int countPosts;

}
