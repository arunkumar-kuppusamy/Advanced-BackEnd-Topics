package oca;

public class TestClass2 {

	public static void main(String[] args) {

		
		String s[] = new String[10];
		for (String string : s) {
			//System.out.println(string);
		}

		float f[] = new float[10];
		for (Float string : f) {
		//	System.out.println(string.floatValue());
		}
		
		char c[] = new char[10];
		for (Character string : c) {
			System.out.println( String.format("\\u%04x",(int)string) );
		}
		
		
	}

}
