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
                                   @RequestParam String userName,
                                   @RequestParam(required = false) List<Integer>  achievements,
                                   @RequestParam(required = false) Integer review){
        if (errors.hasErrors()) {
            model.addAttribute("Games form", "Games");
            model.addAttribute("achievements", achievementRepository.findAll() );
            model.addAttribute("games", gameRepository.findAll());
            model.addAttribute("reviews", reviewRepository.findAll());
            model.addAttribute("title", "Add Game");
            model.addAttribute("myerrors", errors.toString());
            return "games";
        }

        //List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
        //if(achievementObj.contains(gameRepository.)){}

        if(achievements != null) {
            List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
            newGame.setAchievements(achievementObj);
        } if(review != null) {
            Review reviewObj = new Review();
            newGame.setReview(reviewObj);
        }
        newGame.setUserName(userName);

        gameRepository.save(newGame);
        return "redirect:/games";
    }

    @GetMapping("gameDetails")
    public String displayGameDetails(@RequestParam Integer gameId, Model model, Error error,
                                     @RequestParam(required = false) String platform,
                                     @RequestParam(required = false) List<Integer>  achievements,
                                     @RequestParam(required = false) Integer review) {

        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("review", reviewRepository.findAll());
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
        if (review != null) {
                Review reviewObj = new Review();
                model.addAttribute("review", reviewObj);
        } else {
            String reviewObj = "Create Review?";
            model.addAttribute("review", reviewObj);
        }


        return "gameDetails";
    }

    @GetMapping("achievementDetails")
    public String displayAchievementDetails(@RequestParam Integer achievementId, Model model, Error error,
                                     @RequestParam(required = false) String platform,
                                     @RequestParam(required = false) Achievement  achievements) {

        model.addAttribute("achievements", achievementRepository.findAll() );
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("review", reviewRepository.findAll());
        Optional<Achievement> result = achievementRepository.findById(achievementId);

        Achievement achievement = result.get();
        model.addAttribute("title", achievement.getName() + "Details");
        model.addAttribute("game", achievement);
        model.addAttribute("platform", platform);

        if(achievements != null) {
            Achievement achievementObj =  result.get();
            model.addAttribute("achievements", achievementObj);
        } else {
            String achievementObj = "Create Achievement?";
            model.addAttribute("achievements", achievementObj);
        }



        return "achievementDetails";
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
        return "redirect:games";
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
                                         @RequestParam String userName,
                                         @RequestParam(required = false) List<Integer> achievements,
                                         @RequestParam(required = false) Integer reviews) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update Game");
            model.addAttribute("myerrors", errors.toString());
            return "games";
        }


        Game game = gameRepository.findById(gameId).get();
        System.out.println(gameId);


        String gamePlatform = game.getPlatform();
        List<Achievement> gameAchievements = game.getAchievements();

        if(achievements != null) {
            List<Achievement> achievementObj = (List<Achievement>) achievementRepository.findAllById(achievements);
            gameAchievements.addAll(achievementObj);
            game.setAchievements(gameAchievements);
        }else{
            game.setAchievements(gameAchievements);
        }

        if (reviews != null) {
            Review reviewObj = game.getReview();
            game.setReview(reviewObj);
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
