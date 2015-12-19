package com.example.genericstest;

public class Main {
	public static void main(String[] args){
		//1.泛型放入定義格式
		//裡面的 var type為 String
//		Test<String> p = new Test<String>();
//		p.setVar("泛型-----字符串");
//		System.out.println("獲取泛型內容-----" + p.getVar().length() + "-----得到字符串的長度");
//		
//		Test<Integer> p1 = new Test<Integer>();
//		p1.setVar(120);
//		System.out.println("獲取泛型內容-----" + p1.getVar() + "-----得到整數數據的長度");
//		
//		Test<Float> p2 = new Test<Float>();
//		p2.setVar(13.24f);
//		System.out.println("獲取泛型內容-----" + p2.getVar() + "-----得到浮點數數據的長度");
		
		
		//2.Constructor method 中使用泛型
//		Test<String> p1 = new Test<String>("I am A 泛型 constructor_Test");
//		System.out.println("泛型的 constructor method-----" + p1.getVar());
		
		//3.泛型指定多個 type的定義
		Test<String, Integer> p = new Test<String, Integer>();
		p.setKey("字符串 type數據");
		p.setValues(123);
		System.out.println("泛型的多 type定義實例-----" + p.getKey());
		System.out.println("泛型的多 type定義實例-----" + p.getValues());
		
	}
}

//3.
class Test<K, V>{
	private K key;
	public V values;
	
	public K getKey(){
		return key;
	}
	
	public void setKey(K key){
		this.key = key;
	}
	
	public V getValues(){
		return values;
	}
	
	public void setValues(V values){
		this.values = values;
	}
}

////2.
//class Test<T>{
//	private T var;
//	
//	//泛型的 constructor
//	public Test(T var){
//		super();
//		this.var = var;
//	}
//	
//	public T getVar(){
//		return var;
//	}
//	
//	public void setVar(T var){
//		this.var = var;
//	}
//}


//1.
////T是 type的簡稱
//class Test<T>{
//	//var的 type由外部指定，即由 T指定
//	private T var;
//	
//	public T getVar(){
//		//返回值 type由外部決定
//		return var;
//	}
//	
//	//設置 type由外部決定
//	public void setVar(T var){
//		this.var = var;
//	}
//}
