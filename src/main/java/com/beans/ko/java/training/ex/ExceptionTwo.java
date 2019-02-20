package com.beans.ko.java.training.ex;

public class ExceptionTwo {

	public static void main(String[] args) {
		ExceptionTwo t1=new ExceptionTwo();
        System.out.println("test1方法执行完毕！result的值为："+t1.test2());
	}
	
    public int test1(){
        int divider=10;
        int result=100;
        try{
            while(divider>-1){
                divider--;
                result=result+100/divider;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("异常抛出了！！");
            return -1;
        }
    }
    
    public int test2(){
        int divider=10;
        int result=100;
        try{
            while(divider>-1){
                divider--;
                result=result+100/divider;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("异常抛出了！！");
            return result=999;
        }finally{
            System.out.println("这是finally，哈哈哈！！");
            System.out.println("result的值为："+result);
        }
        
    }

}
