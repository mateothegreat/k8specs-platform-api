package k8specs.api.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(final PostsService postsService) {

        this.postsService = postsService;

    }

    @GetMapping("/search")
    public ResponseEntity<Page<Post>> getAll(Pageable pageable) {

        return new ResponseEntity<>(postsService.getAll(pageable), HttpStatus.OK);

    }

    @GetMapping("/user/{displayName}")
    public ResponseEntity<Page<Post>> getByDisplayName(@PathVariable String displayName, Pageable pageable) {

        return new ResponseEntity<>(postsService.getByDisplayName(displayName, pageable), HttpStatus.OK);

    }

    @GetMapping("/user/{displayName}/{postName}")
    public ResponseEntity<Post> getByDisplayName(@PathVariable String displayName, @PathVariable String postName) {

        Optional<Post> post = postsService.getByNameAndUserDisplayName(displayName, postName);

        if (post.isPresent()) {

            return new ResponseEntity<>(post.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) {

        Optional<Post> post = postsService.getById(id);

        if (post.isPresent()) {

            return new ResponseEntity<>(post.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping()
    public ResponseEntity<Post> create(Principal principal, @RequestBody PostCreate postCreate) {

        Optional<Post> _post = postsService.create(principal, postCreate);

        if (_post.isPresent()) {

            return new ResponseEntity<>(_post.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

    }

}
