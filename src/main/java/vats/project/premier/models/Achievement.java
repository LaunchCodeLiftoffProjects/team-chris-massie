package vats.project.premier.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Achievement extends AbstractEntity{

    @OneToOne
    private Game game;

    //@NotBlank(message = "Please enter description.")
    private String description;


    public Achievement(){}

    public Achievement(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
