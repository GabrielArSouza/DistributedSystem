package ufrn.microservice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.ApplicationUser;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

}
