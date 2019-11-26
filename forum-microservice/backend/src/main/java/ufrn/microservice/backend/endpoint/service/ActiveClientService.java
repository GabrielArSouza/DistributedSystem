package ufrn.microservice.backend.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufrn.microservice.core.model.ActiveClient;
import ufrn.microservice.core.repository.ActiveClientRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActiveClientService {

    private final ActiveClientRepository activeClientRepository;
    public void add (ActiveClient activeClient){activeClientRepository.save(activeClient);}
    @Transactional
    public void logout (Long userId){activeClientRepository.deleteByUserId(userId);}

}
