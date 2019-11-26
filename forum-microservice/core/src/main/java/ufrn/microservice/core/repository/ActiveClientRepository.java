package ufrn.microservice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.ActiveClient;

public interface ActiveClientRepository extends PagingAndSortingRepository<ActiveClient, Long> {

    Long deleteByUserId(Long userId);
}
