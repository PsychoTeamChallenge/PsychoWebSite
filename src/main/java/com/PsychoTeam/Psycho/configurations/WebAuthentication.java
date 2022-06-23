package com.PsychoTeam.Psycho.configurations;

import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    ClientService clientService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {
            Client client = clientService.getClient(inputName);

            if (client != null) {
                if (client.isEnabled()){
                    if(client.getEmail().contains("@psycho.com")){
                        return new User(client.getEmail(), client.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
                    }
                    else {
                        return new User(client.getEmail(), client.getPassword(),AuthorityUtils.createAuthorityList("CLIENT"));
                    }
                }
                else {
                    throw new UsernameNotFoundException("Client no activated: " + inputName);
                }
            }
            else {
                throw new UsernameNotFoundException("Unknown client: " + inputName);
            }

        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
