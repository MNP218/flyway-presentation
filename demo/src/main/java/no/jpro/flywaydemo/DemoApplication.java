package no.jpro.flywaydemo;

import no.jpro.flywaydemo.domain.Company;
import no.jpro.flywaydemo.domain.CompanyRepository;
import no.jpro.flywaydemo.domain.Person;
import no.jpro.flywaydemo.domain.PersonRepository;
import no.jpro.flywaydemo.infrastructure.CompanyJpaRepository;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DemoApplication {
    private final EntityManager entityManager;
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    public DemoApplication(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.personRepository = new PersonJpaRepository(entityManager);
        this.companyRepository = new CompanyJpaRepository(entityManager);
    }

    public void run() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Person frode = personRepository.save(new Person("Frode", "Rystad"));
        Person john = personRepository.save(new Person("John", "Doe"));

        Company jPro = new Company("jPro");
        jPro.addEmployee(frode);
        jPro.addEmployee(john);

        companyRepository.save(jPro);

        transaction.commit();
        entityManager.getEntityManagerFactory().close();
    }

    public static void main(String[] args) {
        new DemoApplication(entityManager()).run();
    }

    private static EntityManager entityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("no.jpro.flywaydemo.standalone");
        return entityManagerFactory.createEntityManager();
    }
}
