package ufrn.microservice.view.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufrn.microservice.core.model.ApplicationUser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Controller
public class ViewController {

    private final String gateway = "http://localhost:8080/gateway/";
    // one instance, reuse
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/home")
    public String index(){return "login";}

    @RequestMapping("/account")
    public String accountForm(){return "accountForm";}

    @PostMapping("/account")
    @SneakyThrows
    public String accountForm(ApplicationUser user){
        String data = mapper.writeValueAsString(user);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(gateway+"backend/add"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return "redirect:/home";
    }



}
