package com.clazz.hakan.mainclazz;

import com.clazz.hakan.interFace.PeopleInterface;

public class People implements PeopleInterface {

	public void create() {
		String name1 = namee;
		String surname2 = surname.toString().toUpperCase();
		System.out.println(name1 + " " + surname2);

	}

	public static void main(String[] args) {
		People p = new People();
		p.create();
	}
}
