package es.bootcamp.mvcrest.repository;

import es.bootcamp.mvcrest.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
