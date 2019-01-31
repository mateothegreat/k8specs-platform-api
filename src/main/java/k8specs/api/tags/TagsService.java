package k8specs.api.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagsService {

    private final TagsRepository tagsRepository;

    @Autowired
    public TagsService(final TagsRepository tagsRepository) {

        this.tagsRepository = tagsRepository;

    }

    public Page<Tag> getAll(Pageable pageable) {

        return tagsRepository.findAll(pageable);

    }

    public Tag create(Tag tag) {

        return tagsRepository.save(tag);

    }

    public Optional<Tag> getByName(String name) {

        return tagsRepository.getByName(name);

    }

    public Tag getOrCreate(String tagName) {

        Optional<Tag> optionalTag = getByName(tagName);

        if (optionalTag.isPresent()) {

            return optionalTag.get();

        } else {

            Tag tag = new Tag();

            tag.setName(tagName);

            return create(tag);

        }

    }

}
