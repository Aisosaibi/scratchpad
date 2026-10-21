package ng.pharmacy.data.repositories.impl;

import ng.pharmacy.data.models.User;
import ng.pharmacy.data.repositories.interfaces.UserRepository;
import ng.pharmacy.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private long nextId = 1;

    @Override
    public User save(User user) {
//        if (!users.containsValue(user)) user.setId(nextId++);
        if (user.getId() == 0) {
            user.setId(nextId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else { throw new EntityNotFoundException("User not found"); }
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public void deleteById(Long id) {
        if (!users.containsKey(id)) throw new EntityNotFoundException("User not found");
        users.remove(id);
    }

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public void deleteAll() {

    }

}
