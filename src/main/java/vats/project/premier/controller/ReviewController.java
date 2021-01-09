package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        model.addAttribute("Reviews form", "Reviews");
        model.addAttribute("games", "Games");
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());
        model.addAttribute(new Review());
        return "reviews";
    }

    @PostMapping("reviews")
    public String processReviewsForm(@ModelAttribute @Valid Review newReview, Errors errors, Model model, @RequestParam int gameId){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Review");
            return "reviews";
        }
        ;

        reviewRepository.save(newReview);
        return "redirect:/reviews";
    }

}




