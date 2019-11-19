package ufrn.microservice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
