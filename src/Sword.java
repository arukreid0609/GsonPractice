
public class Sword {
	String name;
	int atk;
	
	public Sword(String name, int hp) {
		this.name = name;
		this.atk = hp;
	}

	@Override
	public String toString() {
		return "Sword [name=" + name + ", atk=" + atk + "]";
	}
}
