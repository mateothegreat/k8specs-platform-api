package k8specs.api.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IsAliveController {

    @GetMapping("/is_alive")
    public ResponseEntity<String> isAlive() {

        return new ResponseEntity<>("I'm alive!", HttpStatus.OK);

    }

}
