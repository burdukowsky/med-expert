package tk.burdukowsky.es.dao;

import tk.burdukowsky.es.models.Disease;
import tk.burdukowsky.es.models.DiseaseType;
import tk.burdukowsky.es.models.Symptom;

import java.util.List;

/**
 * Created by stanislav
 * On 15.04.17.
 */

public interface EsDao {
    List<Symptom> getSymptomsByDiseaseTypeId(Long typeId);
    List<DiseaseType> getDiseaseTypes();
    List<Disease> getProbableDiseasesBySymptomsIds(List<Long> trueSymptomIds, long typeId);
}
