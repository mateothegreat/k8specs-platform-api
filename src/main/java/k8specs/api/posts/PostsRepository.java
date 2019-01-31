package k8specs.api.posts;

import k8specs.api.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PostsRepository extends PagingAndSortingRepository<Post, Long> {

    Page<Post> getByUser(User user, Pageable pageable);

    Optional<Post> getByUserAndName(User user, String name);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO post_tags SET post_id = :post_id, tags_id = :tags_id", nativeQuery = true)
    void createTagLink(Long post_id, Long tags_id);

}
