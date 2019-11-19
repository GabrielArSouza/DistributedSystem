package ufrn.microservice.user.endpoint.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ufrn.microservice.core.model.User;
import ufrn.microservice.core.repository.UserRepository;

@Service
@Slf4j
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Iterable<User> list (Pageable pageable){
        return userRepository.findAll(pageable);
    }

}
