package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ //nesuveike su autowired, tai darem per metoda ir grazinam save kaip nauja objekta
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/resource/**","/register").permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/main") //eina i pagrindini puslapi, jei praeina visus prisijungimus

                .and().logout().permitAll().and().csrf().disable();;

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{//sis metodas tik koduoja passworda
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
