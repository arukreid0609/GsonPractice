
public abstract class Human {
	protected String className;
	public Human() {
		this.className = this.getClass().getName();
	}
}
