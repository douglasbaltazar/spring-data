package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcService;
	private final CrudUnidadeTrabalhoService unidTrabService;
	private final RelatoriosService relatorioService;
	
	public SpringDataApplication(CrudCargoService cr, CrudUnidadeTrabalhoService unidTrabService, CrudFuncionarioService funcService, RelatoriosService relatorioService) {
		this.cargoService = cr;
		this.funcService = funcService;
		this.unidTrabService = unidTrabService;
		this.relatorioService = relatorioService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação voce quer executar?");
			System.out.println("0- Sair");
			System.out.println("1- Cargo");
			System.out.println("2- Unidade Trabalho");
			System.out.println("3- Funcionario");
			System.out.println("4- Relatorio");
			
			int action = scanner.nextInt();
			if(action == 1) {
				cargoService.inicial(scanner);
			} else if(action == 2) {
				unidTrabService.inicial(scanner);
			} else if(action == 3) {
				funcService.inicial(scanner);
			} else if(action == 4) {
				relatorioService.inicial(scanner);
			} else {
				system = false;
			}
		}
	}

}
