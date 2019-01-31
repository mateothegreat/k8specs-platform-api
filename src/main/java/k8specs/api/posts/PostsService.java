package k8specs.api.posts;

import k8specs.api.tags.Tag;
import k8specs.api.tags.TagsService;
import k8specs.api.users.User;
import k8specs.api.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UsersService usersService;
    private final TagsService tagsService;

    @Autowired
    public PostsService(final PostsRepository postsRepository,
                        final UsersService usersService,
                        final TagsService tagsService) {

        this.postsRepository = postsRepository;
        this.usersService = usersService;
        this.tagsService = tagsService;

    }

    public Page<Post> getAll(Pageable pageable) {

        return postsRepository.findAll(pageable);

    }

    public Page<Post> getByDisplayName(String displayName, Pageable pageable) {

        Optional<User> user = usersService.findByDisplayName(displayName);

        if (user.isPresent()) {

            return postsRepository.getByUser(user.get(), pageable);

        } else {

            return Page.empty();

        }

    }

    public Optional<Post> getByNameAndUserDisplayName(String userDisplayName, String postName) {

        Optional<User> user = usersService.findByDisplayName(userDisplayName);

        if (user.isPresent()) {

            Optional<Post> post = postsRepository.getByUserAndName(user.get(), postName);

            if (post.isPresent()) {

                return post;

            } else {

                return Optional.empty();

            }

        } else {

            return Optional.empty();

        }

    }

    public Optional<Post> getById(Long id) {

        return postsRepository.findById(id);

    }

    public Optional<Post> create(Principal principal, PostCreate postCreate) {

        Optional<User> user = usersService.findByEmail(principal.getName());

        if (user.isPresent()) {


            Post post = new Post();

            post.setName(postCreate.getName());
            post.setDescription(postCreate.getDescription());
            post.setUser(user.get());
            post.setValue(postCreate.getValue());
            post.setVisibility(postCreate.getVisibility());

            for (int i = 0; i < postCreate.getTags().size(); i++) {

                Tag tag = tagsService.getOrCreate(postCreate.getTags().get(i));

                post.getTags().add(tag);

            }

            return Optional.of(postsRepository.save(post));

        } else {

            return Optional.empty();

        }

    }

    public void createTagLink(Long postId, Long tagId) {

        postsRepository.createTagLink(postId, tagId);

    }

}
