package ufrn.microservice.auth.endpoint.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ufrn.microservice.auth.endpoint.service.UserService;
import ufrn.microservice.core.model.ApplicationUser;

import java.security.Principal;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage User's Information")
public class UserController {
    private final UserService userService;

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Will retrieve the information from the user available in the token", response = ApplicationUser.class)
    public ResponseEntity<ApplicationUser> getUserInfo(Principal principal){
        ApplicationUser applicationUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<>(applicationUser, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addQuestion (@RequestBody ApplicationUser user){
        userService.add(user);
    }

}
