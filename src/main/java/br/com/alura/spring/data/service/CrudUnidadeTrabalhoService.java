package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	private final UnidadeTrabalhoRepository cR;
	private Boolean system = true;
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository cR) {
		this.cR = cR;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Unidade Trabalho deseja executar?");
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
		System.out.println("Descrição do UnidadeTrabalho");
		String descricao = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		cR.save(unidadeTrabalho);
		System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		System.out.println("Descrição do UnidadeTrabalho");
		String descricao = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		cR.save(unidadeTrabalho);
		System.out.println(id + " atualizado com sucesso!");
		
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadeTrabalhos = cR.findAll();
		unidadeTrabalhos.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho.toString()));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		cR.deleteById(id);
		System.out.println("Deletado");
	}
}
