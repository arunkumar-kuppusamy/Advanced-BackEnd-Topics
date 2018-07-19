package ocp;

public abstract class HashCodeReimplementar {

	abstract String abstrato() throws RuntimeException;

	private static String name;

	public static void main(String[] args) {
		System.out.println(name);
		name = "Rodrigo";
		System.out.println(name);
		System.out.println(name.hashCode());
	}

	//Idea from effective Java : Item 9
    @Override
    public int hashCode() {
    	return 31 * 17 + name.hashCode();
    }
    /*@Override
    public int hashCode() {
        return Objects.hash(name);
    }*/
	/*@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(name).toHashCode();
	}*/
}
