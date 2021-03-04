package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	private final CargoRepository cR;
	private Boolean system = true;
	public CrudCargoService(CargoRepository cR) {
		this.cR = cR;
	}
	
	public void inicial(Scanner scanner) {
		while(system = true) {
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0- Sair");
			System.out.println("1- Salvar");
			System.out.println("2- Atualizar");
			System.out.println("3- Visualizar todos");
			System.out.println("4- Deletar");
			
			int action = scanner.nextInt();
			
			switch(action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
		
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cR.save(cargo);
		System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		System.out.println("Descrição do Cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cR.save(cargo);
		System.out.println(id + " atualizado com sucesso!");
		
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cR.findAll();
		cargos.forEach(cargo -> System.out.println(cargo.toString()));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		cR.deleteById(id);
		System.out.println("Deletado");
	}
}
