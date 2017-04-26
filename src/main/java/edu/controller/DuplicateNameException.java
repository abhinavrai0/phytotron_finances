package edu.controller;

public class DuplicateNameException extends Exception {

	public DuplicateNameException(){
		
	}
	
	public DuplicateNameException( String message){
		super( message);
	}
}
