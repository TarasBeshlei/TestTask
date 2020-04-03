package ua.besh.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.domain.User;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
