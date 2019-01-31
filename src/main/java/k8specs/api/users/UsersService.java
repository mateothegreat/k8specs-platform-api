package k8specs.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(final UsersRepository usersRepository) {

        this.usersRepository = usersRepository;

    }

    public Optional<User> findByEmail(String email) {

        return usersRepository.findByEmail(email);

    }

    public Optional<User> findByEmailAndPassword(String email, String password) {

        return usersRepository.findByEmailAndPassword(email, password);

    }

    public Optional<User> findByDisplayName(String displayName) {

        return usersRepository.findByDisplayName(displayName);

    }

    public Optional<User> create(User user) {

        return Optional.of(usersRepository.save(user));

    }

}
