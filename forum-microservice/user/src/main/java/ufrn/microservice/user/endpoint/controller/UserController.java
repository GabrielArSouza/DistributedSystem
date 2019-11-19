package ufrn.microservice.user.endpoint.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufrn.microservice.core.model.User;
import ufrn.microservice.user.endpoint.service.UserService;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequestMapping("v1/adm/user")
@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<User>> list(Pageable pageable){
        return new ResponseEntity<>(userService.list(pageable), OK);
    }
}
