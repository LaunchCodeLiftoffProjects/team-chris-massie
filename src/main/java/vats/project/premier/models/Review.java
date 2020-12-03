package vats.project.premier.models;

import javax.persistence.Entity;

@Entity
public class Review extends AbstractEntity{

    private String description;

    public Review() {};

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
