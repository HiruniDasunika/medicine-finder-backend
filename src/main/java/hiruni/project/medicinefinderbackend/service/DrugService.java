package hiruni.project.medicinefinderbackend.service;


import hiruni.project.medicinefinderbackend.entity.Drug;
import hiruni.project.medicinefinderbackend.entity.User;
import hiruni.project.medicinefinderbackend.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService  {
    @Autowired
    private DrugRepository drugrepository;

    public Drug saveDrug(Drug drug) {
        return drugrepository.save(drug);
    }

    public Drug getDrugsByDrugID(String drug_name) {return drugrepository.findByName(drug_name);}

    public List<Drug> saveDrugs(List<Drug> drugs) {
        return drugrepository.saveAll(drugs);
    }

    public List<Drug> getDrugs() {
        return drugrepository.findAll();
    }

    public Drug getDrugById(int id) {
        return drugrepository.findById(id).orElse(null);
    }

    public Drug getDrugByName(String drug_name) {
        return drugrepository.findByName(drug_name);
    }

    public Drug getDrugByBrand(String drug_brand) {
        return drugrepository.findByDrugBrand(drug_brand);
    }

    public String deleteDrug(int drug_id) {
        drugrepository.deleteById(drug_id);
        return "User Deleted successfully";
    }

    public Drug updateUser(Drug drug) {
        Drug currentDrug = drugrepository.findById(drug.getId()).orElse(null);
        if (currentDrug != null) {
            currentDrug.setQuantity(drug.getQuantity());
            return drugrepository.save(currentDrug);
        }
        return drug;
    }


}
