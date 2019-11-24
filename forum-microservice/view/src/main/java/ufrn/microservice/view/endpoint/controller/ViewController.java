package ufrn.microservice.view.endpoint.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ViewController {

    @RequestMapping("/home")
    public String index(){return "login";}

    @RequestMapping("/account")
    public String accountForm(){return "accountForm";}



}
