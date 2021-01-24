package vats.project.premier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends AbstractEntity{

    @OneToMany(cascade=CascadeType.ALL)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Review review;

//    @NotBlank(message = "Please select a platform.")
    private String platform;

    private String userName;

    public Game(){}

    public Game(String platform, String userName, List<Achievement> achievements, Review review) {
        this.platform = platform;
        this.userName = userName;
        this.achievements = achievements;
        this.review = review;
    }

    public Game(String platform, String name) {
        this.platform = platform;
        this.name = name;
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

    public Review getReview() { return review; }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
