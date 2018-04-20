package com.insightfullogic.java8.examples.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestChapter1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MusCha mc = new MusCha();
		mc.printlnArtistName();
	}
    static class MusCha extends MusicChapter{
    	
    	public MusCha() {
    		super();
    	}
    	
    	
    	public void printlnArtistName() {
    		//
    		this.artists.stream().map(action->action.getName()).forEach(predicate->System.out.println("tttt"));
    		//惰性求数值测试
    		this.artists.stream().map(action->action.getName()).filter(predicate->{System.out.println("qqqq");return  predicate.equals("John Coltrane");});
    		List<String> names = new ArrayList<String>();
    		//及早求职
    		names = this.artists.stream().map(action->action.getName()).filter(predicate->{System.out.println("123");return  predicate.equals("John Coltrane");}).collect(Collectors.toList());
    		names.stream().forEach(action->System.out.println(action));
    		//双filter测试
    		names = this.artists.stream().map(action->action.getName()).filter(predicate->{System.out.println("123");return  predicate.equals("John Coltrane");}).filter(predicate->{System.out.println("123");return  predicate.equals("Paul McCartney");}).collect(Collectors.toList());
    		names.stream().forEach(action->System.out.println(action));
    	}
    	 
    	public void printlnAlbumName() {
    		
    	}
    	
    	public void printlnAllName() {
    		
    	}
    }
}
