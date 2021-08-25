package com.md.admin.user;

import com.md.admin.security.MdUserDetails;
import com.md.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService service;

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal MdUserDetails loggedUser,
                              Model model) {
        String mail = loggedUser.getUsername();
        User user = service.getByEmail(mail);


        model.addAttribute("user", user);
        return "account_form";
    }
}
