package es.bootcamp.mvcrest.repository;

import es.bootcamp.mvcrest.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<List<User>> getTop3ByFirstNameContains(String firstName);
    Optional<List<User>> getTop3ByLastNameContains(String lastName);
}
