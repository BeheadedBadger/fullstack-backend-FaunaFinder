package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;

@Entity
@Table(name = "donations")
public class Donation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    int amount;
    @ManyToOne
    User shelter;
    @ManyToOne
    User user;

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getShelter() {
        return shelter;
    }

    public void setShelter(User shelter) {
        this.shelter = shelter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
