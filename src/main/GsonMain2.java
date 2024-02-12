package main;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gsonsave.GsonSave;

public class GsonMain2 {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		var g = new GsonSave("save.json");
		List<Human> list = new ArrayList<Human>();
		list.add(new Hero("勇者A",100));
		list.add(new Hero("ヒーローB",150));
		list.add(new Wizard("魔法使いaaaaa",50));
		Human h = new Hero("Yuusha",100);
//		Field[] field = Human.class.getDeclaredFields();
		Field[] field = h.getClass().getSuperclass().getDeclaredFields();
//		System.out.println(field);
//		System.out.println(h.getClass().getSuperclass().getSuperclass() instanceof Object);

		// 親クラスのフィールド含めて全て取得
		List<Field> fList = new ArrayList<>();
		Class<?> c = h.getClass();
		int count = 0;
		while(c != null) {
			if(c == Object.class)break;
			System.out.print(++count + ":");
			System.out.println(c);
			Field[] f = c.getDeclaredFields();
			fList.addAll(Arrays.asList(f));
			c = c.getSuperclass();
		}
//		System.out.println(fList.size());
//		for(Field fi : field) {
		for(Field fi : fList) {
//			fi.setAccessible(true);
			System.out.println(fi.getName());
//			System.out.println(fi.get(h));
		}

//		g.save(list);
//		list = g.loadHumans();
//		for(Human h:list) {
//			System.out.println(h);
//		}
	}

}
