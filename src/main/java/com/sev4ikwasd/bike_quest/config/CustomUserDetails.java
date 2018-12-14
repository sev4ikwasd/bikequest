package com.sev4ikwasd.bike_quest.config;

import com.sev4ikwasd.bike_quest.domain.entity.Privilege;
import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.domain.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private RestUser restUser;

    public CustomUserDetails(){

    }

    public CustomUserDetails(RestUser restUser) {
        this.restUser = restUser;
    }

    public RestUser getRestUser(){
        return restUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : restUser.getRoles()){
            for(Privilege privilege : role.getPrivileges()){
                authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return restUser.getPassword();
    }

    @Override
    public String getUsername() {
        return restUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return restUser.isEnabled();
    }
}
