package org.polytech.covidapi.domain;

import static org.polytech.covidapi.domain.Privilege.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ERole {
    // On prévoit l'ajout potentiel de privilège pour le futur, 
    // cependant le programme fonctionne sans pour l'instant
    DOCTOR(
        Set.of(ALL_PRIVILEGES)
    ),
    ADMIN(
        Set.of(ALL_PRIVILEGES)
    ),
    SUPERADMIN(
        Set.of(ALL_PRIVILEGES)
    );

    @Getter
    private final Set<Privilege> privileges;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = getPrivileges()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}