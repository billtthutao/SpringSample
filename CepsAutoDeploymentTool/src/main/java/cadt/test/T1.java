package cadt.test;

import java.util.ArrayList;
import java.util.List;

public class T1 {
	private final String name;
	private final List<String> titles;
	
	public T1(String name, List<String> titles) {
		this.name = name;
		this.titles = titles;
	}
	
	public List<String> getTitles(){
		return titles;
	}
	public static void main(String[] args) {
		T1 t1 = new T1("hutao",new ArrayList());
		t1.getTitles().add("developer");
		System.out.println(t1.getTitles().toString());
	}
}
