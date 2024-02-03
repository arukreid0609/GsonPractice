import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
public class GsonInstant {
	Gson gson = new Gson();

	public void load() {
		String path = "save2.json";
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))){
			// Json文字列→JsonObject生成
			JsonObject jsonObject = gson.fromJson(br.readLine(),JsonObject.class);
			// classNameプロパティからクラス名を取得、クラス名からインスタンス生成
			String className = jsonObject.get("className").getAsString();
			Object obj = Class.forName(className).getDeclaredConstructor().newInstance();
			// 生成したインスタンス（obj）からクラスを取得してHumanインスタンスを生成（実体は子クラスのインスタンス）
			Human h = (Human)gson.fromJson(jsonObject, obj.getClass());
			System.out.println(h);
			System.out.println(h.getClass());
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}
	public void instantiate() {
		String path = "save2.json";
		Hero h = new Hero("佐々木",100);
		
		// Jsonのプロパティにクラス名追加
		Gson gson = new Gson();
		JsonElement json = gson.toJsonTree(h);
		JsonObject obj = json.getAsJsonObject();
		obj.addProperty("className",h.getClass().getName());
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
			// toJsonメソッドでインスタンスからJson文字列作成
			String jsontext = gson.toJson(obj);
			JsonArray j = new JsonArray();
			j.add(gson.toJsonTree(new Hero("佐々木",111)));
			j.add(gson.toJsonTree(new Hero("佐々",111)));
			JsonObject obj2 = new JsonObject();
			obj2.add("sasaki",j);

			// 保存
//			bw.write(jsontext);
			bw.write(gson.toJson(obj2));
		} catch (IOException e) {
			;
		}
	}
}
