package main;
public class Hero extends Human{
	String a;
	
	public Hero() {
		
	}
	public Hero(String name,int hp) {
		this.name = name;
		this.hp = hp;
		this.sword = new Sword("剣",50);
	}

	@Override
	public String toString() {
		return "Hero [name=" + name + ", hp=" + hp + ", sword=" + sword + "]";
	}
}
