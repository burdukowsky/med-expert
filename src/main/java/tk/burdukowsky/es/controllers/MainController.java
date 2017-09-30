package tk.burdukowsky.es.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.burdukowsky.es.dao.EsDao;
import tk.burdukowsky.es.models.Disease;
import tk.burdukowsky.es.models.DiseaseType;
import tk.burdukowsky.es.models.Symptom;

import java.util.List;

/**
 * Created by stanislav
 * On 15.04.17.
 */
@Controller
public class MainController {

    private EsDao esDao;

    @Autowired
    public void setEsDao(EsDao esDao) {
        this.esDao = esDao;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<DiseaseType> diseaseTypes = esDao.getDiseaseTypes();
        model.addAttribute("diseaseTypes", diseaseTypes);
        return "index";
    }

    @RequestMapping("/symptoms-by-disease-type-id")
    @ResponseBody
    public List<Symptom> symptomsByDiseaseTypeId(@RequestParam("typeId") long typeId) {
        return esDao.getSymptomsByDiseaseTypeId(typeId);
    }

    @RequestMapping("/diseases-by-symptoms-ids")
    @ResponseBody
    public List<Disease> diseasesBySymptomsIds(@RequestParam("ids") List<Long> trueSymptomIds,
                                               @RequestParam("typeId") long typeId) {
        return esDao.getProbableDiseasesBySymptomsIds(trueSymptomIds, typeId);
    }
}
