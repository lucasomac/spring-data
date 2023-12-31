package br.com.lucolimac.springdata.service;

import br.com.lucolimac.springdata.especification.EmployeeSpecification;
import br.com.lucolimac.springdata.orm.Employee;
import br.com.lucolimac.springdata.repository.EmployeeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportDynamicEmployee {

    private final EmployeeRepository employeeRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ReportDynamicEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        if (nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite o Salario");
        Double salary = scanner.nextDouble();

        if (salary == 0) {
            salary = null;
        }

        System.out.println("Digite o data de contratacao");
        String data = scanner.next();

        LocalDate dataContract;
        if (data.equalsIgnoreCase("NULL")) {
            dataContract = null;
        } else {
            dataContract = LocalDate.parse(data, formatter);
        }

        List<Employee> employees = employeeRepository.findAll(Specification
                .where(
                        EmployeeSpecification.name(nome))
                .or(EmployeeSpecification.cpf(cpf))
                .or(EmployeeSpecification.salary(salary))
                .or(EmployeeSpecification.dateContract(dataContract))
        );
        employees.forEach(System.out::println);
    }

}