package strings_Nuvem_Imutabilidade;

public class StringsComentariosScape {

	public static void main(String[] args) {

		int age = /* 25 */ 74;
		String name = "e" + "Ja /*va*/ v";
		String course = //this is a comment
		"eJava";

		System.out.println(age);//74
		System.out.println(name);//eJa /*va*/ v
		System.out.println(course);//eJava


	}

}
