package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    String username;
    @Column(nullable = false, length = 255)
    String password;
    @Getter
    @ManyToMany(mappedBy = "favourites")
    List<Animal> favouriteAnimals;
    @Getter
    @OneToMany(mappedBy = "user")
    List<Donation> donations;
    @Getter
    @Enumerated(EnumType.STRING)
    Role role;
    @Getter
    @OneToOne
    private Image userPhoto;

    //Shelter-specific
    @Getter
    String speciality;
    @Getter
    @OneToMany
    @JoinColumn(name = "animal_id")
    List<Animal> shelterAnimals;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
