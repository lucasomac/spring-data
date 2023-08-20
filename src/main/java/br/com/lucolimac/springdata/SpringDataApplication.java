package br.com.lucolimac.springdata;

import br.com.lucolimac.springdata.service.CrudEmployeeService;
import br.com.lucolimac.springdata.service.CrudRoleService;
import br.com.lucolimac.springdata.service.CrudUnitWorkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
    private Boolean system = true;
    private final CrudRoleService crudRoleService;
    private final CrudEmployeeService crudEmployeeService;
    private final CrudUnitWorkService crudUnitWorkService;

    public SpringDataApplication(CrudRoleService crudRoleService, CrudEmployeeService crudEmployeeService, CrudUnitWorkService crudUnitWorkService) {
        this.crudRoleService = crudRoleService;
        this.crudEmployeeService = crudEmployeeService;
        this.crudUnitWorkService = crudUnitWorkService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (system) {
            System.out.println("Qual função deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Employee");
            System.out.println("3 - Unidade");
            System.out.println("4 - Relatorios");
            System.out.println("5 - Relatorio dinamico");

            int function = scanner.nextInt();

            switch (function) {
                case 1 -> crudRoleService.innit(scanner);
                case 2 -> crudEmployeeService.init(scanner);
                case 3 -> crudUnitWorkService.init(scanner);

//                case 4:
//                    relatoriosService.inicial(scanner);
//                    break;
//                case 5:
//                    relatorioFuncionarioDinamico.inicial(scanner);
//                    break;
                default -> {
                    System.out.println("Finalizando");
                    system = false;
                }
            }
        }
    }
}
