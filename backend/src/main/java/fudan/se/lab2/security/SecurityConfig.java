package fudan.se.lab2.security;

import fudan.se.lab2.security.jwt.JwtRequestFilter;
import fudan.se.lab2.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author LBW
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtUserDetailsService userDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(JwtUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO: Configure your auth here. Remember to read the JavaDoc carefully.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: you need to configure your http security. Remember to read the JavaDoc carefully.

        // We dont't need CSRF for this project.
        http.csrf().disable()
                // Make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers("/api/*").permitAll()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/findAllBooks").permitAll()
                .antMatchers("/api/addBook").permitAll()
                .antMatchers("/api/categories/{cid}/books").permitAll()
                .antMatchers("/api/covers").permitAll()
                .antMatchers("/api/file/*").permitAll()
                .antMatchers("/api/addCopies").permitAll()
                .antMatchers("/api/getArea").permitAll()
                .antMatchers("/api/getCopies/{isbn}").permitAll()
                .antMatchers("/api/borrowBook").permitAll()
                .antMatchers("/api/returnBook").permitAll()
                .antMatchers("/api/search").permitAll()
                .antMatchers("/api/getDetails/{id}").permitAll()
                .antMatchers("/api/searchCopy").permitAll()
                .antMatchers("/api/reset").permitAll()
                .antMatchers("/api/reserveBook").permitAll()
                .antMatchers("/api/search-user/{keyword}").permitAll()
                .antMatchers("/api/getReserve").permitAll()
                .antMatchers("/api/libLogin").permitAll()
                .antMatchers("/api/addLibrarian").permitAll()
                .antMatchers("/api/forget").permitAll()
                .antMatchers("/api/verify").permitAll()
                .antMatchers("/api/forgetReset").permitAll()
                .antMatchers("/api/information/{userName}").permitAll()
                .antMatchers("/api/copyRecord/{isbn}").permitAll()
                .antMatchers("/api/adminRecord").permitAll()
                .antMatchers("/api/userRecord/{userName}").permitAll()
                .antMatchers("/api/userFineRecord/{userName}").permitAll()

                .antMatchers("/api/setTime/teacher").permitAll()
                .antMatchers("/api/setTime/underGraduate").permitAll()
                .antMatchers("/api/getTime/teacher").permitAll()
                .antMatchers("/api/setTime/graduate").permitAll()
                .antMatchers("/api/getTime/graduate").permitAll()
                .antMatchers("/api/getTime/underGraduate").permitAll()


                .antMatchers("/api/payFine").permitAll()
                .antMatchers("/api/remind").permitAll()
                .antMatchers("/api/sendRemind").permitAll()

                .antMatchers("/api/registerVerify").permitAll()

                .antMatchers("/api/information/getReserve/{userName}").permitAll()
                .antMatchers("/api/information/getReturn/{userName}").permitAll()


                .antMatchers("/api/creditRecord/{userName}").permitAll()
                .antMatchers("/api/userInfo/{userName}").permitAll()

                .antMatchers("/api/evaluation").permitAll()
                .antMatchers("/api/readAllBooks/{userName}").permitAll()
                .antMatchers("/api/deleteComment").permitAll()
                .antMatchers("/api/getCommentList/{isbn}").permitAll()
                .antMatchers("/api/commitReply").permitAll()

                .antMatchers("/api/Adminrefresh").permitAll()
                .antMatchers("/api/Userrefresh").permitAll()
                .antMatchers("/api/CreditRefresh").permitAll()







                .anyRequest().authenticated();

//      Here we use JWT(Json Web Token) to authenticate the user.
//      You need to write your code in the class 'JwtRequestFilter' to make it works.
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Hint: Now you can view h2-console page at `http://IP-Address:<port>/h2-console` without authentication.
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
