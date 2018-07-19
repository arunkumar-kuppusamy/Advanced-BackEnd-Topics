package simple;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


interface RepositorioDePessoas {
	List<Pessoa> getPessoas();
}

public class PessoaTest {

	@Test
	public void main() {
		
		Pessoa pessoa = new Pessoa("Rodrigo Cirino", 30);
		
		try {
			RepositorioDePessoas repositorio = mock(RepositorioDePessoas.class);
			when(repositorio.getPessoas()).thenReturn(Arrays.asList(pessoa));
			System.out.println(repositorio.getPessoas().get(0).getNome());			
		}catch(Exception e) {	
			e.printStackTrace();
		}
	}

	
	
}

class Pessoa {

	private RepositorioDePessoas pessoas;
	private String nome;
	private int idade;
	
	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
	public Pessoa(RepositorioDePessoas pessoas, String nome, int idade) {
		this(nome, idade);
		this.pessoas = pessoas;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

}