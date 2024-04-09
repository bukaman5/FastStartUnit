package com.ski.skiresort.exeption;

public class CoachNotFoundException extends RuntimeException{
private static final long serialVersionUID =1;
public CoachNotFoundException(String message){
super(message);
}
}
