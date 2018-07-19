package oca;

class Phone {
	static void call() {
		System.out.println("Call-Phone 1");
	}
	void call2() {
		System.out.println("Call-Phone 2");
	}
}

class SmartPhone extends Phone {
	static void call() {
		System.out.println("Call-SmartPhone 1");
	}
	void call2() {
		System.out.println("Call-SmartPhone 2");
	}
}

class Diferenca_AcessoEstatico_NaoEstatico {
	public static void main(String... args) {
		Phone phone = new Phone();
		Phone smartPhone = new SmartPhone();

		//Repare que em metodos estaticos, 
		//Eclipse até indica usar o nome da classe Phone no lugar das variaveis
		phone.call();//The static method call() from the type Phone should be accessed in a static way
		smartPhone.call();

		//Porem em metodos non-statics ele não indica de forma alguma.
		phone.call2();
		smartPhone.call2();
	}
}