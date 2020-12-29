package vats.project.premier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tracker extends AbstractEntity{

    @OneToMany(cascade= CascadeType.ALL)
    private List<Game> games = new ArrayList<Game>();

    public Tracker() {};

    public Tracker(List<Game> agames){
        this.games = agames;
    }

}

