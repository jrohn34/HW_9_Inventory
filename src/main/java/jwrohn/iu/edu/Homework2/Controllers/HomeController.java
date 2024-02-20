package jwrohn.iu.edu.Homework2.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String greetings() {
        return "Welcome to the Guitar Store!";
    }
}
//re-push