package ufrn.microservice.view.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufrn.microservice.core.model.ApplicationUser;
import ufrn.microservice.core.util.HttpRequestManager;

import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ViewController {

    private final String gateway = "http://localhost:8080/gateway/";
    private final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/home")
    public String index(){return "login";}

    @RequestMapping("/account")
    public String accountForm(){return "accountForm";}

    @PostMapping("/account")
    @SneakyThrows
    public String accountForm(ApplicationUser user){
        String data = mapper.writeValueAsString(user);
        HttpResponse<String> response = HttpRequestManager.requestPostOperation(data, gateway+"backend/add");
        return "redirect:home";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){

        Long userId = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        log.info("Realizing the logout to user id '{}'", userId);
        HttpResponse<String> response = HttpRequestManager.requestPostOperationWithAuth(
                userId.toString(), gateway+"backend/"+userId+"/logout", token);

        if (response.statusCode() == 200)
            log.info("Successfully logout");

        return "redirect:../home";
    }

    @PostMapping("/home")
    @SneakyThrows
    public String login (ApplicationUser user, HttpSession session){
        log.info("Realizing the authentication to user '{}'", user.getUsername());
        String data = mapper.writeValueAsString(user);
        HttpResponse<String> response = HttpRequestManager.requestPostOperation(data, gateway+"backend/login");

        if (response.statusCode() == 200){
            log.info("Successfully authentication, saving session. . .");
            String token = response.body();
            log.info("Getting user id");
            HttpResponse<String> userData = HttpRequestManager.requestGetOperationWithAuth(gateway+"auth/user/info", token);
            ApplicationUser info = mapper.readValue(userData.body(), ApplicationUser.class);
            log.info("Saving credentials. . .");
            session.setAttribute("applicationUser", user);
            session.setAttribute("token", token);
            session.setAttribute("userId", info.getId());
            session.setAttribute("username", user.getUsername());

            return "welcome";
        }

        return "redirect:home";
    }



}
