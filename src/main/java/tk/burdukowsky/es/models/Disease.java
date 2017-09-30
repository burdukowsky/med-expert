package tk.burdukowsky.es.models;

import java.util.List;
import java.util.Objects;

/**
 * Created by stanislav
 * On 18.04.17.
 */
public class Disease {
    private long id;
    private String name;
    private String advice;
    private List<Long> symptomsIds;
    private float chance;

    public Disease(long id, String name, String advice, List<Long> symptomsIds) {
        this.id = id;
        this.name = name;
        this.advice = advice;
        this.symptomsIds = symptomsIds;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdvice() {
        return advice;
    }

    public float getChance() {
        return chance;
    }

    public void calculateChance(List<Long> trueSymptomsIds) {
        final int[] countMatches = {0};
        trueSymptomsIds.forEach(id -> {
            if (symptomsIds.stream().anyMatch(sId -> Objects.equals(sId, id))) {
                countMatches[0]++;
            }
        });
        if (countMatches[0] < symptomsIds.size()) {
            chance = (float) countMatches[0] / symptomsIds.size();

        } else {
            chance = (float) symptomsIds.size() / trueSymptomsIds.size();
        }
    }
}
