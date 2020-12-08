package vats.project.premier.models;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String pwHash;

    @OneToMany(cascade= CascadeType.ALL)
    public List<Game> games = new ArrayList<>();

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.pwHash = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
