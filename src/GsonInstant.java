import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonInstant {
	private Gson gson = new Gson();
	private String path = "save2.json";

	public Human loadHuman() {
		Human h = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String json = br.readLine();
			// Json文字列→インスタンス生成
			h = (Human) getInstanceFromJson(json);
			System.out.println("読み込み完了しました");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("読み込みに失敗しました");
		}
		return h;
	}

	public List<Human> loadHumans() {
		List<Human> h = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String json = br.readLine();
			// Json文字列→インスタンス生成
			JsonObject o = gson.fromJson(json, JsonObject.class);
			JsonArray array = o.get("datas").getAsJsonArray();
			h = new ArrayList<Human>();
			for(JsonElement e:array) {
				Human human = (Human)getInstanceFromJson(e);
				h.add((Human)getInstanceFromJson(e));
			}
			System.out.println("読み込み完了しました");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("読み込みに失敗しました");
		}
		return h;
	}

	private Object getInstanceFromJson(String json)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		// Json文字列→JsonObject生成
		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		// classNameプロパティからクラス名を取得、クラス名からインスタンス生成
		String className = jsonObject.get("className").getAsString();
		Object obj = Class.forName(className).getDeclaredConstructor().newInstance();
		// JsonObjectからインスタンス生成（子クラスのインスタンス）
		return gson.fromJson(jsonObject, obj.getClass());
	}

	private Object getInstanceFromJson(JsonElement json)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		return getInstanceFromJson(gson.toJson(json));
	}


	// インスタンスをJsonObjectに変換する（プロパティにクラス名も追加）
	private JsonObject toJsonObject(Object obj) {
		JsonObject jObj = gson.toJsonTree(obj).getAsJsonObject();
		jObj.addProperty("className", obj.getClass().getName());
		return jObj;
	}

	// 複数のインスタンスをJsonObjectに変換
	private JsonObject toJsonObjects(List list) {
		JsonObject json = new JsonObject();
		JsonArray array = new JsonArray();
		json.add("datas", array);
		for (Object o : list) {
			array.add(toJsonObject(o));
		}
		return json;
	}

	// Jsonファイルへの保存
	private void save(JsonObject json) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
			bw.write(gson.toJson(json));
		} catch (IOException e) {
			;
		}
	}

	public void save(Object obj) {
		JsonObject json = toJsonObject(obj);
		save(json);
	}

	public void save(List list) {
		JsonObject json = toJsonObjects(list);
		save(json);
	}
}
