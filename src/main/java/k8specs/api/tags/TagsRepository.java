package k8specs.api.tags;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TagsRepository extends PagingAndSortingRepository<Tag, Long> {

    Optional<Tag> getByName(String name);

}
