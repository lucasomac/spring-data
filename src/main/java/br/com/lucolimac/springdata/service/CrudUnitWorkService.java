package br.com.lucolimac.springdata.service;

import br.com.lucolimac.springdata.orm.UnitWork;
import br.com.lucolimac.springdata.repository.UnitWorkRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnitWorkService {

	private Boolean system = true;
	private final UnitWorkRepository unitWorkRepository;

	public CrudUnitWorkService(UnitWorkRepository unitWorkRepository) {
		this.unitWorkRepository = unitWorkRepository;
	}


	public void init(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de unidade deseja executar");
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
		System.out.println("Digite o nome da unidade");
        String nome = scanner.next();

        System.out.println("Digite o endereco");
        String address = scanner.next();

        UnitWork unitWork = new UnitWork();
        unitWork.setDescription(nome);
        unitWork.setAddress(address);

        unitWorkRepository.save(unitWork);
        System.out.println("Salvo");
	}
	
	private void update(Scanner scanner) {
		System.out.println("Digite o id");
        Long id = scanner.nextLong();

        System.out.println("Digite o nome da unidade");
        String nome = scanner.next();

        System.out.println("Digite o endereco");
        String address = scanner.next();

        UnitWork unitWork = new UnitWork();
        unitWork.setId(id);
        unitWork.setDescription(nome);
        unitWork.setAddress(address);

        unitWorkRepository.save(unitWork);
        System.out.println("Alterado");
	}
	
	private void view() {
		Iterable<UnitWork> units = unitWorkRepository.findAll();
		units.forEach(System.out::println);
	}
	
	private void delete(Scanner scanner) {
		System.out.println("Id");
		long id = scanner.nextInt();
		unitWorkRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
}
