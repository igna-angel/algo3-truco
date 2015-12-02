package com.modelo;

import com.exceptions.EmptyListException;
import com.exceptions.ValueNotFoundException;

public class CircularList<T> {

	private Node<T> _firstNode;
	private Node<T> _lastNode;
	private Node<T> _currentNode;
	private int _size;
	
	public CircularList(){
		this._firstNode = null;
		this._lastNode = null;
		this._currentNode = null;
		this._size = 0;
	}
	
	public int getSize(){
		return this._size;
	}
	
	public boolean isEmpty(){
		return this.getSize() == 0;
	}
	
	public void add(T value){
		Node<T> newNode = new Node<T>(value);
		if(this.isEmpty()){
			this._firstNode = newNode;
			this._lastNode = newNode;
			newNode.setPrevious(this.getFirstNode());					
		}else{
			this._lastNode.setNext(newNode);
			newNode.setPrevious(this.getLastNode());
		}		
		
		newNode.setNext(this.getFirstNode());
		this.getFirstNode().setPrevious(newNode);
		this._lastNode = newNode;
		this._size++;
	}
	
	public T getCurrent(){
		return this.getCurrentNode().getValue();
	}
	
	private Node<T> getCurrentNode(){
		return this._currentNode;
	}
	
	private Node<T> getLastNode(){
		return this._lastNode;
	}

	private Node<T> getFirstNode(){
		return this._firstNode;
	}
	
	public void advanceCursor(){
		this._currentNode = this.getCurrentNode().getNext();
	}

	public void resetToFirst(){
		this._currentNode = this.getFirstNode();
	}
	
	public T getAt(int index){
		if(index > this.getSize()-1) throw new IndexOutOfBoundsException();
		
		int i = 0;
		
		this.resetToFirst();
		
		while(i < index){
			this.advanceCursor();
			i++;
		}
		
		return this.getCurrent();
	}
	
	public T getLast(){
		if(this.isEmpty()) throw new EmptyListException();
		return this.getLastNode().getValue();
	}
	
	public T getFirst(){
		if(this.isEmpty()) throw new EmptyListException();
		return this.getFirstNode().getValue();
	}
	
	public void removeAt(int index){
		if(index > this.getSize()) throw new IndexOutOfBoundsException();
		
		int i = 0;
		Node<T> node;
		
		this.resetToFirst();
		
		while(i < index){
			this.advanceCursor();
			i++;
		}
		
		node = this.getCurrentNode();
		
		if(this.getSize() > 1){
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());
			this._size--;
		}else if (this.getSize() == 1){
			this._firstNode = null;
			this._currentNode = null;
			this._lastNode = null;
			this._size--;
		}	
	}
	
	public boolean isCurrentFirst(){
		return this.getCurrent() == this.getFirst();
	}
	
	public int getIndexOf(T value){
		int i = 0;
		boolean found = false;
		
		this.resetToFirst();		
		while(!found && i < this.getSize()){
			if(value == this.getCurrent()) 
				found = true;
			else{
				this.advanceCursor();
				i++;
			}
		}
		
		if(found) return i;
		
		throw new ValueNotFoundException();
	}

	public void moveCursorTo(int index){
		this.resetToFirst();
		
		int i = 0;
		
		while(i < index){
			this.advanceCursor();
			i++;
		}
	}
}
