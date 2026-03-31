package se.iths.axel.springmessenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.iths.axel.springmessenger.model.Email;
import se.iths.axel.springmessenger.service.MessageService;

@Controller
@RequestMapping("/email")
public class EmailController {

    private final MessageService service;

    public EmailController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("email", new Email());
        return "form";
    }

    @PostMapping
    public String mail(@ModelAttribute Email email, RedirectAttributes redirectAttributes) {
        service.send(email);
        redirectAttributes.addFlashAttribute("success", "Mail sent");
        return "redirect:/email";
    }
}
