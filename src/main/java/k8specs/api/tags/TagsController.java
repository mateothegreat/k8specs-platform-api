package k8specs.api.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(final TagsService tagsService) {

        this.tagsService = tagsService;

    }

    @GetMapping("/search")
    public ResponseEntity<Page<Tag>> getAll(Pageable pageable) {

        return new ResponseEntity<>(tagsService.getAll(pageable), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Tag> create(@RequestBody Tag tag) {

        return new ResponseEntity<>(tagsService.create(tag), HttpStatus.OK);

    }

}
