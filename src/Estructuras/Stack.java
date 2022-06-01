package Estructuras;

/**
 *
 * @author ABRAHAM
 * @param <T>
 */
public interface Stack<T> {
    
    public Boolean empty();
    public Boolean full();
    public T pop();
    public void  push(T item);
   
}