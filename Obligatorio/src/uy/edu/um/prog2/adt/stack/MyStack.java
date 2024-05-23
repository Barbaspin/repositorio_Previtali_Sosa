package uy.edu.um.prog2.adt.stack;
import Exceptions.*;
public interface MyStack<T> {

	void push(T value);
	
	T pop() throws EmptyStackException;
	
	T peek();
	
	int size();
	
}
