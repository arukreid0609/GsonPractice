package main;
import java.util.ArrayList;
import java.util.List;

import gsonsave.GsonSave;

public class GsonMain2 {

	public static void main(String[] args) {
		var g = new GsonSave("save.json");
		List<Human> list = new ArrayList<Human>();
		list.add(new Hero("勇者A",100));
		list.add(new Hero("ヒーローB",150));
		list.add(new Wizard("魔法使いaaaaa",50));

		g.save(list);
//		list = g.loadHumans();
//		for(Human h:list) {
//			System.out.println(h);
//		}
	}

}