package main;
import java.util.List;

import gsonsave.GsonSave;

public class GsonMain {

	public static void main(String[] args) {
		var g = new GsonSave<Human>("save.json");
		List<Human> list = g.loadList();

		for(Human h : list) {
			System.out.println(h);
		}
	}
}
