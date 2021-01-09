package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vats.project.premier.models.Achievement;
import vats.project.premier.models.Game;
import vats.project.premier.models.Review;
import vats.project.premier.models.data.AchievementRepository;
import vats.project.premier.models.data.GameRepository;
import vats.project.premier.models.data.ReviewRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("game")
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
        model.addAttribute(new Game());
        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());

        return "game/games";
    }
    @PostMapping("games")
    public String processGamesForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model,
                                   @RequestParam(required = false) List<Integer>  achievements, @RequestParam(required = false) List<Integer> reviews){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            model.addAttribute("myerrors", errors.toString());
            return "game/games";
        }

        if(achievements == null) {
            return "redirect:/game/games";
        } else {
            List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
            newGame.setAchievements(achievementObj);
        } if(reviews == null) {
            return "redirect:/game/games";
        } else {
            List<Review> reviewObj = (List<Review>) reviewRepository.findAllById(reviews);
            newGame.setReviews(reviewObj);
        }
        gameRepository.save(newGame);
        return "redirect:/game/games";
    }



    @GetMapping("delete")
    public String displayDeleteGameForm(Model model) {

        model.addAttribute("title", "Delete Games");
        model.addAttribute("games", gameRepository.findAll());
        return "game/delete";
    }

    @PostMapping("delete")
    public String processDeleteGamesForm(@RequestParam(required = false) int[] gameIds) {

        if (gameIds != null) {
            for (int id : gameIds) {
                gameRepository.deleteById(id);
            }
        }

        return "redirect:";
    }
}
