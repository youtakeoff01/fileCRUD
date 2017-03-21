package com.hand.main.test;

public class MainReturnFinallyTest {
	public static void main(String[] args) {
		System.out.println(testThis());
		
//		System.out.println(getString());
	}
	
	public static String testThis(){
		try {
			return "one";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally");
		}
		return "two";
	}
		
	static String getString(){
		try{
            return "SUCCESS";
        }catch(Exception e){
             e.printStackTrace();
        }finally{
            System.out.println("Finally is executing");
        }
		return "ERROR";//如果这句放在finally之外呢？
    }	
}

