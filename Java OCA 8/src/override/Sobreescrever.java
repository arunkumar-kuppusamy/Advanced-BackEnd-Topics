package override;

public class Sobreescrever extends Car {

	public int gearRatio = 9;

	@Override
	public String accelerate() {
		return "Accelerate : SportsCar";
	}

	public static void main(String[] args) {
		
		Sobreescrever c1 = new Sobreescrever();
		System.out.println(c1.gearRatio + " " + c1.accelerate());
		//Main c2 = new Car();//Type mismatch: cannot convert from Car to Main
		//System.out.println(c2.gearRatio + " " + c2.accelerate());
		Car c3 = new Sobreescrever();
		System.out.println(c3.gearRatio + " " + c3.accelerate());
		Car c4 = new Car();
		System.out.println(c4.gearRatio + " " + c4.accelerate());
	}

}

class Car {
	public int gearRatio = 8;

	public String accelerate() {
		return "Accelerate : Car";
	}
}
