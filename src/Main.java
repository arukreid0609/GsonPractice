import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		Hero hero = new Hero("勇者",100);
//		save(hero);
//		Hero hero = load();
		System.out.println(hero);
	}
	
	// 書き込み
	public static void save(Hero h) {
		// save.jsonファイルにJson文字列を保存
		String path = "save.json";
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"))){
			// toJsonメソッドでインスタンスからJson文字列作成
			Gson gson = new Gson();
			String json = gson.toJson(h);

			// 保存
			bw.write(json);
		}catch(IOException e) {
			;
		}
	}
	
	// 読み込み
	public static Hero load(){
		// 戻り値用にHero型変数hを宣言
		Hero h = null;
		
		// save.jsonからJson文字列を読み込む
		String path = "save.json";
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))){
			String json = br.readLine();

			// 読み込んだJson文字列からインスタンス生成
			Gson gson = new Gson();
			h = gson.fromJson(json, Hero.class);
		}catch(IOException e) {
			System.out.println("ファイル読み込みに失敗しました");
		}
		return h;
	}
	// 読み込み
	public static Hero load(boolean b){
		// 戻り値用にHero型変数hを宣言
		Hero h = null;
		
		// save.jsonからJson文字列を読み込む
		String path = "save.json";
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))){
			String json = br.readLine();

			// 読み込んだJson文字列からインスタンス生成
			Gson gson = new Gson();
			Object obj = Class.forName("Hero");

			h = gson.fromJson(json,h.getClass());
		}catch(IOException e) {
			System.out.println("ファイル読み込みに失敗しました");
		}catch(Exception e) {
			
		}
		return h;
	}
}
