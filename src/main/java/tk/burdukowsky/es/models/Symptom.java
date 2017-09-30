package tk.burdukowsky.es.models;

/**
 * Created by stanislav
 * On 15.04.17.
 */
public class Symptom {
    private long id;
    private String description;

    public Symptom(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
