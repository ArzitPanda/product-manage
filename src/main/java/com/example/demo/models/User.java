package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="platform_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String password;

    

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> wishList;

    @OneToMany(mappedBy="userOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "user_details_id")
    private com.example.demo.models.UserDetails userDetails;

    private LocalDateTime createdAt=java.time.LocalDateTime.now();
    private LocalDateTime updatedAt=java.time.LocalDateTime.now();

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      
      return   roles.stream().map((ele)->{
            return new SimpleGrantedAuthority("ROLE_"+ele.name());
        }).collect(Collectors.toSet());
    }

    public void setPassword(String password,PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password); 
    }


    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
  
        return email;
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
