package uz.pdp.apptoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apptoken.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsUserByUsername(String username);
    Optional<User>findByUsername(String username);

    User getUserByUsername(String username);
}
