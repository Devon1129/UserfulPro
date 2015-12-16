package com.example.javatypeandextendstest;

public class ByValueTest {
	/*
	 * @ description Java中沒有 reference傳遞 只有values傳遞 
	 */
    public static void main(String[] args) {
       
    	//建立並 construct兩個 object.
        Person p1 = new Person("Alexia", "female");
        Person p2 = new Person("Edward", "male");

        System.out.println("對象交換前：p1 = " + p1.toString());
        System.out.println("對象交換前：p2 = " + p2.toString());
        
       //交換p1對象和p2對象
       Person.swapObject(p1, p2);
       //從交換結果中看出，實際對象並未交換
       System.out.println("\n對象交換後：p1 = " + p1.toString());
       System.out.println("對象交換後：p2 = " + p2.toString());

       //建立兩個對象 Array
       Person[] arraya = new Person[2];
       Person[] arrayb = new Person[2];

       //分別 construct Array對象
       arraya[0] = new Person("Alexia", "female");
       arraya[1] = new Person("Edward", "male");
       
       arrayb[0] = new Person("jmwang", "female");
       arrayb[1] = new Person("hwu", "male");

        System.out.println('\n' + "對象Array交換前：arraya[0] = "
                + arraya[0].toString() + ", arraya[1] = "
                + arraya[1].toString());
        System.out.println("對象Array交換前：arrayb[0] = "
                + arrayb[0].toString() + ", arrayb[1] = "
                + arrayb[1].toString());
        
        //交換這兩個對象 Array
        Person.swapObjectArray(arraya, arrayb);
        System.out.println('\n' + "對象Array交換後：arraya[0] = "
                + arraya[0].toString() + ", arraya[1] = "
                + arraya[1].toString());
        System.out.println("對象Array交換後：arrayb[0] = "
                + arrayb[0].toString() + ", arrayb[1] = "
                + arrayb[1].toString());

       //建立兩個普通 Array
       int[] a = new int[2];
       int[] b = new int[2];

       //給 Array各元素賦值 
       for (int i = 0; i < a.length; i++) {
            a[i] = i;
            b[i] = i + 1;
        }

       System.out.println('\n' + "基本type Array交換前：a[0] = " + a[0] + ", a[1] = " + a[1]);
       System.out.println("基本type Array交換前：b[0] = " + b[0] + ", b[1] = " + b[1]);

       //交換兩個基本 type Array
       Person.swapIntArray(a, b);
       System.out.println('\n' + "基本type Array交換後：a[0] = " + a[0] + ", a[1] = " + a[1]);
       System.out.println("基本type Array交換後：b[0] = " + b[0] + ", b[1] = " + b[1]);
        
       //改變對象 Array的內容
       Person.changeObjectArray(arraya, arrayb);
       System.out.println('\n' + "對象Array 內容交換並改變後：arraya[1] = " + arraya[1].toString());
       System.out.println("對象Array 內容交換並改變後：arrayb[1] = " + arrayb[1].toString());
        
       //改變基本 type Array的內容
       Person.changeIntArray(a, b);
       System.out.println('\n' + "基本type Array內容交換並改變後：a[1] = " + a[1]);
       System.out.println("基本type Array內容交換並改變後：b[1] = " + b[1]);
    }
}
class Person {

    private String name;

    private String sex;

    public Person(String x, String y) {
        this.name = x;
        this.sex = y;
    }

    //重寫 toString()，方便輸出
    public String toString() {

        return name + " " + sex;
    }

    //交換對象引用
    public static void swapObject(Person p1, Person p2) {
        Person tmp = p1;
        p1 = p2;
        p2 = tmp;
    }

    //交換基本 type
    public static void swapInt(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    //交換對象Array
    public static void swapObjectArray(Person[] p1, Person[] p2) {
        Person[] tmp = p1;
        p1 = p2;
        p2 = tmp;
    }

   //交換基本 type Array
   public static void swapIntArray(int[] x, int[] y) {
        int[] tmp = x;
        x = y;
        y = tmp;
    }

   //改變對象 Array中的內容
   public static void changeObjectArray(Person[] p1, Person[] p2) {
        Person tmp = p1[1];
        p1[1] = p2[1];
        p2[1] = tmp;
        
 
        Person p = new Person("wjl", "male");
        p1[1] = p;//傳出去的值有改變
    }

   //改變基本 type Array中的內容
   public static void changeIntArray(int[] x, int[] y) {
        int tmp = x[1];
        x[1] = y[1];
        y[1] = tmp;

        x[1] = 5;//傳出去的值有改變
    }
}


