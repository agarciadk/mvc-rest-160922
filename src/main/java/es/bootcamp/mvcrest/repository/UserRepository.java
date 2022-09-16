package es.bootcamp.mvcrest.repository;

import es.bootcamp.mvcrest.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(UUID.randomUUID(), "John", "Doe"));
        users.add(new User(UUID.randomUUID(), "Bilbo", "Baggins"));
        users.add(new User(UUID.randomUUID(), "Frodo", "Baggins"));
        users.add(new User(UUID.randomUUID(), "John", "Doe"));
        users.add(new User(UUID.randomUUID(), "Bilbo", "Baggins"));
        users.add(new User(UUID.randomUUID(), "Frodo", "Baggins"));
        users.add(new User(UUID.randomUUID(), "John", "Doe"));
        users.add(new User(UUID.randomUUID(), "Bilbo", "Baggins"));
        users.add(new User(UUID.randomUUID(), "Frodo", "Baggins"));
    }

    public List<User> getUsers() { return users; }
    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst();
    }

    public User saveUser(User user) {
        UUID newId = UUID.randomUUID();
        user.setId(newId);
        users.add(user);

        return user;
    }

    public User updateUser(UUID id, User user) {
        Integer idx = getIdx(id);
        if (idx == null) return null;
        user.setId(id);
        users.add(idx, user);
        return user;
    }

    private Integer getIdx(UUID id) {
        int idx = -1;
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getId(), id)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return null;
        return idx;
    }

    public Boolean removeUser(UUID id) {
        Integer idx = getIdx(id);
        if (idx == null) return false;

        users.remove(idx.intValue());
        return true;
    }

    public List<User> list(int page, int size) {
        int fromIndex = (page - 1) * size;
        int toIndex = fromIndex + size;
        try {
            return users.subList(fromIndex, toIndex);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
}
