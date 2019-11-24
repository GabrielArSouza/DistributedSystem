package ufrn.microservice.auth.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufrn.microservice.core.model.ApplicationUser;
import ufrn.microservice.core.repository.ApplicationUserRepository;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final ApplicationUserRepository repository;
    public void add (ApplicationUser user) {repository.save(user);}

}
