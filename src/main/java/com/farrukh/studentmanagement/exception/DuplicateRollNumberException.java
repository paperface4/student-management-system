package com.farrukh.studentmanagement.exception;

public class DuplicateRollNumberException extends RuntimeException {
    public DuplicateRollNumberException(String msg){
        super(msg);
    }   
}
