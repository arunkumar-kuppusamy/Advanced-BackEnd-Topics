package strings_Nuvem_Imutabilidade;


/*
 * String Builder vs StringBuffer.
 * 	1 - StringBuffer is synchronized, StringBuilder is not.
 * 	2 - Tem exatamente os mesmos métodos, com os mesmos retornos e parametros.
 *  3 - Muitas questões para confundir length com capacity
 *  	Lenght corta e elimina a string atual se for menor
 *  	Capacity apenas assegura haver espaço suficiente na memoria virtual, o que é muito dificil acontecer.
 */


class StringBuilder_Buffer_CapacidadeComprimento {

	public final static void main(String[] args) {
		
		/*
		 * 	CONSTRUTOR DA CLASSE
		    StringBuilder()
				Constructs a string builder with no characters in it and an initial capacity of 16 characters.
			StringBuilder(CharSequence seq)
				Constructs a string builder that contains the same characters as the specified CharSequence.
			StringBuilder(int capacity)
				Constructs a string builder with no characters in it and an initial capacity specified by the capacity argument.
			StringBuilder(String str)
				Constructs a string builder initialized to the contents of the specified string. 
		  
		 */
		StringBuilder builder = new StringBuilder();
		builder.append("StringBuilder class provides an API compatible with StringBuffer, but with no guarantee of synchronization.");
		builder.charAt(1);
		builder.delete(0, 1);
		builder.deleteCharAt(2);
		builder.indexOf("A");
		//builder.reverse();
		builder.replace(0, 3, "ROD");
		builder.substring(0, 1);
		builder.insert(0, "INSER");
		builder.lastIndexOf("B");
		builder.subSequence(0, 1);
		builder.lastIndexOf("X");
		builder.setLength(100);
		builder.setCharAt(0, 'A');//The character at the specified index is set to ch.
		
		/*
		 *  Muitas questões com relação a capacidade, size, já que diferente da classe String esta pode enlarge.
		 *  
		 */
		builder.trimToSize();//Attempts to reduce storage used for the character sequence.
		int l = builder.length();//Returns the length (character count).
		builder.setLength(300);//Returns VOID - Sets the length of the character sequence.
		int c = builder.capacity();//Returns the current capacity.
		builder.ensureCapacity(300);//Returns VOID - Ensures that the capacity is at least equal to the specified minimum.
		builder = null;
		
		
		/*StringBuffer buffer = new StringBuffer();
		buffer.append("StringBuilder class provides an API compatible with StringBuffer, but with no guarantee of synchronization.");
		buffer.length();
		buffer.setLength(100);
		buffer.capacity();
		buffer.charAt(0);
		buffer.delete(0, 3);//Removes the characters in a substring of this sequence.
		buffer.deleteCharAt(5);//Removes the char at the specified position in this sequence.
		buffer.ensureCapacity(300);
		buffer.indexOf("b");
		buffer.trimToSize();
		buffer.insert(0, "Rod");
		//buffer.reverse();
		buffer.replace(0, 2, "!");
		buffer.substring(0, 2);
		buffer.insert(0, "X");
		buffer.lastIndexOf("X");
		buffer.subSequence(0, 1);
		buffer.codePointAt(0);//Returns the character (Unicode code point) at the specified index.
		System.out.println(buffer.toString());
		buffer = null;*/
		
		
		StringBuilder comprimento = new StringBuilder("123 5678 0");
		comprimento.append("abcdefghijklmnopqrstuvwyxz");

		System.out.println("------------ SET LENGHT ------------------");
		System.out.println("length() :\t"+comprimento.length());
		System.out.println("toString() :\t["+comprimento.toString()+"]\n");

		comprimento.setLength(100);
		System.out.println("100 p/ length() :\t"+comprimento.length() + "\t--> Preencher o LENGHT com x espacos vazios. null characters ('\\u0000') ");
		System.out.println("toString() :\t["+comprimento.toString()+"]\n");

		comprimento.setLength(10);
		System.out.println("10 p/ length() :\t"+comprimento.length() + "\t--> PERDE QUALQUER VALOR, que exceda o comprimento maximo desta string.");
		System.out.println("toString() :\t["+comprimento.toString()+"]\n");

		comprimento.setLength(50);
		System.out.println("50 p/ length() :\t"+comprimento.length() + "\t--> Preencher o LENGHT com x espacos vazios.");
		System.out.println("toString() :\t["+comprimento.toString()+"]\n");

		StringBuilder capacidade = new StringBuilder("123 5678 0");		
		capacidade.append("abcdefghijklmnopqrstuvwyxz");
		System.out.println("------------ ENSURE CAPACITY ------------------");
		System.out.println("\t| Capacidade somente assegura, NAO elimina, NEM adiciona vazios.");
		System.out.println("\t| Se vc setar como 1000 sera mil sempre, mesmo que redefina para menos.");
		System.out.println("\t| Para diminuir a capacidade use TRIMTOSIZE!!!. ");
		System.out.println("capacity() :\t"+capacidade.capacity());
		System.out.println("toString() :\t["+capacidade.toString()+"]\n");

		capacidade.ensureCapacity(1000);
		System.out.println("100 p/ capacity() :\t"+capacidade.capacity());
		System.out.println("toString() :\t["+capacidade.toString()+"]\n");

		capacidade.ensureCapacity(10);
		System.out.println("10 p/ capacity() :\t"+capacidade.capacity());
		System.out.println("toString() :\t["+capacidade.toString()+"]\n");

		capacidade.ensureCapacity(50);
		System.out.println("50 p/ capacity() :\t"+capacidade.capacity());
		System.out.println("toString() :\t["+capacidade.toString()+"]\n");

		capacidade.trimToSize();
		System.out.println("trimToSize() capacity() :\t"+capacidade.capacity());
		System.out.println("toString() :\t["+capacidade.toString()+"]\n");
		
		capacidade.append("1234567890");
		System.out.println("novosAPPEND capacity() :\t"+capacidade.capacity()+"\t Novo APPEND faz aumentar de novo a capacidade.");
		System.out.println("toString() :\t["+capacidade.toString()+"]\n");
		
		StringBuilder trimToSize = new StringBuilder("123 5678 0");	
		trimToSize.append("aBbcdefghijklmnopqrstuvwyxz");
		System.out.println("------------ TRIM TO SIZE ------------------");
		System.out.println("capacity() :\t"+trimToSize.capacity());
		System.out.println("length() :\t"+trimToSize.length());
		System.out.println("toString() :\t["+trimToSize.toString()+"]\n");

		trimToSize.trimToSize();
		System.out.println("trimToSize() capacity() :\t"+trimToSize.capacity()+"\t Trim apenas corta a capacidade para reduzir memoria, nao mexe no comprimento.");
		System.out.println("trimToSize() length() :\t"+trimToSize.length());
		System.out.println("toString() :\t["+trimToSize.toString()+"]\n");

		//---- EXTRAS ------
		//Trick adicionar bem no meio da String
		trimToSize.insert(  trimToSize.length()/2  , "ADICIONAR BEM NO MEIO");
		System.out.println("toString() :\t["+trimToSize.toString()+"]\n");		

		//Trick para faz a mesma coisa que append
		trimToSize.insert(trimToSize.length(), "ADICIONAR AO FINAL");
		System.out.println("toString() :\t["+trimToSize.toString()+"]\n");
	}

}
  	 
    	 