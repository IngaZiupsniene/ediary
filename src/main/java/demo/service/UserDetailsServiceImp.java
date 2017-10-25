package demo.service;

import demo.model.Role;
import demo.model.User;
import demo.repository.IUserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    IUserRep iUserRep;

    @Override
    @Transactional(readOnly = true) //neuzrakinti lenteles, kol gausim duomenis
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = iUserRep.findByUsername(s);
        Role role = user.getRole();
        Set<GrantedAuthority> grantedAuthorities= new HashSet<>();
//        for(Role role: user.getRole()){
      //            GrantedAuthority s= new SimpleGrantedAuthority(role.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
