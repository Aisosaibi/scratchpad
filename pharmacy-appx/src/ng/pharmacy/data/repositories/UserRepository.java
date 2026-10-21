package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.User;

public interface UserRepository extends ParentRepository<User, Long> {
    User findByUsername(String username);
}
