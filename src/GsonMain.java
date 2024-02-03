import java.util.ArrayList;
import java.util.List;

public class GsonMain {

	public static void main(String[] args) {
		var g = new GsonInstant();
		List<Human> list = new ArrayList<Human>();
		list.add(new Hero("勇者",100));
		list.add(new Hero("ヒーロー",150));
		list.add(new Wizard("魔法使いaaaaa",50));

		g.save(list);
//		list = g.loadHumans();
//		for(Human h:list) {
//			System.out.println(h);
//		}
	}

}
