package no.jpro.flywaydemo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "personSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person1", cascade = CascadeType.ALL)
    private Collection<Friendship> friendships = new ArrayList<>();

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void addFriend(Person friend) {
        Friendship friendship = new Friendship(this, friend);
        friend.friendships.add(friendship);
        friendships.add(friendship);
    }

    public Collection<Friendship> getFriendships() {
        return friendships;
    }
}
