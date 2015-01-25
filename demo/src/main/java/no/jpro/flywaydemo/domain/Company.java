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
import java.util.Collections;

@Entity
public class Company {
    @Id
    @SequenceGenerator(name = "companySeq", sequenceName = "companySeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySeq")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Collection<Person> employees = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public Company() {
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Collection<Person> employees() {
        return Collections.unmodifiableCollection(employees);
    }

    public void addEmployee(Person employee) {
        employee.setCompany(this);
        employees.add(employee);
    }

    public void removeEmployee(Person employee) {
        employees.remove(employee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != null ? !id.equals(company.id) : company.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
