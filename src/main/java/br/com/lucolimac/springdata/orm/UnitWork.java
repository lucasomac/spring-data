package br.com.lucolimac.springdata.orm;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "unit_work")
public class UnitWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String address;
    @ManyToMany(mappedBy = "unitsWork", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Unidades: " + "id:" + id +
                "| descricao:" + description +
                "| endereco:" + address;
    }
}