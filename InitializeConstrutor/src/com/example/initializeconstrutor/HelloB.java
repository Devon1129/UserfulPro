package com.example.initializeconstrutor;

public class HelloB extends HelloA {
	    public HelloB() {
	        System.out.println("HelloB");
	    }
	    
	    public HelloB(int num) {
	    	super(num);
	        System.out.println("HelloB " + num);
	    }
	    
	    { System.out.println("I'm B class"); }
	    
	    static { System.out.println("static B"); }
}
