
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [四、多线程（SE13-SE14）](#四-多线程se13-se14)
  - [SE13](#se13)
  - [4.1 多线程概述](#41-多线程概述)
  - [4.2 创建多线程](#42-创建多线程)
  - [4.3 线程的总结](#43-线程的总结)
  - [4.4 线程示例 - 买票](#44-线程示例-买票)
  - [4.5 多线程安全问题](#45-多线程安全问题)
  - [4.6 同步函数和同步代码块](#46-同步函数和同步代码块)
  - [4.7 多线程下的单例](#47-多线程下的单例)
  - [4.8 死锁](#48-死锁)
  - [SE14](#se14)
  - [4.9 线程间通信](#49-线程间通信)
  - [4.10 等待唤醒机制（未完成SE14）](#410-等待唤醒机制未完成se14)
- [五、常用对象API(SE15-SE20)](#五-常用对象apise15-se20)
  - [5.1 String类](#51-string类)
    - [5.1.1 String类基础知识](#511-string类基础知识)
    - [5.1.2 String类的方法](#512-string类的方法)
      - [**1、获取**](#1-获取)
      - [**2、转换**](#2-转换)
      - [**3、判断**](#3-判断)
      - [**4、比较**](#4-比较)
      - [**5、intern方法**](#5-intern方法)
    - [5.1.3 String的练习](#513-string的练习)
      - [**1、字符串的排序:StringTest1.java**](#1-字符串的排序stringtest1java)
      - [**2、一个子串在整串中出现的次数:StringTest2.java**](#2-一个子串在整串中出现的次数stringtest2java)
      - [**3、两个字符串中最大相同的子串：StringTest3.java**](#3-两个字符串中最大相同的子串stringtest3java)
      - [**4、模拟一个trim功能一致的方法：StringTest4.java**](#4-模拟一个trim功能一致的方法stringtest4java)
  - [5.2 对象包装类](#52-对象包装类)
    - [5.2.1 基本数据类型对象包装类概述](#521-基本数据类型对象包装类概述)
      - [**1、基本数据类型包装类的作用**](#1-基本数据类型包装类的作用)
      - [**2、基本数据类型包装类的进制转换**](#2-基本数据类型包装类的进制转换)
      - [**3、int和Integer的区别:**](#3-int和integer的区别)
      - [**4、JDK自动装箱拆箱**](#4-jdk自动装箱拆箱)
    - [5.2.2 基本数据类型对象包装类练习](#522-基本数据类型对象包装类练习)
  - [5.3 StringBuffer类](#53-stringbuffer类)
    - [5.3.1 StringBuffer特点](#531-stringbuffer特点)
    - [5.3.2 StringBuffer操作](#532-stringbuffer操作)
  - [5.4 StringBuilder类](#54-stringbuilder类)
    - [5.4.1 StringBuffer和StringBuilder对比](#541-stringbuffer和stringbuilder对比)
      - [不同点：](#不同点)
      - [联系：](#联系)
    - [5.4.2 StringBuilder类练习：StringBuilderTest.java](#542-stringbuilder类练习stringbuildertestjava)
      - [补充： JDK升级的原因：](#补充-jdk升级的原因)
  - [5.5 集合框架](#55-集合框架)
- [六、IO流（SE21-SE24)](#六-io流se21-se24)
- [七、GUI(SE25)](#七-guise25)
- [八、网络编程(SE26)](#八-网络编程se26)

<!-- /code_chunk_output -->

# 四、多线程（SE13-SE14）
## SE13 
## 4.1 多线程概述
![](images/多线程概述.png)
![](images/java的两种线程1.png)
![](images/java的两种线程.png)
![](images/多线程概述2.png)

## 4.2 创建多线程
**方法一：继承Thread类**
![](images/创建多线程的方法一.png)
![](images/创建多线程的方法一Thread类.png)

但是当需要多线程的类本身有父类时，方法一就不能用了，这时使用方法二，用
接口实现。

**方法二：实现Runnable接口**
![](images/创建多线程的方法二.png)
![](images/创建多线程的方法二Runable接口.png)

![](images/用Runable接口实现多线程的优势.png)

## 4.3 线程的总结
**多线程的内存图解**
![](images/多线程的内存示例.png)
**多线程的四种状态**
![](images/多线程的四种状态.png)

## 4.4 线程示例 - 买票
**方法一实现**
![](images/买票.png)

**方法二实现**
![](images/买票方法二.png)

**易错点：**
![](images/买票易错点.png)

## 4.5 多线程安全问题
**原因：多线程共享数据时，某个没处理完的线程被CPU打断，回来后数据不同了**
![](images/线程安全问题原因.png)
![](images/线程安全问题.png)

**解决方法：同步代码块**
![](images/线程安全问题解决方法.png)
![](images/线程安全问题解决方法2.png)
![](images/线程安全问题解决方法3.png)

**同步方法解决多线程安全问题的示例**
```java
// 需求： 储户，两个
//        每个都到银行存钱每次存100，存3次
class Bank{
  private int sum = 0;
  Object obj = new Object();
  public void add(int num){// add是多线程的代码，有共享数据
    synchronized(obj){    //用同步的方法，加一个锁
      sum = sum + num;
      System.out.println("bank sum = " + sum);
    }
  }
}

// 定义类实现Runnable，用于放线程的内容
class Cus implements Runnable{  
  private Bank b = new Bank ();
  public void run(){
    // Bank b = new Bank ();
    for (int x = 0 ; x< 3; x++){
      b.add(100); // add也是进程的代码
    }
  }
}

class BankDemo{
  public static void main(String[] args) {
    // 实例化Runnable子类的对象
    Cus c = new Cus();
    // 创建线程的对象，并传入Runnable子类的对象作为线程的内容
    Thread t1 = new Thread(c);
    Thread t2 = new Thread(c);
    // 通过Thread对象，运行多线程
    t1.start();
    t2.start();
    
  }
}
```
## 4.6 同步函数和同步代码块
**验证同步函数锁1（有疑问）**
![](images/验证同步函数锁1（有疑问）.png)

**验证同步函数锁2（有疑问flag）**
![](images/验证同步函数锁2（有疑问flag）.png)

**同步函数和同步代码块的对比**
![](images/同步函数和同步代码块的对比.png)

**静态同步函数锁**
![](images/验证静态同步函数锁.png)
```java
// 买票：
// 需求：多个人，买票----》多线程
//      一张票，有人买了，其他人就不能再买了。

// 1. 搞清该同步那个部分：run? show？
//		如果同步run则0线程进来之后出不去，因为while (true)始终成立。
//		所以需要同步的是show()函数

// 2. 验证同步函数的锁
//		Thread-0在同步函数中买票
//		Thread-1在同步代码块中买票
//		如果两个Thread用的同一个锁的话就不会有安全隐患，这个通过flag实现。

class SynFunctionDemoTicket implements Runnable {
	private int num = 200; // 票数
	//Object obj = new Object();
	boolean flag = true;
	public void run() {
		if (flag) {// flag为true，Thread-0同步代码块买票
			while(true){
				synchronized (this) {
					if (this.num > 0) {
						try {Thread.sleep(10);} catch (InterruptedException e) {}
						System.out.println("flag:" + flag + " " + Thread.currentThread().getName()
						+ "....SynFunction obj..." + this.num--);
					}
				}				
			}
		} else {// flag为false，Thread-1同步函数show买票
			while (true) {this.show();}
		}
	}
	public synchronized void show() {// 同步函数防止多线程公用数据时发生错误
		if (this.num > 0) {
			try {Thread.sleep(10);} catch (InterruptedException e) {}
			System.out.println(
					"flag:" + flag + " " + Thread.currentThread().getName() 
					+ "....SynFunction fun..." + this.num--);
		}
	}
}
class SynFunctionDemo {
	public static void main(String[] args) {
		// 展示方法二
		SynFunctionDemoTicket t = new SynFunctionDemoTicket(); // 将并行处理的部分作为参数传给多线程的类Thread
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		// 在主线程开启了-0之后，把flag置为假之前，让主线程停一下，就剩-0线程运行
		try {Thread.sleep(10);} catch (InterruptedException e) {}
		t.flag = false;
		t2.start();
	}
}
```
## 4.7 多线程下的单例
```java
// // 饿汉式
// class Single{
// 	private static final Single s = new Single();
// 	private Single(){}
// 	public static Single getInstance(){
// 		return s;
// 	}
// }

// 懒汉式(面试！！！)
class Single {
	private static Single s = null;

	private Single() {
	}

	public static Single getInstance() {
		// 共享数据
		// 多条语句操作共享数据
		if (s == null) {
			synchronized (Single.class) {
				if (s == null) {
					s = new Single();
				}
			}
		}
		return s;
	}
}

//
class SingleDemo {
	public static void main(String[] args) {
		System.out.println("Hello world.");
	}
}
```
## 4.8 死锁
**死锁的情景-同步的嵌套**
![](images/死锁.png)

## SE14
## 4.9 线程间通信
![](images/线程间通信.png)
![](images/不同任务的多线程公用资源.png)
![](images/不同任务的多线程成片输出原因.png)

## 4.10 等待唤醒机制（未完成SE14）
![](images/等待唤醒机制.png )
![](images/等待唤醒机制2.png)

# 五、常用对象API(SE15-SE20)
## 5.1 String类
### 5.1.1 String类基础知识
![](images/String类.png)
![](images/String对象一旦创建就不能改变.png)

**不同String对象比较**
> s:创建一个对象，在常量池中，由String类维护，不可变，可共享。
> s1:创建两个对象：new的和“abc”在堆内存中。
![](images/String对象2.png)

### 5.1.2 String类的方法
#### **1、获取**
![](images/String对象方法-获取.png)
![](images/String对象方法2.png)
![](images/String对象方法1.png)
![](images/String对象方法3.png)

#### **2、转换**
![](images/String方法2_转换.png)

#### **3、判断**
 String方法3_判断.png
#### **4、比较**
```java
package cn.itcast.pl.string.demo;

class StringMethodDemo{
	public static void main(String[] args) {
		System.out.println("hello world.");
		stringMethodDemo();
	}
	public static void stringMethodDemo(){
		// 1. 获取
		System.out.println("========== 1. 获取 =========");
		String s = "abcdecfa";
		System.out.println("s:" + s);
		System.out.println("length:" + s.length());
		System.out.println("char:" + s.charAt(2));
		System.out.println("index:" + s.indexOf("c"));
		System.out.println("lastIndex:" + s.lastIndexOf("a"));

		System.out.println("substring:" + s.substring(2,5));
		
		// 2. 转换
		String sz = "张三，李四，王五";
		System.out.println(" =========== 2. 转换 ==========");
		System.out.println("sz:" + sz);
		// 2.1 split
		String[] arr = sz.split("，");
		System.out.println("=======arr.length:" + arr.length); // 3
		for (int i = 0; i <arr.length; i++){
			System.out.println( arr[i]);
		}
		// 2.2 toCharArray 
		char[] chs = sz.toCharArray();
		System.out.println("=======chs.length:" + chs.length); // 8
		for (int i = 0; i <chs.length; i++){
			System.out.println( chs[i]);
		}
		// 2.3 getBytes
		String sz1 = "ab你";
		byte[] bytes = sz1.getBytes(); // 输出ASCII码
		System.out.println("=======bytes.length:" + bytes.length); //5
		System.out.println("sz1:" + sz1);
		for (int i = 0; i <bytes.length; i++){
			System.out.println( bytes[i]);
		}
		// 2.4 toUpperCase,toLowerCase 
		System.out.println("Abc".toUpperCase());
		System.out.println("Abc".toLowerCase());
		
		// 2.5 replace
		System.out.println("=======replace"); 
		String s1 = "java";
		String s2 = s1.replace("a", "p");
		System.out.println("s2: "+ s2);

		// 2.6 trim
		System.out.println("=======trim"); 
		String st1 = "  java";
		String st2 = st1.trim();
		System.out.println("st1: "+ st1 + "\n"+"st2: "+ st2);

		// 2.7 字符串进行连接concat
		System.out.println("=======concat"); 
		String sc1 = "java";
		String sc2 = "concat";
		System.out.println("sc1: "+ sc1 + "\n"+"sc2: "+ sc2);
		String sc3 = sc1.concat(sc2);
		System.out.println("sc1.concat(sc2): "+ sc3);

		// 3. 判断 返回值位boolean
		System.out.println(" =========== 3. 判断 ==========");
		String sp = "abc";
		// equals + toLowerCase
		System.out.println(sp.equals("ABC".toLowerCase()));// true
		// equalsIgnoreCase
		System.out.println(sp.equalsIgnoreCase("ABC"));//true
		// equals
		System.out.println(sp.equals("ABC"));//false
		// contains
		System.out.println(sp.contains("cc"));//false
		// start
		String str = "ArrayDemo.java";
		System.out.println(str.startsWith("Array"));//true
		System.out.println(str.endsWith(".java")); //true

		// 4. a
		System.out.println(" =========== 4. 判断 ==========");
		System.out.println("a compareTo A: " + "a".compareTo("A")); //true
	}
}
```
#### **5、intern方法**
返回字符串对象的规范化表示形式。

### 5.1.3 String的练习
#### **1、字符串的排序:StringTest1.java**
![](images/StringTest1思路.png)

#### **2、一个子串在整串中出现的次数:StringTest2.java**
![](images/StringTest2思路.png)

#### **3、两个字符串中最大相同的子串：StringTest3.java**
![](images/StringTest3思路.png)
![](images/StringTest3难点1.png)

#### **4、模拟一个trim功能一致的方法：StringTest4.java**
![](images/StringTest4思路.png)


## 5.2 对象包装类
### 5.2.1 基本数据类型对象包装类概述
![](images/API基本数据类型对象包装类概述.png)

#### **1、基本数据类型包装类的作用**
![](images/基本数据类型包装类的作用1.png)

#### **2、基本数据类型包装类的进制转换**
![](images/API包装类的进制转换.png)

#### **3、int和Integer的区别:**
>1、Integer是int的包装类，int则是java的一种基本数据类型 
2、Integer变量必须实例化后才能使用，而int变量不需要 
3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
4、Integer的默认值是null，int的默认值是0

#### **4、JDK自动装箱拆箱**
![](images/JDK自动装箱拆箱.png)
![](images/JDK自动装箱拆箱2.png)

### 5.2.2 基本数据类型对象包装类练习
![](images/基本数据类型对象包装类练习.png)

## 5.3 StringBuffer类
### 5.3.1 StringBuffer特点
![](images/StringBuffer.png)
![](images/StringBuffer特点.png)

### 5.3.2 StringBuffer操作
![](images/StringBuffer操作.png)



## 5.4 StringBuilder类
### 5.4.1 StringBuffer和StringBuilder对比
#### 不同点：
> * StringBuffer类（旧：JDK1.0），是线程同步的，通常用于多线程。
> * StringBuilder类（新:JDK1.5），线程不同步，通常用于单线程，快。

#### 联系：
> jdk1.5以后出现了功能和StringBuffer一模一样的对象，就是StringBuilder。

### 5.4.2 StringBuilder类练习：StringBuilderTest.java
> 存完数据之后以字符串来用，就用StringBuffer或者StringBuilder类
```java
//  将int类型的数组变为字符串

class StringBuilderTest{
    public static void main(String[] args) {
        int [] arr = {1,4,3,5,6,7,9};
        System.out.println("arr:" + "\t" + arr);
        // 用String，字符串拼接
        String str = arrayToString1(arr);
        System.out.println("str:" + "\t" + str);
        // 用StringBuilder，append方法
        StringBuilder str2 = arrayToString2(arr);
        System.out.println("str2:" + "\t" + str2);

    }

    private static StringBuilder arrayToString2(int[] arr) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i =0; i<arr.length; i++)
            if(i != arr.length-1){
                // str.append(arr[i]).append(",");
                str.append(arr[i] + ","); // 上一行的另一种写法
            }
            else{
                str.append(arr[i]).append("]");
            }
        return str;
    }
    public static String arrayToString1(int[] arr) {
        String str = "[";

        for(int i =0; i<arr.length; i++)
            if(i != arr.length-1){
                str += arr[i] +","; // 每一次都有一个字符串常量值
            }
            else{
                str += arr[i] +"]";
            }
        return str;
    }
}
```
#### 补充： JDK升级的原因：
> * 简化书写
> * 提高效率
> * 增加安全性

## 5.5 集合框架
> 集合和数组
> 集合：可变长度
> 数组：固定长度




# 六、IO流（SE21-SE24)

# 七、GUI(SE25)

# 八、网络编程(SE26)
