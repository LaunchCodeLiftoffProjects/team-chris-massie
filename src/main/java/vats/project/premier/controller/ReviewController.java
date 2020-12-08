package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vats.project.premier.models.data.ReviewRepository;

@Controller
@RequestMapping("reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @RequestMapping("reviews")
    public String displayReviewsForm(Model model) {
        model.addAttribute("Reviews form", "Reviews");

        return "reviews";
    }

}
