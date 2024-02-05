package main;
import gsonsave.GsonSave;

public class GsonMain2 {

	public static void main(String[] args) {
		GsonSave<Human> g = new GsonSave<Human>("save.json");
		Hero hero = new Hero("勇者A",100);

		g.save(hero);
//		list = g.loadHumans();
//		for(Human h:list) {
//			System.out.println(h);
//		}
	}

}
