package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {

	public static void main(String[] args) {
//		Hero hero = new Hero("勇者", 100);
		//		save(hero);
				Hero hero = load();
				System.out.println(hero);
//		p();
		//		save(hero);
		//		saveJson(hero);
	}

	// 書き込み
	public static void save(Hero h) {
		// save.jsonファイルにJson文字列を保存
		String path = "save.json";
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
			// toJsonメソッドでインスタンスからJson文字列作成
			Gson gson = new Gson();
			String json = gson.toJson(h);
			// 保存
			bw.write(json);
		} catch (IOException e) {
			;
		}
	}

	// 読み込み
	public static Hero load() {
		// 戻り値用にHero型変数hを宣言
		Hero h = null;

		// save.jsonからJson文字列を読み込む
		String path = "save.json";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String json = br.readLine();

			// 読み込んだJson文字列からインスタンス生成
			Gson gson = new Gson();
						h = gson.fromJson(json, Hero.class);
		} catch (IOException e) {
			System.out.println("ファイル読み込みに失敗しました");
		}
		return h;
	}

	// 読み込み
	public static Hero load(boolean b) {
		// 戻り値用にHero型変数hを宣言
		Hero h = null;

		// save.jsonからJson文字列を読み込む
		String path = "save.json";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String json = br.readLine();

			// 読み込んだJson文字列からインスタンス生成
			Gson gson = new Gson();

			h = gson.fromJson(json, h.getClass());
		} catch (IOException e) {
			System.out.println("ファイル読み込みに失敗しました");
		} catch (Exception e) {
			;
		}
		return h;
	}

	public static void saveJson(Hero h) {
		String path = "save.json";
		Gson gson = new Gson();
		String json = null;
		try {
			Object obj = Class.forName("Hero").getDeclaredConstructor().newInstance();
			JsonObject jobj = gson.fromJson(json, JsonObject.class);
			String type = jobj.get("className").getAsString();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public static void p() {
		try {
			// 文字列からクラスを取得
			Class<?> c = Class.forName("Hero");
			// 取得したクラスのデフォルトコンストラクタを利用してインスタンス生成
			Object h = c.getDeclaredConstructor().newInstance();
			Human a = (Human) new Gson().fromJson("", h.getClass());
			System.out.println(h.getClass());
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
