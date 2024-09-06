
package com.example.Pos_System.controller;

import com.example.Pos_System.models.Users;
import com.example.Pos_System.repository.UsersRepository;
import com.example.Pos_System.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/users")
public class RegisterController {
    @Autowired
    private UsersRepository urpo;
    
    private  UserService userService;
    
    @GetMapping({"","/"})
    public String showUsersList(Model model){
        List <Users> users = urpo.findAll();
        model.addAttribute("users",users);
        return "index";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("users",new Users());
        return "/register";
    }
    @PostMapping("/register")
    public String registerUsers(@ModelAttribute Users users, RedirectAttributes redirectAttributes){
        userService.save(users);
        redirectAttributes.addFlashAttribute("message","Registration successful! Please log in.");
        return "redirect:/login";
    }
 
}
