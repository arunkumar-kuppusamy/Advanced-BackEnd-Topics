package interruptor;


/*
	Compreender quando o default sera executado
	Se break faz diferenca no uso do default
	Se tiver match ou nao tiver quando sera executado o default
*/
class Student { int marks = 10; }
public class SwtichDefaultRules {

	public static void main(String[] args) {

		Student s = new Student();
		switch (s.marks) {
			//default case executes IF ONLY NO matching value are found
			//Se o default estivesse logo apos um case match ele vai executa ate encontrar um break.
			default: System.out.print(" 100 ");
			case 10: System.out.print(" 10 ");
			case 98: System.out.println(" 98 "); 
		}
		
		switch (s.marks) {
			case 10: System.out.print(" 10 ");
			//Se o default estivesse logo apos um case match ele vai executa ate encontrar um break.
			default: System.out.print(" 100 ");
			//break na ultima instrucao nao faz diferenca nenhuma
			case 98: System.out.println(" 98 "); break; 
		}
		
		switch (s.marks) {
			case 10: System.out.print(" 10 ");
			//Se o default com break para igual nos cases;
			default: System.out.print(" 100 "); break;
			case 98: System.out.println(" 98 "); 
		}
		
	}

}
