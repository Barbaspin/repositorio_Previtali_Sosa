package uy.edu.um.prog2.adt.queue;
import Exceptions.*;

public interface MyQueue<T> {

	void enqueue(T value);
	
	T dequeue() throws EmptyQueueException;
	
	boolean contains(T value);
	
	int size();
	
}
