package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository fR;
	private final CargoRepository cargoR;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	public CrudFuncionarioService(FuncionarioRepository fR, CargoRepository cargoR, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.fR = fR;
		this.cargoR = cargoR;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de funcionaro deseja executar?");
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
		System.out.println("Digite o nome");
		String nome = scanner.next();
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a data da contratacao");
		String dataContratacao = scanner.next();
		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scanner);
		Funcionario func = new Funcionario();
		func.setNome(nome);
		func.setCpf(cpf);
		func.setSalario(salario);
		func.setDatacontratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoR.findById(cargoId);
		func.setCargo(cargo.get());
		func.setUnidadeTrabalho(unidades);
		fR.save(func);
		System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		System.out.println("Digite o nome");
		String nome = scanner.next();
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a data da contratacao");
		String dataContratacao = scanner.next();
		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scanner);
		Funcionario func = new Funcionario();
		func.setNome(nome);
		func.setCpf(cpf);
		func.setSalario(salario);
		func.setDatacontratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoR.findById(cargoId);
		func.setCargo(cargo.get());
		func.setUnidadeTrabalho(unidades);
		fR.save(func);
		System.out.println(id + " atualizado com sucesso!");
		
	}
	
	private void visualizar() {
		Iterable<Funcionario> funcionarios = fR.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		fR.deleteById(id);
		System.out.println("Deletado");
	}
	
	private List<UnidadeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite o unidadeId ( para sair digite 0 )");
			Integer unidadeId = scanner.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}
}
