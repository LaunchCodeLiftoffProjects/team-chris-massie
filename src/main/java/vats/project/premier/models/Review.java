package vats.project.premier.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Review extends AbstractEntity{

    @OneToOne(optional=false, mappedBy="review")
    private Game game;

    @NotBlank(message = "Please select a game and enter a review.")
    private String description;

    public Review() {};

    public Review(Game game, String description) {
        this.game = game;
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

    public void setGame(Game games) {
        this.game = game;
    }
}
