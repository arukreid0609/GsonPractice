
public abstract class Human {
	String name;
	int hp;
	Sword sword;
	
	public Human() {
		
	}

	public Human(String name,int hp) {
		this.name = name;
		this.hp = hp;
		this.sword = new Sword("剣",50);
	}
}
