package com.modelo;

public class Node<T> {
	
	private T _value = null;
	
	private Node<T> _previousNode = null;
	private Node<T> _nextNode = null;
	
	public Node(T value){
		this._value = value;
	}
	
	public T getValue(){
		return this._value;
	}
	
	public void setNext(Node<T> node){
		this._nextNode = node;
	}
	
	public void setPrevious(Node<T> node){
		this._previousNode = node;
	}
	
	public Node<T> getNext(){
		return this._nextNode;
	}
	
	public Node<T> getPrevious(){
		return this._previousNode;
	}
	
}
