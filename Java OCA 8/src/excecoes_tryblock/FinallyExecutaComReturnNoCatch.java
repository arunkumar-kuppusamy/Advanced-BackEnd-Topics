package excecoes_tryblock;

public class FinallyExecutaComReturnNoCatch {

	public static void main(String[] args) {

	/* RETURN DENTRO DE CATCH - FLUXO SE ALTERA.
		1 - encontrou return dentro do cath
		2 - vai para o finally
		3 - volta para o cath e executa o return;
		4 - sai do metodo, sem executar algo fora do try
		
		finally somente nao executa em caso de java.lang.System.exit(0);
	 */
		int a = 10;
		String name = null;
		try {
			a = name.length(); //line1
			a++; //line2
		} catch (NullPointerException e) {//entra aqui
			++a;//10+1
			return;
		} catch (RuntimeException e) {//nao entra nesse catch
			a--;
			java.lang.System.exit(0);
		} finally {
			System.out.println(a);//IMPRIME 10+1 e volta para o catch
		}

		System.out.println("Return foi chamado.");
	}

}
