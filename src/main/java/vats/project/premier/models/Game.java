package vats.project.premier.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends AbstractEntity{

    private String system;

    private List<Achievement> achievements = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
