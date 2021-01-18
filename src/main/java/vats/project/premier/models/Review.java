package vats.project.premier.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review extends AbstractEntity{

    @OneToOne(cascade = CascadeType.ALL)
    private Game game;

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
