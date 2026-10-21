package ng.pharmacy.data.repositories.interfaces;

import ng.pharmacy.data.models.User;

public interface UserRepository extends Repository<User, Long> {
    User findByUsername(String username);
}
