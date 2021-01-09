package vats.project.premier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends AbstractEntity{

    @OneToMany
    private final List<Tracker> trackers = new ArrayList<>();

    @OneToMany(cascade= CascadeType.ALL)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(cascade= CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @NotBlank(message = "Please select a platform.")
    private String platform;

    public Game(){}

    public Game(String platform, List<Achievement> anAchievement, List<Review> aReview){
        this.platform = platform;
        this.achievements = anAchievement;
        this.reviews = aReview;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
