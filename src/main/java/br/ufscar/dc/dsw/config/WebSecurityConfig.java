package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import br.ufscar.dc.dsw.security.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	*/
	

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authz) -> authz
                		.requestMatchers("/clientes", "/locadoras", "/locacoes").permitAll()
						.requestMatchers("/clientes/*", "/locadoras/*").permitAll()
						.requestMatchers("/locacoes/*").permitAll()
						.requestMatchers("/locadoras/cidades/*").permitAll()
						.requestMatchers("/locacoes/clientes/*").permitAll()
						.requestMatchers("/locacoes/locadoras/*").permitAll()
						.anyRequest().authenticated())
			/*.formLogin((form) -> form
						.loginPage("/login")
						.permitAll())
			.logout((logout) -> logout
						.logoutSuccessUrl("/").permitAll())*/;

        return http.build();
    }
} 