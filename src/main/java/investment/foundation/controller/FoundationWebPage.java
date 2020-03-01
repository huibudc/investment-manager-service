package investment.foundation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoundationWebPage {
    @GetMapping("/foundationEmailContent")
    public String all() {
        return "foundationEmailContent";
    }
}
