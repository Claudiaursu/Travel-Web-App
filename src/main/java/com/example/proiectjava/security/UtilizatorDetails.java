package com.example.proiectjava.security;
import com.example.proiectjava.model.Utilizator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilizatorDetails implements UserDetails {

    private Utilizator utilizator;

    public UtilizatorDetails(Utilizator utilizator){
        this.utilizator = utilizator;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        utilizator.getRoleList().forEach( r-> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        } );

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.utilizator.getParola();
    }

    @Override
    public String getUsername() {
        return this.utilizator.getEmail();
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
        return true;
    }
}
