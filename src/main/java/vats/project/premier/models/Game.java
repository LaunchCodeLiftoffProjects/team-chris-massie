package vats.project.premier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends AbstractEntity{

    private String platform;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
