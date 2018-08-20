package com.tt.api.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.Collections.singleton;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {
    private String name;
    private Role userRole;
    private String login;

    @DBRef(lazy = true)
    @JsonIgnore
    private Company company;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonGetter(value = "company")
    public String getAssignedCompany() {
        return company != null ? company.getId() : null;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singleton(new SimpleGrantedAuthority(userRole == null ? Role.USER.getValue() : userRole.getValue()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return login;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
