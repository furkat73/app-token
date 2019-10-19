package uz.pdp.apptoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apptoken.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
