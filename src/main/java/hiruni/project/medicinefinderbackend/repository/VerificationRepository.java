package hiruni.project.medicinefinderbackend.repository;

import hiruni.project.medicinefinderbackend.entity.User;
import hiruni.project.medicinefinderbackend.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Integer> {

    Optional <Verification> findByToken(String token);
}
