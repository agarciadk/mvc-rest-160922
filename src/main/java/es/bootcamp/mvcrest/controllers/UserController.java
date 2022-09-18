package es.bootcamp.mvcrest.controllers;

import es.bootcamp.mvcrest.errorHandler.UserNotFoundException;
import es.bootcamp.mvcrest.model.User;
import es.bootcamp.mvcrest.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> all() {
       return (List<User>) repository.findAll();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("{id}")
    public User replace(@PathVariable Long id, @RequestBody User body) {
        return repository.findById(id)
                .map(user -> {
                   user.setFirstName(body.getFirstName());
                   user.setLastName(body.getLastName());
                   return repository.save(user);
                })
                .orElseGet(() -> {
                    body.setId(id);
                    return repository.save(body);
                });
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping( params = { "page", "size" })
    public Page<User> list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}
