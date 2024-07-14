package nl.novi.FaunaFinder.dtos.output;

import nl.novi.FaunaFinder.models.User;

public class DonationOutputDto {
    Long id;
    int amount;
    User shelter;
    User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
