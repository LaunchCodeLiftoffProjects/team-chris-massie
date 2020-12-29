package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vats.project.premier.models.Achievement;
import vats.project.premier.models.Game;
import vats.project.premier.models.Review;
import vats.project.premier.models.data.AchievementRepository;
import vats.project.premier.models.data.GameRepository;
import vats.project.premier.models.data.ReviewRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @RequestMapping("games")
    public String displayGamesForm(Model model) {
        model.addAttribute("Games form", "Games");
        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        model.addAttribute(new Game());
        return "games";
    }
    @PostMapping("games")
    public String processGamesForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model, @RequestParam List<Integer>  achievementIds, @RequestParam List<Integer> reviewIds){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "game";
        }
        List<Achievement> achievement = (List<Achievement>) achievementRepository.findAllById(achievementIds);
        newGame.setAchievements(achievement);

        List<Review> review = (List<Review>)reviewRepository.findAllById(reviewIds);
        newGame.setReviews(review);

        gameRepository.save(newGame);
        return "games";
    }
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Game");
        model.addAttribute(new Game());
        model.addAttribute("categories", gameRepository.findAll());
        return "create";
    }
}
