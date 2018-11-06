package com.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruitment.bean.Register;
import com.recruitment.repository.RegisterRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Register user = registerRepository.findByEmailId(emailId);
System.err.println(user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
       
        	System.err.println("ROLE:::::::::"+user.getUserType());
        	 grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserType()));
        
System.out.println("rambabu");
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), grantedAuthorities);
    }
}
