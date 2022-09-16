package es.bootcamp.mvcrest.controllers;

import es.bootcamp.mvcrest.errorHandler.UserListNotAcceptable;
import es.bootcamp.mvcrest.errorHandler.UserNotFoundException;
import es.bootcamp.mvcrest.model.User;
import es.bootcamp.mvcrest.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
       return userRepository.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepository.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = userRepository.updateUser(id, user);
        if (updatedUser == null) {
            throw new UserNotFoundException(id);
        }
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable UUID id) {
        userRepository.removeUser(id);
    }

    @GetMapping( params = { "page", "size" })
    public List<User> list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "3") int size
    ) {
        List<User> list = userRepository.list(page, size);
        if (list == null) {
            throw new UserListNotAcceptable(page, size);
        }
        return list;
    }
}
