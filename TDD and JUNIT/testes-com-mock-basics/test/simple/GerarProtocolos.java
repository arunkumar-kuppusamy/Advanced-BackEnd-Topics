package simple;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

interface RepositorioDeProtocolos {
	List<Protocolos> getProtocolos();
	String getUniqueProtocolo();
	void salva(Protocolos capture);
	void setaProtocolos(String s);
}

public class GerarProtocolos {

	//@Mock 
	//static RepositorioDeProtocolos rep;
	
	//@Test
	public static void main(String[] args) {
		
		//add the behavior of calc service to add two numbers
		//when(rep.getUniqueProtocolo()).thenReturn("b");
			
		//test the add functionality
		//Assert.assertEquals(rep.getUniqueProtocolo(),"b");
		
		Protocolos protocolos = new Protocolos("0215489745643210216545631");
		//----------------------------------------------------------------------------
		try {
			RepositorioDeProtocolos repositorio = mock(RepositorioDeProtocolos.class);
			when(repositorio.getProtocolos()).thenReturn(Arrays.asList(protocolos));//Alteramos o mock para assumir um novo valor

			//when(repositorio.getProtocolos()).thenThrow(new IllegalArgumentException("Sistema Indisponível para protocolos"));
			//when(protocolos.getNumero()).thenCallRealMethod();
			//doReturn("42").when(repositorio.getProtocolos().get(0));
			//(protocolos.getVoidProtocolos()).thenCallRealMethod();
			
			ArgumentCaptor<Protocolos> capture = ArgumentCaptor.forClass(Protocolos.class);
			ArgumentCaptor<Protocolos> argument = ArgumentCaptor.forClass(Protocolos.class);
			//verify(repositorio).salva(capture.capture());
			//verify(repositorio, never()).getProtocolos();
			//Protocolos protocoloGerado = capture.getValue();
			
		    //verify(repositorio).salva(argument.capture());
		    //assertEquals("John", argument.getValue().getNumero());
		    
			for (Protocolos proc : repositorio.getProtocolos()) {
				System.out.println(proc.getNumero());
			}
			
			//assertEquals("123456", protocoloGerado.getNumero());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//----------------------------------------------------------------------------
	}

}

class Protocolos {

	private RepositorioDeProtocolos protocolos;
	private String numero;

	public Protocolos(String numero) {
		this.setNumero(numero);
	}

	public Protocolos(RepositorioDeProtocolos protocolos, String numero) {
		this.protocolos = protocolos;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void getVoidProtocolos() {
		System.out.println("Mockando valores.");
	}
	
}