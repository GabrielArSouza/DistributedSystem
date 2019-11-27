package ufrn.microservice.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import ufrn.microservice.core.property.JwtConfiguration;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author Gabriel Araújo
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl(), "/**/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/**/swagger-resources/**", "/**/webjars/springfox-swagger-ui/**", "/**/v2/api-docs/**").permitAll()
                .antMatchers(
                        "/view/**",
                        "/view/question/**",
                        "/question/**",
                        "/view/answer/**",
                        "/answer/**",
                        "/view/home/**",
                        "/home/**",
                        "/view/account/**",
                        "/account/**",
                        "/view/login/**",
                        "/login/**",
                        "/view/logout/**",
                        "/logout/**").permitAll()
                .antMatchers("/backend/login/**", "/login/**", "/backend/add/**", "/add/**").permitAll()
                .antMatchers("/auth/login/**").permitAll()
                .antMatchers("/auth/user/add/**", "/auth/add/**", "/user/add/**","/add/**").permitAll()
                .antMatchers("/question/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/auth/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();
    }
}