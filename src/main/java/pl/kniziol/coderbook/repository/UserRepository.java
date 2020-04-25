package pl.kniziol.coderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kniziol.coderbook.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean findByEmail(String email);
}
