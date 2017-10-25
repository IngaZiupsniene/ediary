package demo.controller;

import demo.model.Role;
import demo.model.User;
import demo.service.IRoleService;
import demo.service.ISecurityService;
import demo.service.IUserService;
import demo.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserService iUserService;
    @Autowired
    ISecurityService iSecurityService;
    @Autowired
    UserValidator userValidator;
    @Autowired
    IRoleService iRoleService;

    @RequestMapping(value = "/index")
    public String test(){
        return "index";}

    @RequestMapping( value ={ "/main", "/"}, method = RequestMethod.GET)
    public String welcome(Model model){
        model.addAttribute("username", iSecurityService.findLoginUsername());
        return "main";
    }

    @RequestMapping( value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error){
        if(error!=null){
            model.addAttribute("error", "wrong username and password");
        }
        return "login";
    }

    @RequestMapping( value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("userform", new User());
        List<Role> roleList=iRoleService.roleList();

        model.addAttribute("rolelist", roleList );
        return "registration";
    }


    @RequestMapping( value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userform") User userform, BindingResult bindresult, Model model){
        userValidator.validate(userform, bindresult); //bindresult - error'o reiksmes is musu klaidu saraso (issikvietem per interfeisa)
        if(bindresult.hasErrors()){
            return "registration";
        }

        iUserService.save(userform); //irasome i duomenu baze //hipernate viska padaro
        iSecurityService.login(userform.getUsername(), userform.getPasswordconfirm()); //tikrinam sauguma ir spring prilogina

        return "redirect:/main"; //nukreipia i pagrindini programos puslapi
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);//tik iskvieciam objekta, jo kurti nereikia
        }
        return "redirect:/login?logout"; //numeta i logina ir is karto turi suveikti logout
    }



    @RequestMapping(value = "/getrole", method = RequestMethod.GET)
    public String getRole(Model model,  Authentication authentication ){
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities(); //gaunam kolekcija roliu
        Iterator<?extends GrantedAuthority> iterator=authorities.iterator();
        while (iterator.hasNext()){
            GrantedAuthority next=iterator.next();
            String role=next.getAuthority();
            if (role.equals("administratorius")){
                return "adminlangas";
            }
        }
        return "ka jau sukursim";
    }

}
