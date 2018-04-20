package com.insightfullogic.java8.examples.chapter4;

import java.util.function.BinaryOperator;

public class OverLoadMethod {
	
	private static int x = 2;
	
	private static int y = 4;

	private static interface IntegerBiFunction extends BinaryOperator<Integer>{
		
	}
	
	private static void overloadeMethod(BinaryOperator<Integer> Lambda) {
		int z = Lambda.apply(x, y);
		System.out.println("this is OverLoadMethod.BinaryOperator<Integer> " + z);
	}
	
	private static void overloadeMethod(IntegerBiFunction Lambda) {
		int z = Lambda.apply(x, y);
		System.out.println("this is OverLoadMethod.IntegerBiFunction " + z);
	}
	
	public static void main(String str[]) {
		//lamda表达式的类型就是接口类型，对于函数重载，只选择最具体的哪一个函数接口
		OverLoadMethod.overloadeMethod((x,y)->{return x+y;});
		OverLoadMethod.overloadeMethod((Integer x,Integer y)->{return x+y;});
		
		
	}
}
