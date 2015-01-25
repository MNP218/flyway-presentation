package no.jpro.flywaydemo;

import no.jpro.flywaydemo.domain.Company;
import no.jpro.flywaydemo.domain.Person;
import no.jpro.flywaydemo.domain.PersonRepository;
import no.jpro.flywaydemo.infrastructure.CompanyJpaRepository;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DemoApplication {
    private final PersonRepository personRepository;
    private final CompanyJpaRepository companyJpaRepository;

    public DemoApplication(EntityManager entityManager) {
        this.personRepository = new PersonJpaRepository(entityManager);
        this.companyJpaRepository = new CompanyJpaRepository(entityManager);
    }

    public void run() {
        Person frode = personRepository.save(new Person("Frode", "Rystad"));
        Person john = personRepository.save(new Person("John", "Doe"));

        Company jPro = new Company("jPro");
        jPro.addEmployee(frode);
        jPro.addEmployee(john);

        companyJpaRepository.save(jPro);
    }

    public static void main(String[] args) {
        new DemoApplication(entityManager()).run();
    }

    private static EntityManager entityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("no.jpro.flywaydemo");
        return entityManagerFactory.createEntityManager();
    }
}
