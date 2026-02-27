package com.ecommerce.sb_ecom.Security.Services;

import com.ecommerce.sb_ecom.Model.User;
import com.ecommerce.sb_ecom.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServicesImpl implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username)
                .orElseThrow(()->
                        new UsernameNotFoundException("User Not Found with username: "+username));

        return UserdetailsImpl.build(user);
    }
}
