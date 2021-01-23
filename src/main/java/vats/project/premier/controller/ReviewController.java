package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vats.project.premier.models.Game;
import vats.project.premier.models.Review;
import vats.project.premier.models.data.GameRepository;
import vats.project.premier.models.data.ReviewRepository;

import javax.validation.Valid;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("reviews")
    public String displayReviewsForm(Model model) {
        model.addAttribute("reviews", "Reviews");
        model.addAttribute("game", "Games");
        model.addAttribute("game", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        model.addAttribute(new Review());
        return "reviews";
    }

    @PostMapping("reviews")
    public String processReviewsForm(@ModelAttribute @Valid Review newReview, Errors errors, Model model, @RequestParam Integer gameId,
                                     @RequestParam String description){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Review");
            model.addAttribute("description", newReview.getDescription());
            model.addAttribute("reviews", "reviews");
            model.addAttribute("game", gameRepository.findAll());
            model.addAttribute("reviews", reviewRepository.findAll());
            return "reviews";
        }
        Game game = gameRepository.findById(gameId).get();
        game.setReview(newReview.getGame().getReview());
        gameRepository.save(game);
        newReview.setGame(game);
        reviewRepository.save(newReview);
        return "redirect:/reviews";
    }

    @GetMapping("deleteReview")
    public String displayDeleteReviewForm(Model model) {
        model.addAttribute("title", "Delete Reviews");
        model.addAttribute("reviews", reviewRepository.findAll());
        return "deleteReview";
    }
    @PostMapping("deleteReview")
    public String processDeleteReviewForm(@RequestParam(required = false) int[] reviewIds) {
        if (reviewIds != null) {
            for (int id : reviewIds) {
                reviewRepository.deleteById(id);
            }
        }
        return "deleteReview";
    }

}




