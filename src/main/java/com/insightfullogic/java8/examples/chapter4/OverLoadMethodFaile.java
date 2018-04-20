package com.insightfullogic.java8.examples.chapter4;

import java.util.function.BinaryOperator;


public class OverLoadMethodFaile {
	private static int x = 2;
	private static int y = 2;
	private static String sx = "2";
	private static String sy = "2";
	@FunctionalInterface
	private interface IntegerBiFunction {
		public int apply(int x, int y);
	}
	
	@FunctionalInterface
	private interface StringFunction {
		public String apply(String x, String y);
	}
	
	private static void overloadeMethod(BinaryOperator<Integer> Lambda) {
		Lambda.apply(x, y);
		System.out.println("this is OverLoadMethodFaile.BinaryOperator<Integer>");
	}
	
	private static void overloadeMethod(IntegerBiFunction Lambda) {
		Lambda.apply(x, y);
		System.out.println("this is OverLoadMethodFaile.IntegerBiFunction");
	}
	
	private static void overloadeMethod(StringFunction Lambda) {
		Lambda.apply(sx,sy);
		System.out.println("this is OverLoadMethodFaile.StringFunction");
	}
	
	public static void main(String[] str) {
		/*有时候函数重载，可能无法区分最具体的接口类型。如OverLoadMethodFaile的IntegerBiFunction接口同BinaryOperator接口一样都存在apply需要实现，
		 *由于没有继承关系，导致系统无法比较OverLoadMethodFaile与BinaryOperator那个接口更加具体，导致编译失败
		 *OverLoadMethodFaile.overloadeMethod((x,y)->x+y);
		 *当OverLoadMethodFaile的IntegerBiFunction接口继承BinaryOperator接口后上述方法可以通过系统编译，如下所示的继承关系
		 *
		 */
		OverLoadMethodFaile.overloadeMethod((String x,String y)->x+y);
		OverLoadMethodFaile.overloadeMethod((Integer x,Integer y)->x+y);
		OverLoadMethodFaile.overloadeMethod((int x,int y)->x+y);
	}
	
}
