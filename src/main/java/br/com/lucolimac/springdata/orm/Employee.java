package br.com.lucolimac.springdata.orm;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private Double salary;
    private LocalDate dateContract;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false, insertable=false, updatable=false)
    private Role role;
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_unit", joinColumns = {
            @JoinColumn(name = "fk_employee") },
            inverseJoinColumns = { @JoinColumn(name = "fk_unit") })
    private List<UnitWork> unitsWork;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getDateContract() {
        return dateContract;
    }

    public void setDateContract(LocalDate dateContract) {
        this.dateContract = dateContract;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<UnitWork> getUnitsWork() {
        return unitsWork;
    }

    public void setUnitsWork(List<UnitWork> unitsWork) {
        this.unitsWork = unitsWork;
    }

    @Override
    public String toString() {
        return "Employee: " + "id:" + id + "| nome:'" + name + "| cpf:" + cpf + "| salario:" + salary
                + "| dataContratacao:" + dateContract + "| cargo:" + role.getDescription();
    }
}
