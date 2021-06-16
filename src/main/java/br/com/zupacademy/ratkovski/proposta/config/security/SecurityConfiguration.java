package br.com.zupacademy.ratkovski.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
      //  http.headers().httpStrictTransportSecurity().disable();

        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers( HttpMethod.GET,"/actuator/**").permitAll()
                        .antMatchers( "/**").hasAuthority("SCOPE_meu-escopo")
                    /*    .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_meu-escopo")
                        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_meu-escopo")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_meu-escopo")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_meu-escopo")*/
                        .anyRequest().authenticated())

                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

}

