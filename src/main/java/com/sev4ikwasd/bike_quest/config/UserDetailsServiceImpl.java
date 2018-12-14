package com.sev4ikwasd.bike_quest.config;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthenticationService authenticationService;

    @Autowired
    public UserDetailsServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        RestUser user = authenticationService.getUserByUsernameOrEmail(s);
        /*Collection<Role> roles = user.getRoles();
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }*/
        return new CustomUserDetails(user);
        //return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }
}
