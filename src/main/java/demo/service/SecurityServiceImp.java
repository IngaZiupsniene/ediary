package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImp implements ISecurityService{

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public String findLoginUsername() {
//        Object userDetails=SecurityContextHolder.getContext().getAuthentication().getDetails();
//       String username=null;
//        if(userDetails instanceof UserDetails); //patikrinam ar objektas priklauso UserDetails (is Security) klasei
//        username=((UserDetails)userDetails).getUsername(); //grazinam is karto username is Userdetails duomenu

        //arba paprastai, galima su nauju metodu - getnamee():

        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return username; //si username - galim panaudoti kai reikia panaudoti arba atvaizduoti konkreciai prisijungto userio duoemnis.
    }

    @Override
    public void login(String username, String password) {
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());//sifruoojama informacija

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
    }
}
