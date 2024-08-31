package br.ufscar.dc.dsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.User;
 
public class MyUserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private IUserDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = dao.findByEmail(email);
         
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
         
        return new MyUserDetails(user);
    }
 
}