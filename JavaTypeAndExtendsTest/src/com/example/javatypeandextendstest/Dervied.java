package com.example.javatypeandextendstest;

public class Dervied extends Base{
	private String name = "dervied";

    public Dervied() {
        /*
         * 這裡 override 掉父類別的 method，
         * 因此不會call到 父類別的 method，
         * 第一次會 load父類別，
         * 才回到子類別初始成員，
         * 因此，第一次輸出的 name是 null.
         */
    	tellName();
        printName();
    }
    
    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }
    
    public void printName() {
        System.out.println("Dervied print name: " + name);
    }

    public static void main(String[] args){
    	new Dervied();    
    }
    
}

