
package com.example.Pos_System.controller;

import com.example.Pos_System.models.Users;
import com.example.Pos_System.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
   @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginPage(Model model, RedirectAttributes redirectAttributes, HttpSession session){
      Users currentUsers = (Users) session.getAttribute("currentUsers");
      if(Objects.nonNull(currentUsers)){
        model.addAttribute("users",currentUsers);
        return "redirect:/dashboard";
      }
      model.addAttribute("users",new Users());
      return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("users") Users users, RedirectAttributes redirectAttributes, HttpSession session){
        Users validUsers = userService.validateUsers(users.getUsername(), users.getPassword(),users.getRole());
        if(Objects.nonNull(validUsers)){
            redirectAttributes.addFlashAttribute("message", "Login Successfull!");
            session.setAttribute("currentUsers", validUsers);
            if(validUsers.getRole().equals("admin")){
                return "redirect:/index";
            }else{
               return "redirect:/dashboard"; 
            }
        }else{
            redirectAttributes.addFlashAttribute("error","Invalid username or password.Please Try again");
            return "redirect:/login";
        }
        
    }
    @GetMapping("/dashboard")
    public String showDashboardPage(Model model, HttpSession session, RedirectAttributes redirectAttributes){
        Users currentUsers = (Users) session.getAttribute("currentUsers");
        if(Objects.isNull(currentUsers)){
            redirectAttributes.addFlashAttribute("error", "You are not Logged In. Please re-login to account");
            return "redirect:/login";
        } 
        model.addAttribute("users",currentUsers);
        return "index"; 
    } 
}
