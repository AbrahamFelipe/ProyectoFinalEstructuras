/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author ABRAHAM
 */
public class QueueRef<T> implements Queue<T> {
    private Node<T> front, rear;

    public T getFront() {
        return front.getData();
    }
    private int count;
    

    public QueueRef() {
        front = null;
        rear = null;
        count = 0;
        
    }
    


    @Override
    public void enqueue(T data) {
        Node<T> r = new Node(data); 
        
        if(rear != null){
            rear.setNext(r);
        
        } else {
            front = r;
        }
        rear = r;
        count++;
        
    }

    @Override
    public T dequeue() {
        if(!empty()){
            T ret = front.getData(); 
            front = front.getNext();
            count--;
            return ret;
        } else{
            rear = null;
            throw new RuntimeException("El Queue esta vacio");
        }
        
        
    }

    @Override
    public Boolean empty() {
        return count<=0;
    }

    @Override
    public Boolean full() {
        //Este metodo no es necesario en este tipo de queue
        return null;
    }
    
    public void print(){
        Node<T> ref = front; //puede cambiar en generico
        while(ref != null){
            System.out.print(ref.getData()+" ");
            ref = ref.getNext();
        }
        System.out.println("");
    }
    
    
    public int getCount() {//da el numero total de elementos que tiene el queue
        return count;
    }
    
    
}