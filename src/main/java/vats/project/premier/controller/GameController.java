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
    public String processGamesForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model, @RequestParam List<Integer>  achievements, @RequestParam List<Integer> reviews){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            model.addAttribute("myerrors", errors.toString());
            return "games";
        }
        List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
        newGame.setAchievements(achievementObj);

        List<Review> reviewObj = (List<Review>)reviewRepository.findAllById(reviews);
        newGame.setReviews(reviewObj);

        gameRepository.save(newGame);
        return "redirect:/games";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Game");
        model.addAttribute(new Game());
        model.addAttribute("categories", gameRepository.findAll());
        return "create";
    }
}
