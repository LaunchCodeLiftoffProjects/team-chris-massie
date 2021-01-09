package vats.project.premier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends AbstractEntity{

    private String platform;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Review reviews;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();

    public Game(){}

    public Game(String platform, List<Achievement> achievements, Review reviews) {
        this.platform = platform;
        this.achievements = achievements;
        this.reviews = reviews;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Review getReviews() {
        return reviews;
    }

    public void setReviews(Review reviews) {
        this.reviews = reviews;
    }
}
