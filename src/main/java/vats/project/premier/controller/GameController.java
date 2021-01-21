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
import java.util.Optional;

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
    public String processGamesForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model,
                                   @RequestParam String userName,  @RequestParam(required = false) List<Integer>  achievements,
                                   @RequestParam(required = false) List<Integer> reviews){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            model.addAttribute("myerrors", errors.toString());
            return "games";
        }

        if(achievements == null) {
            return "redirect:/games";
        } else {
            List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
            newGame.setAchievements(achievementObj);
        } if(reviews == null) {
            return "redirect:/games";
        } else {
            List<Review> reviewObj = (List<Review>) reviewRepository.findAllById(reviews);
            newGame.setReviews(reviewObj);
        }
        newGame.setUserName(userName);

        gameRepository.save(newGame);
        return "redirect:/games";
    }

    @GetMapping("gameDetails")
    public String displayGameDetails(@RequestParam Integer gameId, Model model, Error error,
                                     @RequestParam(required = false) String platform,
                                     @RequestParam(required = false) List<Integer>  achievements,
                                     @RequestParam(required = false) List<Integer> reviews) {

        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        Optional<Game> result = gameRepository.findById(gameId);

        Game game = result.get();
        model.addAttribute("title", game.getName() + "Details");
        model.addAttribute("game", game);
        model.addAttribute("platform", platform);

        if(achievements != null) {
            List<Achievement> achievementObj =  game.getAchievements();
            model.addAttribute("achievements", achievementObj);
        } else {
            String achievementObj = "Create Achievement?";
            model.addAttribute("achievements", achievementObj);
        }
        if (reviews != null) {
                List<Review> reviewObj = game.getReviews();
                model.addAttribute("reviews", reviewObj);
        } else {
            String reviewObj = "Create Review?";
            model.addAttribute("reviews", reviewObj);
        }


        return "gameDetails";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Game");
        model.addAttribute(new Game());
        model.addAttribute("categories", gameRepository.findAll());
        return "create";
    }

    @GetMapping("delete")
    public String displayDeleteGameForm(Model model) {

        model.addAttribute("title", "Delete Games");
        model.addAttribute("games", gameRepository.findAll());
        return "delete";
    }

    @PostMapping("delete")
    public String processDeleteGamesForm(@RequestParam(required = false) int[] gameIds) {

        if (gameIds != null) {
            for (int id : gameIds) {
                gameRepository.deleteById(id);
            }
        }
        return "redirect:/games";
    }

    @GetMapping("update")
    public String displayUpdateGameForm(Model model) {
        model.addAttribute("Games form", "Games");
        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        model.addAttribute("title", "Update Games");
        model.addAttribute(new Game());
        return "update";
    }

    @PostMapping("update")
    public String processUpdateGamesForm(@ModelAttribute @Valid Game newGame, Model model, Errors errors,
                                         @RequestParam int gameId, @RequestParam(required = false) String platform,
                                         @RequestParam String userName, @RequestParam(required = false) List<Integer>  achievements,
                                         @RequestParam(required = false) List<Integer> reviews) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update Game");
            model.addAttribute("myerrors", errors.toString());
            return "games";
        }

        Game game = gameRepository.findById(gameId).get();
        System.out.println(gameId);

        String gamePlatform = game.getPlatform();

        if(achievements != null) {
            List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
            game.setAchievements(achievementObj);
        }
        if (reviews != null) {
            List<Review> reviewObj = (List<Review>)reviewRepository.findAllById(reviews);
            game.setReviews(reviewObj);
        }
        if (gamePlatform != null) {
            game.setPlatform(gamePlatform);

        } else {
            game.setPlatform(platform);
        }
        game.setUserName(userName);

        gameRepository.save(game);
        return "redirect:/games";

    }
}
