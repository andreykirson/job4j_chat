//package ru.job4j.chat.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import javax.sql.DataSource;
//
///**
// * @author Andrey
// * @version 1
// * @date 2/19/2021
// */
//
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select name, password, enabled, email "
//                        + "from persons "
//                        + "where email = ?")
//                .authoritiesByUsernameQuery(
//                        "select p.email, r.authority "
//                                + "from roles as r, persons as p "
//                                + "where p.email = ? and p.authority_id = r.id");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login", "/reg")
//                .permitAll()
//                .antMatchers("/**")
//                .hasAnyRole("ADMIN", "USER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
//    }
//}
