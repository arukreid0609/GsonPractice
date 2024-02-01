public class Hero {
	private String className;
	String name;
	int hp;
	Sword sword;
	
	public Hero(String name,int hp) {
		this.className = this.getClass().getName();
		this.name = name;
		this.hp = hp;
		this.sword = new Sword("剣",50);
	}

	@Override
	public String toString() {
		return "Hero [className=" + className + ", name=" + name + ", hp=" + hp + ", sword=" + sword + "]";
	}

	
}
