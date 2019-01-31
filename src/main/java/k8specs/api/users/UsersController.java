package k8specs.api.users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import k8specs.api.common.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(final UsersService usersService) {

        this.usersService = usersService;

    }

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user) {

        if (usersService.findByEmail(user.getEmail()).isPresent()) {

            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

        } else {

            Optional<User> _user = usersService.create(user);

            if (_user.isPresent()) {

                return new ResponseEntity<>(_user.get(), HttpStatus.OK);

            } else {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }

        }

    }

    @PostMapping("/login")
    public ResponseEntity<RequestResult> login(@RequestBody UserLogin userLogin) {

        Optional<User> user = usersService.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());

        if (user.isPresent()) {

            String JWT = Jwts.builder()
                    .setSubject(userLogin.getEmail())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400 * 7 * 1000))
                    .signWith(SignatureAlgorithm.HS512, "supersecret@!")
                    .compact();

            return new ResponseEntity<>(new RequestResult(RequestResult.RESULT_OK, JWT), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new RequestResult(RequestResult.RESULT_ERROR, "Invalid email address and/or password."), HttpStatus.OK);

        }

    }

    @GetMapping("/profile/{displayName}")
    public ResponseEntity<User> getByDisplayName(@PathVariable String displayName) {

        Optional<User> user = usersService.findByDisplayName(displayName);

        if (user.isPresent()) {

            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

}
