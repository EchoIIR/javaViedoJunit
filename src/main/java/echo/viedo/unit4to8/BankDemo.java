package echo.viedo.unit4to8;

// 需求： 储户，两个
//        每个都到银行存钱每次存100，存3次

class Bank{
    private int sum = 0;
  
    //====== 同步代码块实现同步 ============
    // Object obj = new Object();
    // public void add(int num){// add是多线程的代码，有共享数据
    //   synchronized(obj){   //用同步的方法，加一个锁  
    //     sum = sum + num;
    //     System.out.println("bank sum = " + sum);
    //   }
    // }
  
      //====== 同步函数实现同步 ============
      
      public synchronized void  add(int num){// add是多线程的代码，有共享数据
          sum = sum + num;
          System.out.println("bank sum = " + sum);
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
  