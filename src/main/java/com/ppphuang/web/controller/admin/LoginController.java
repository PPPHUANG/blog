package com.ppphuang.web.controller.admin;

import com.ppphuang.web.beans.User;
import com.ppphuang.web.service.UserService;
import com.ppphuang.web.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.awt.*;

@Controller
@RequestMapping("/admin")
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String LoginPage() {
        return "admin/login";
    }

    @GetMapping("/login")
    public String LoginPage1() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String Login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, RedirectAttributes attributes, Model model) {
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
