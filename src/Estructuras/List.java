/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Estructuras;

/**
 *
 * @author ABRAHAM
 */
public  abstract class List<T> {
    
    public abstract Boolean empty();
    public abstract Boolean full();
    public  abstract Boolean delete(T item);
    public abstract Boolean insert(T item);
    public  abstract Boolean search(T item);
    public abstract void output();
    
    
}
