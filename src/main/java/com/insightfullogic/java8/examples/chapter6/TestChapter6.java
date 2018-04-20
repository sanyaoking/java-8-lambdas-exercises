package com.insightfullogic.java8.examples.chapter6;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestChapter6 {
	public static void main(String[] str) {
		//流使用及早球之后，就关闭stream，代码要注意只一点
//		IntStream range  = IntStream.range(1, 10);
//		IntStream range1  = IntStream.range(1, 10);
//		TestChapter6.parSumOfSquares(range);
//		TestChapter6.sequentialSumOfSquares(range1);
//		List<Integer> list = Stream.of(1,2,3,4).collect(Collectors.toList());
//		TestChapter6.parThrough(list);
//		TestChapter6.multiplyThrough(list);
		List<Integer> list1 = new ArrayList<Integer>();//Stream.of(1,2,3,4,5).collect(Collectors.toList());
		for(int i=1;i<100;i++) {
			Integer iu = new Integer(i);
			list1.add(iu);
		}
		TestChapter6.slowSumOfSquares(list1);
		List<IntegerUtil> list2 = new ArrayList<IntegerUtil>();//Stream.of(new IntegerUtil(1),new IntegerUtil(2),new IntegerUtil(3),new IntegerUtil(4),new IntegerUtil(5)).collect(Collectors.toList());
		for(int i=1;i<100;i++) {
			IntegerUtil iu = new IntegerUtil(i);
			list2.add(iu);
		}
		TestChapter6.zdySumOfSquares(list2);
	}
	
	public static int parSumOfSquares(IntStream range) {
		double i = range.parallel().mapToDouble(e->e*e).reduce(0, (pre,e)->pre+e);
		System.out.println(i);
		return 1;
	}
	
	public static int sequentialSumOfSquares(IntStream range) {
		int i = range.map(x->x*x).sum();
		System.out.println(i);
		return 1;
	}
	
	public static int parThrough(List<Integer> list) {
		List<Integer> temp = new ArrayList<Integer>(Arrays.asList(new Integer[list.size()]));
		Collections.copy(temp, list);
		temp.add(0, temp.get(0)*5);
		int i = temp.stream().parallel().reduce(1, (pre,e)->{
				return pre*e;
			});
		System.out.println(i);
		return 1;
		
	}
	
	public static int multiplyThrough(List<Integer> list) {
		List<Integer> temp = new ArrayList<Integer>(Arrays.asList(new Integer[list.size()]));
		Collections.copy(temp, list);
		int i = temp.stream().reduce(5, (pre,e)->pre*e);
		System.out.println(i);
		return 1;
		
	}
	
	public static void slowSumOfSquares(List<Integer> list) {
		int i = list.stream().parallel().reduce(0, (x,y)->x+y*y, (x,y)->x+y);
//		i = list.parallelStream().collect(e->0, (x,y)->x+y*y, (x,y)->x+y);
		System.out.println("slowSumOfSquares = "+i);
	}
	
	public static void zdySumOfSquares(List<IntegerUtil> list) {
		IntegerCollector ic = new IntegerCollector();
		IntegerUtil i = list.parallelStream().collect(ic);
//		i = list.parallelStream().collect(e->0, (x,y)->x+y*y, (x,y)->x+y);
		System.out.println("zdySumOfSquares = "+i.getResult().intValue());
	}
	
	static class IntegerCollector implements Collector<IntegerUtil, IntegerUtil, IntegerUtil>{
		
		private IntegerUtil it = new IntegerUtil(0);
		
		public IntegerCollector() {
			
		}

		@Override
		public Supplier<IntegerUtil> supplier() {
			// TODO Auto-generated method stub
			return ()->this.it;
		}

		@Override
		public BiConsumer<IntegerUtil, IntegerUtil> accumulator() {
			// TODO Auto-generated method stub
			return it::acc;
		}

		@Override
		public BinaryOperator<IntegerUtil> combiner() {
			// TODO Auto-generated method stub
			return it::com;
		}

		@Override
		public Function<IntegerUtil, IntegerUtil> finisher() {
			// TODO Auto-generated method stub
			return  x->x;
		}

		@Override
		public Set<Characteristics> characteristics() {
			// TODO Auto-generated method stub
			return  Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
		}
		
	}
	
	static class IntegerUtil{
		
		private Integer result = new Integer(0);
		
		public IntegerUtil(int i) {
			result = new Integer(i);
		}
		
		public void SetResult(Integer y ) {
			this.result = y;
		}
		
		
		public Integer getResult() {
			return result;
		}
		
		public IntegerUtil get(IntegerUtil i, IntegerUtil y) {
			return i;
		}

		public IntegerUtil acc (IntegerUtil x, IntegerUtil y) {
			// TODO Auto-generated method stub
//				System.out.println("x = "+x.result+";y = "+y.result);
//				System.out.println(pre.intValue());
			    Integer temp = x.getResult()+y.getResult()*y.getResult();
			    x.SetResult(temp);
//			    System.out.println(x.getResult().intValue());
//			    System.out.println(this.result.intValue());
				return x;
		}
		
		public IntegerUtil com (IntegerUtil x, IntegerUtil y) {
			// TODO Auto-generated method stub
//				System.out.println("pre = "+pre.intValue());
//				this.result = x.getResult()+y.getResult();
//				Integer temp = x.getResult()+y.getResult();
//			    x.SetResult(temp);
				return x;
		}
	}
}
