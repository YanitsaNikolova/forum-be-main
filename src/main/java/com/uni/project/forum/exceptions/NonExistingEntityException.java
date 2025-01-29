package com.uni.project.forum.exceptions;

public class NonExistingEntityException extends RuntimeException {

    public NonExistingEntityException(String message) {
        super(message);
    }

    public NonExistingEntityException() {
        super("Entity не съществува!");
    }
}