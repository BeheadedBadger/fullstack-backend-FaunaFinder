package nl.novi.FaunaFinder.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Entity
@JsonIgnoreProperties({"favouriteAnimals", "shelterAnimals"})
@Table(name = "users")
public class User implements UserDetails {
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    @Id
    private Long id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    String username;
    @Column(nullable = false, length = 255)
    String password;
    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference (value="favouriteAnimals")
    @JoinTable( name = "favourites",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<Animal> favouriteAnimals;
    @Getter
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "donations")
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
    @JsonManagedReference (value="shelterAnimals")
    @JoinColumn(name = "shelter_animals")
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
