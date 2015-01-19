package no.jpro.flywaydemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Friendship {
    @Id
    @SequenceGenerator(name = "friendshipSeq", sequenceName = "friendshipSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendshipSeq")
    private Long id;

    @ManyToOne
    private Person person1;
    @ManyToOne
    private Person person2;

    public Friendship(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    public Friendship() {
    }

    public Long getId() {
        return id;
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }
}
