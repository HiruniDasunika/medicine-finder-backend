package hiruni.project.medicinefinderbackend.controller;

import hiruni.project.medicinefinderbackend.entity.Drug;
import hiruni.project.medicinefinderbackend.service.DrugService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DrugController {

    @Autowired
    private DrugService drugservice;


    @PostMapping("/addDrug")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Drug addDrug(@RequestBody Drug drug){
        return drugservice.saveDrug(drug);
    }


    @PostMapping("/addDrugs")
    public List<Drug> addDrugs(@RequestBody List<Drug> drugs){
        return drugservice.saveDrugs(drugs);

    }


    @GetMapping("/getAllDrugs")
    public List<Drug> findAllDrugs(){
        return drugservice.getDrugs();
    }


    @GetMapping("/drugById/{id}")
    public Drug findDrugById(@PathVariable int id){
        return drugservice.getDrugById(id);
    }


    @GetMapping("/drugByName/{name}")
    public Drug findDrugByName(@PathVariable String name){
        return drugservice.getDrugByName(name);
    }

    @GetMapping("/drug/{brand}")
    public Drug findDrugsByBrand(@PathVariable String brand){
        return drugservice.getDrugByBrand(brand);
    }



}
