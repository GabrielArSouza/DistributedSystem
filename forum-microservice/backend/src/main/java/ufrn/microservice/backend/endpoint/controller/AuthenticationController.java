package ufrn.microservice.backend.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ufrn.microservice.backend.endpoint.service.ActiveClientService;
import ufrn.microservice.core.model.ActiveClient;
import ufrn.microservice.core.model.ApplicationUser;
import ufrn.microservice.core.util.HttpRequestManager;


import java.net.http.HttpResponse;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final String gateway = "http://localhost:8080/gateway/";
    private final ObjectMapper mapper = new ObjectMapper();
    private final ActiveClientService activeClientService;

    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ApplicationUser user){
        String data = mapper.writeValueAsString(user);
        HttpResponse<String> response = HttpRequestManager.requestPostOperation(data, gateway+"auth/login");

        try{
            String token = response.headers().firstValue("authorization").get();
            HttpResponse<String> userData = HttpRequestManager.requestGetOperationWithAuth(gateway+"auth/user/info", token);
            ApplicationUser info = mapper.readValue(userData.body(), ApplicationUser.class);

            ActiveClient client = new ActiveClient();
            client.setUserId(info.getId());
            client.setToken(token);
            activeClientService.delete(client);
            activeClientService.add(client);

            return new ResponseEntity<>(token, OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error in authentication - invalid credentials", HttpStatus.NOT_FOUND);
        }
    }

    @SneakyThrows
    @PostMapping("/{userId}/logout")
    public ResponseEntity<String> login(@PathVariable(value="userId") Long userId){
        activeClientService.logout(userId);
        return new ResponseEntity<>("Successfully logout", OK);
    }

    @PostMapping("/add")
    @SneakyThrows
    public void addAccount(@RequestBody ApplicationUser user){
        String data = mapper.writeValueAsString(user);
        HttpResponse<String> response = HttpRequestManager.requestPostOperation(data, gateway+"auth/user/add");
    }

}
