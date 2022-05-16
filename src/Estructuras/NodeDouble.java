/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author ABRAHAM
 */
public class NodeDouble<T> {
    
    private T data;
    private NodeDouble next,back;

   public NodeDouble(){
        this(null);
    }
    
    public NodeDouble(T data) {
        this.data = data;
        next = null;
        back = null;
    }
    

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeDouble getNext() {
        return next;
    }

    public void setNext(NodeDouble next) {
        this.next = next;
    }

    public NodeDouble getBack() {
        return back;
    }

    public void setBack(NodeDouble back) {
        this.back = back;
    }
    
    
}
