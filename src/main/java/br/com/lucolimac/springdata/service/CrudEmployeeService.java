package br.com.lucolimac.springdata.service;

import br.com.lucolimac.springdata.orm.Employee;
import br.com.lucolimac.springdata.orm.Role;
import br.com.lucolimac.springdata.orm.UnitWork;
import br.com.lucolimac.springdata.repository.EmployeeRepository;
import br.com.lucolimac.springdata.repository.RoleRepository;
import br.com.lucolimac.springdata.repository.UnitWorkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudEmployeeService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;
    private final UnitWorkRepository unitWorkRepository;

    public CrudEmployeeService(RoleRepository roleRepository, EmployeeRepository employeeRepository, UnitWorkRepository unitWorkRepository) {
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.unitWorkRepository = unitWorkRepository;
    }


    public void init(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de funcionario deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1 -> save(scanner);
                case 2 -> update(scanner);
                case 3 -> view(scanner);
                case 4 -> delete(scanner);
                default -> system = false;
            }

        }

    }

    private void save(Scanner scanner) {
        System.out.println("Digite o nome");
        String name = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salary = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContract = scanner.next();

        System.out.println("Digite o cargoId");
        Long roleId = scanner.nextLong();

        List<UnitWork> units = unit(scanner);

        Employee employee = new Employee();
        setFields(name, cpf, salary, dataContract, roleId, employee);
        employee.setUnitsWork(units);

        employeeRepository.save(employee);
        System.out.println("Salvo");
    }

    private List<UnitWork> unit(Scanner scanner) {
        boolean isTrue = true;
        List<UnitWork> units = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            long unitWorkId = scanner.nextLong();

            if (unitWorkId != 0) {
                Optional<UnitWork> unitWork = unitWorkRepository.findById(unitWorkId);
                units.add(unitWork.get());
            } else {
                isTrue = false;
            }
        }

        return units;
    }

    private void update(Scanner scanner) {
        System.out.println("Digite o id");
        Long id = scanner.nextLong();

        System.out.println("Digite o nome");
        String name = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salary = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContract = scanner.next();

        System.out.println("Digite o cargoId");
        Long roleId = scanner.nextLong();

        Employee employee = new Employee();
        employee.setId(id);
        setFields(name, cpf, salary, dataContract, roleId, employee);

        employeeRepository.save(employee);
        System.out.println("Alterado");
    }

    private void setFields(String name, String cpf, Double salary, String dataContract, Long roleId, Employee employee) {
        employee.setName(name);
        employee.setCpf(cpf);
        employee.setSalary(salary);
        employee.setDataContract(LocalDate.parse(dataContract, formatter));
        Optional<Role> role = roleRepository.findById(roleId);
        employee.setRole(role.get());
    }

    private void view(Scanner scanner) {
        System.out.println("Qual pagina voce deseja visualizar");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "name"));
        Page<Employee> employees = employeeRepository.findAll(pageable);

        System.out.println(employees);
        System.out.println("Pagina atual " + employees.getNumber());
        System.out.println("Total elemento " + employees.getTotalElements());
        employees.forEach(System.out::println);
    }

    private void delete(Scanner scanner) {
        System.out.println("Id");
        long id = scanner.nextLong();
        employeeRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
