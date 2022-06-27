package edu.escuelaing.arsw.labs.board;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.escuelaing.arsw.labs.board.model.Board;
import edu.escuelaing.arsw.labs.board.model.Point;

@SpringBootApplication
@RestController
public class WebSiteController {

    public static void main(String[] args) {
        SpringApplication.run(WebSiteController.class, args);
    }

    @GetMapping("/board")
    public ModelAndView board() {
        return getModelAndViewFromName("board.html");
    }

    @PostMapping("/drawPoint")
    public void drawPoints(@RequestBody Point point) {
        Board.getInstance().addPoint(point);
    }

    @GetMapping("/getPoints")
    public List<Point> getPoints() {
        return Board.getInstance().getPoints();
    }

    @GetMapping("/clear")
    public void clear() {
        Board.getInstance().clear();
    }

    @GetMapping("/status")
    public String status() {
        return "{\"status\":\"Greetings from Spring Boot. " +
                java.time.LocalDate.now() + ", " +
                java.time.LocalTime.now() +
                ". " + "The server is Runnig!\"}";
    }

    private ModelAndView getModelAndViewFromName(String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(name);
        return modelAndView;
    }
}
