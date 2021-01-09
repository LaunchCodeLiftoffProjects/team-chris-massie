package vats.project.premier.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review extends AbstractEntity{

    @OneToOne(cascade = CascadeType.ALL)
    private Game games;

    private String description;

    public Review() {};

    public Review(Game games, String description) {
        this.games = games;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getGames() {
        return games;
    }

    public void setGames(Game games) {
        this.games = games;
    }
}
