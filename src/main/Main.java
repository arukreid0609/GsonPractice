package main;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {


	// フィールドがクラス型かどうかの判定をして、
	// フィールドのクラス情報もGsonで保存出来ないか
	// 実験中
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Human hero = new Human();
		Field[] f = hero.getClass().getDeclaredFields();
		System.out.println(Arrays.toString(f));
		System.out.println(f.length);
		for(Field field : f) {
			Class<?> type = field.getType();
//			System.out.println(field.getType());
			System.out.println();
		}
	}
}
