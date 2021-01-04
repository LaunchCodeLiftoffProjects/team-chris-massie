package vats.project.premier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tracker extends AbstractEntity{

    @OneToMany(cascade= CascadeType.ALL)
    private List<Game> games = new ArrayList<>();

    private String userName;

    public Tracker() {}

    public Tracker(List<Game> agames, String userName){
        this.userName = userName;
        this.games = agames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}

