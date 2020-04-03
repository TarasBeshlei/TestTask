package ua.besh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.besh.domain.Message;
import ua.besh.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping(path = "/")
    public String showHome() {
        return "hello";
    }

    @PostMapping(path="/form") // Map ONLY POST Requests
    public String addNewUser (@RequestParam String name
            , @RequestParam String email, Map<String, Object> model) {

        Message message = new Message(name, email);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("message", messages);

        return "main";
    }

    @PostMapping(path = "/logout")
    public String logOut() {
        return "redirect:/login";
    }


    @GetMapping(path="/form")
    public String getAllUsers(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("message", messages);
        return "main";
    }
}