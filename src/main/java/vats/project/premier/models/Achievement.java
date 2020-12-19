package vats.project.premier.models;

import javax.persistence.Entity;

@Entity
public class Achievement extends AbstractEntity{

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
