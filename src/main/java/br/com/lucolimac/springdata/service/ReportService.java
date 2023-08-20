package br.com.lucolimac.springdata.service;

import br.com.lucolimac.springdata.orm.Employee;
import br.com.lucolimac.springdata.orm.EmployeeProjection;
import br.com.lucolimac.springdata.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void init(Scanner scanner) {
        while (system) {
            System.out.println("Qual relatorio deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");
            System.out.println("3 - Busca funcionario data contratacao");
            System.out.println("4 - Pesquisa funcionario salario");

            int action = scanner.nextInt();

            switch (action) {
                case 1 -> findEmployeeByName(scanner);
                case 2 -> findEmployeeNameSalaryBiggerDateContract(scanner);
                case 3 -> findEmployeeByDateContract(scanner);
                case 4 -> searchEmployeeSalary();
                default -> system = false;
            }

        }
    }

    private void findEmployeeByName(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar");
        String name = scanner.next();
        List<Employee> list = employeeRepository.findByName(name);
        list.forEach(System.out::println);
    }

    private void findEmployeeNameSalaryBiggerDateContract(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();

        System.out.println("Qual data contratacao deseja pesquisar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Qual salario deseja pesquisar");
        Double salary = scanner.nextDouble();

        List<Employee> list = employeeRepository.findNameSalaryBiggerDataContract(nome, salary, localDate);
        list.forEach(System.out::println);
    }

    private void findEmployeeByDateContract(Scanner scanner) {
        System.out.println("Qual data contratacao deseja pesquisar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Employee> list = employeeRepository.findDateContractBigger(localDate);
        list.forEach(System.out::println);
    }

    private void searchEmployeeSalary() {
        List<EmployeeProjection> list = employeeRepository.findEmployeeSalary();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() + " | nome: " + f.getName() + " | salario: " + f.getSalary()));
    }

}