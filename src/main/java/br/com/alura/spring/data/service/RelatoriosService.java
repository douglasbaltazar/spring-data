package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	private Boolean system = true;
	
	private final FuncionarioRepository funcRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosService(FuncionarioRepository funcRepository) {
		this.funcRepository = funcRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0- Sair");
			System.out.println("1- Busca funcionario nome");
			System.out.println("2- Busca funcionario nome salario e data");
			System.out.println("3- Busca funcionario data maior");
			System.out.println("4- Busca funcionario salario maior");
			
			int action = scanner.nextInt();
			
			switch(action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataMaior(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			
			default:
				system = false;
				break;
			}
		}
		
	}
	
	private void buscaFuncionarioDataMaior(Scanner scanner) {
		System.out.println("Digite a data");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		List<Funcionario> list = funcRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		System.out.println("Digite a data");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcRepository.FindNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() 
		+ " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
	}
	
}
