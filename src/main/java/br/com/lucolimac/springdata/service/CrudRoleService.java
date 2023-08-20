package br.com.lucolimac.springdata.service;

import br.com.lucolimac.springdata.orm.Role;
import br.com.lucolimac.springdata.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudRoleService {
    private Boolean system = true;
    private final RoleRepository roleRepository;

    public CrudRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void innit(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1 -> save(scanner);
                case 2 -> update(scanner);
                case 3 -> view();
                case 4 -> delete(scanner);
                default -> system = false;
            }

        }

    }

    private void save(Scanner scanner) {
        System.out.println("Descricao do cargo");
        String description = scanner.next();
        Role role = new Role();
        role.setDescription(description);
        roleRepository.save(role);
        System.out.println("Salvo");
    }

    private void update(Scanner scanner) {
        System.out.println("Id");
        long id = scanner.nextInt();
        System.out.println("Descricao do Cargo");
        String description = scanner.next();
        Role role = new Role();
        role.setId(id);
        role.setDescription(description);
        roleRepository.save(role);
        System.out.println("Atualizado");
    }

    private void view() {
        Iterable<Role> cargos = roleRepository.findAll();
        cargos.forEach(System.out::println);
    }

    private void delete(Scanner scanner) {
        System.out.println("Id");
        long id = scanner.nextInt();
        roleRepository.deleteById(id);
        System.out.println("Deletado");
    }
}
