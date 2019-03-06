package com.codeup.blog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    // this is the roll-dice page
    @GetMapping("/roll-dice")
    public String rollTheDice() {
        return "roll-dice";
    }

    // this is the roll-dice results page
    @GetMapping("/roll-dice/{guess}")
    public String rollTheDice(@PathVariable int guess, Model model) {
        System.out.println(guess);
        int random = (int) Math.ceil(Math.random() * 6);
        model.addAttribute("random", random);
        model.addAttribute("guess", guess);

        //this compares the users guess with the dice roll result and sets the data to be passed to the html
        Boolean guessedNumber;
        if (guess == random){
            //this shows returns a boolean and gets the message in the rollDiceResult html file
            model.addAttribute("guessedNumber", true);
        } else {
            model.addAttribute("guessedNumber", false);
        }

        return "rollDiceResults";
    }

}
