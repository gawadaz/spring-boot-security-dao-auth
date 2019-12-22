package com.jawadaz.springsecurity01.service;

import com.jawadaz.springsecurity01.dao.RoleRepository;
import com.jawadaz.springsecurity01.dao.UserRepository;
import com.jawadaz.springsecurity01.entity.Role;
import com.jawadaz.springsecurity01.entity.User;
import com.jawadaz.springsecurity01.util.UserToUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserToUserDetails userToUserDetailsconverter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = new User("jawad","test123", new ArrayList<>());
        User user = findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found!"));
        return userToUserDetailsconverter.convert(user);
    }

    @Override
    @Transactional
    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public void save(User user) throws Exception{
        Optional<User> theUser = findUserByUserName(user.getUserName());
        if(theUser.isPresent()) {
            throw new Exception("User: " + user.toString() + " Already exists!");
        }
        Set<Role> theRoles = user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()).orElse(role))
                .collect(Collectors.toSet());
        user.setRoles(theRoles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
