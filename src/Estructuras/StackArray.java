
package Estructuras;

import java.util.Arrays;

/**
 *
 * @author ABRAHAM
 * @param <T>
 */
public class StackArray<T> implements Stack<T>{
    
    private static final int N = 3;//valor por defecto
    private int top;
    private T[] sarray;
    
    // constructors
    public StackArray() {
        this(N);
    }
    public StackArray(int n) {
        top = 0;
        sarray = (T[]) new Object[n];
    }
    
    // value returning methods
    @Override
    public Boolean empty() {
        return top <= 0;
    }
    @Override
    public Boolean full() {
        return top >= sarray.length;
    }
    
    @Override
    public T pop() {
        if(empty()){
            throw new RuntimeException("Stack is empty");
        }
        
        top--;
        return sarray[top];
        
    }
    
    
    @Override
    public void push(T item) {
        if(full()){
            throw new RuntimeException("Stack is full");
        }
        sarray[top]=item;
        top++;
    }
    
    public void print(){
        for(int i = top-1; i>=0;i--){
            System.out.print(sarray[i]+" ");
        }
        System.out.println(" ");
        
    }

    
    
    
}
