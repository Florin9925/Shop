package com.example.shopbackend.security.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.shopbackend.security.enums.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    BASIC_USER(Sets.newHashSet(PRODUCT_READ, ORDER_READ)),
    ADMIN(Sets.newHashSet(ORDER_READ, ORDER_WRITE, USER_READ, USER_WRITE, PRODUCT_WRITE, PRODUCT_READ, PRODUCT_DELETE)),
    UNSPECIFIED(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> _permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        _permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return _permissions;
    }
}

