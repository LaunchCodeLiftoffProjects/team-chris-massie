package vats.project.premier.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vats.project.premier.models.data.GameRepository;

@Controller
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
}