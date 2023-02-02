package hiruni.project.medicinefinderbackend.repository;

//import hiruni.project.medicinefinderbackend.entity.Auth;
import hiruni.project.medicinefinderbackend.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug,Integer> {

    Drug findByName(String drug_name);

    Drug findByDrugBrand(String drug_brand);
}

