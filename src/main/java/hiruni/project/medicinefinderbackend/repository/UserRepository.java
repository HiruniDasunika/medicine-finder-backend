package hiruni.project.medicinefinderbackend.repository;

import hiruni.project.medicinefinderbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByName(String name);
//
//    User findByUserType(String type);

    Optional<User> findByEmail(String email);
}
