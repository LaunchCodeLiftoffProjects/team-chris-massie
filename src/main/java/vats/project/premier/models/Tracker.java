package vats.project.premier.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Tracker extends AbstractEntity{

//    @OneToMany(cascade= CascadeType.ALL)
//    private List<Game> games = new ArrayList<>();

    private String userName;

    @ManyToOne
    private Game game;

    public Tracker() {}

//    public Tracker(List<Game> agames, String userName){
    public Tracker(Game agame, String userName){
        this.userName = userName;
//        this.games = agames;
        this.game = agame;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public List<Game> getGames() {
    public Game getGame(){
//        return games;
        return game;
    }

//    public void setGames(List<Game> games) {
//        this.games = games;
//    }
    public void setGame(Game game) {
        this.game = game;
    }
}

