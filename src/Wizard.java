
public class Wizard extends Human{
	public Wizard() {
		
	}
	
	public Wizard(String name, int hp) {
		super(name,hp);
	}

	@Override
	public String toString() {
		return "Wizard [name=" + name + ", hp=" + hp + ", sword=" + sword + "]";
	}
	
	
}
