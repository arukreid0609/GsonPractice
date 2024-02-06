package main;
public class Hero extends Human{
	
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
