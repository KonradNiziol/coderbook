package pl.kniziol.coderbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kniziol.coderbook.dto.MessageInformation;

@Controller
public class MainController {

    @GetMapping({"", "/","/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/notFound")
    public String notFound(Model model) {
        model.addAttribute("message", MessageInformation.builder()
                .cssStyle("cssStyleAlert.danger")
                .messageStatus("Danger!")
                .message("Page not found")
                .build());
        return "infoMessage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model){
        model.addAttribute("message", MessageInformation.builder()
                .cssStyle("cssStyleAlert.danger")
                .messageStatus("message.danger")
                .message("Access Denied!")
                .build());
        return "infoMessage";
    }

    // TODO for test
    @GetMapping("/user")
    public String user(){
        return "user/user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

}
