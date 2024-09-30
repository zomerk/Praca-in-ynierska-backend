package com.example.pracainzynierska.service.adapter;

import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RequiredArgsConstructor
public class TrainerAdapter implements UserDetails {
    @Getter
    private final Trainer trainer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_TRAINER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return trainer.getPassword();
    }

    @Override
    public String getUsername() {
        return trainer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return trainer.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return trainer.getVerified();
    }
}