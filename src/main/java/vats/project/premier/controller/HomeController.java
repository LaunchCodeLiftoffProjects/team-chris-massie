package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vats.project.premier.models.Tracker;
import vats.project.premier.models.data.AchievementRepository;
import vats.project.premier.models.data.GameRepository;
import vats.project.premier.models.data.ReviewRepository;
import vats.project.premier.models.data.TrackerRepository;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TrackerRepository trackerRepository;

//    @RequestMapping("")
//    public String displayHomePage(Model model) {
//        model.addAttribute("home", "Welcome Page");
//        return "index";
//    }

    @RequestMapping("")
    public String displayHomePage(Model model) {
        model.addAttribute("home", "Welcome Page");
        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        model.addAttribute(new Tracker());
        return "index";
    }

    @RequestMapping("register")
    public String displayRegisterForm(Model model) {
        model.addAttribute("register form", "Register here");

        return "register";
    }

    @PostMapping("")
    public String processGamesForm(@ModelAttribute @Valid Tracker newTracker, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game Tracker Information");
            return "index";
        }

//        Game game= gameRepository.findById(gameId);
//        newTracker.setGames(game);
//
//        Achievement achievement=achievementRepository.findById(achievementId);
//        newTracker.setAchievements(achievement);
//
//        newTracker.setReviews(review);

        trackerRepository.save(newTracker);
        return "index";
    }



}
