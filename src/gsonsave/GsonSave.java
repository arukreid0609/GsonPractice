package gsonsave;
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

public class GsonSave<T> {
	private Gson gson = new Gson();
	private String path;

	public GsonSave(String path) {
		if(path.matches("[a-zA-Z0-9/]{1,}.json")) {
			this.path = path;
		}else {
			throw new IllegalArgumentException("ファイル名は半角英数字、末尾は.jsonのみ有効");
		}
	}

	public T load() {
		T obj = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String json = br.readLine();
			// Json文字列→インスタンス生成
			obj = (T) getInstanceFromJson(json);
			System.out.println("読み込み完了しました");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("読み込みに失敗しました");
		}
		return obj;
	}

	public List<T> loadList() {
		List<T> list = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String json = br.readLine();
			// Json文字列→インスタンス生成
			JsonObject object = gson.fromJson(json, JsonObject.class);
			JsonArray array = object.get("datas").getAsJsonArray();
			list = new ArrayList<T>();
			for(JsonElement element:array) {
				T instance = (T)getInstanceFromJson(element);
				list.add(instance);
			}
			System.out.println("読み込み完了しました");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("読み込みに失敗しました");
		}
		return list;
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
	private JsonObject toJsonObjects(List<?> list) {
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

	public void save(List<?> list) {
		JsonObject json = toJsonObjects(list);
		save(json);
	}
}
