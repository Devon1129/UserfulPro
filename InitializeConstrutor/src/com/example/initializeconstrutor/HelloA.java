package com.example.initializeconstrutor;

public class HelloA {
	private int a = 0;
	public HelloA() {
        System.out.println("HelloA");
    }
    
	public HelloA(int num) {	
        System.out.println("HelloA " + num);
    }
	
	// Initialer block: The Java compiler copies initializer blocks into every constructor.
    { System.out.println("I'm A class"); }
    
    
    // static Initialer block
    static { System.out.println("static A"); }
    
    public static void main(String[] args) {
    	new HelloB(); 
    	new HelloB(10); 

    }
}
/*
 * 輸出:
 * static A
 * static B
 * I'm A class
 * HelloA
 * I'm B class
 * HelloB
 * I'm A class
 * HelloA 10
 * I'm B class
 * HelloB 10
 */

//MyNote:static 會先被 load進來，將下來進入 class內 initializer成員，再來才會到 constructor，執行 constructor. 

//https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
/*
 * Initializing Instance Members
 * The Java compiler copies initializer blocks into every constructor. 
 */

