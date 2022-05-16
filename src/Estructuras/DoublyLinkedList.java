/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author ABRAHAM
 * @param <T>
 */
public class DoublyLinkedList<T> extends List<T>{
    
    private NodeDouble<T> head;
    private NodeDouble<T> tail;
    private int count;

    public DoublyLinkedList(){
        head = null;
        tail = null;
        count = 0;
    
    }

    //añadir elementos al final de la lista
    public void addLast(T data){
        NodeDouble<T> newNodo = new NodeDouble<>(data);
        //si lista esta vacia
        if(empty()){
            head = newNodo;
            tail = newNodo;
        }else{
            tail.setNext(newNodo);
            newNodo.setBack(tail);
            tail = newNodo;  
        
        }
        count++;
    }

    public void addFirst(T data){
        NodeDouble<T> newNodo = new NodeDouble<>(data);
        //si lista esta vacia
        if(empty()){
            head = newNodo;
            tail = newNodo;
        } else{
            head.setBack(newNodo);
            newNodo.setNext(head);
            head = newNodo;
        }
        count++;

    }
   
    //inserta un elemento en la posicion especifica en la lista
    public void add(int index, T data){
        NodeDouble<T> newNodo = new NodeDouble<>(data);
        NodeDouble<T> ref = head;
        int ite=0;
        
        if(index==0){
            addFirst(data);
            return;
        }
        
        while(ref != null){          
            if(ite==index){
                ref.getBack().setNext(newNodo);
                newNodo.setBack(ref.getBack());
                newNodo.setNext(ref);
                ref.setBack(newNodo); 
                count++;
                return;
            }
            ref = ref.getNext(); 
            ite++;
        }
        
        if(ref==null){
            throw new RuntimeException("No hay elemento antes de este index");
        }
    }
    
   
    //elimina el primer elemento y devuelve su dato
    public T deleteFirst(){
        T dato;
    
        if (head != null && head.getNext() != null){
            dato = head.getData();
            head.getNext().setBack(null);
            head = head.getNext();
            count--;  
        } else if(head==null){
            dato = null;
            head = null;
            tail = null;
            count = 0;    
        } else{
            dato = head.getData();
            head = null;
            tail = null;
            count--; 
        }
        return dato;

     
    }
    
    //elimina el ultimo elemento y devuelve su dato
    public T deleteLast(){
        T dato; 
        if (tail != null && tail.getBack() != null){
            dato = tail.getData();
            tail.getBack().setNext(null);
            tail = tail.getBack();
            count--;
        }else if(tail==null){
            dato = null;
            head = null;
            tail = null;
        }
        else{
            dato = tail.getData();
            head = null;
            tail = null;
            count--;
        }
        return dato;
    }
    
    //elimina el nodo en el index especificado
    public T deleteElement(int index){
        NodeDouble<T> ref = head;
        int ite=0;
        T dato = null;
        
        if(index==0){
            dato = deleteFirst();
            return dato;
            
        }else if(index==count-1){
            dato = deleteLast();
            return dato;
        }

        while(ref != null){          
            if(ite==index){
                dato = ref.getData();
                ref.getNext().setBack(ref.getBack());
                ref.getBack().setNext(ref.getNext());
                count--;
                return dato;
                
            }
            ref = ref.getNext(); 
            ite++;
        }
        
        if(ref==null){
            throw new RuntimeException("No hay elemento antes de este index");
           
        }
    
        return dato;
    }

    
    
    public T getFirst() {
        if(!empty()){
            return head.getData();
        }else{
            System.out.println("Lista vacia");
            return null;
        }
        
    }

    public T getLast() {
        if(tail != null){
            return tail.getData();
            
        }else{
            System.out.println("Lista vacia");
            return null;
        }
        
    }
    
    //da el dato que contenga el nodo con dicho index
    public T getElement(int index){
        NodeDouble<T> ref = head;
        T data = null;
        int ite=0;
   
        while(ref != null){
            data = ref.getData();
            
            if(ite==index){
                return data;
            }
            ref = ref.getNext(); 
            ite++;
        }
        
        if(ref==null){
            throw new RuntimeException("No hay elemento en este index");
        }
        
        return data;
        
        
    }
    
    @Override
    public void output() {  
        //Node current will point to head  
        NodeDouble<T> ref = head;  
        if(head == null) {  
            System.out.println("Lista doble vacia");  
            return;  
        }  
        System.out.println("Nodos de la lista doble: ");  
        while(ref != null) {  
            System.out.print(ref.getData() + " ");  
            ref = ref.getNext();  
        }  
        System.out.println("");
    }
    
    //remplaza el elemento de la posicion especifica con el elemento especifico
    public void set(int index, T data){
        NodeDouble<T> ref = head;
        int ite=0;
   
        while(ref != null){
            if(ite==index){
                ref.setData(data);
                return;
            }
            ref = ref.getNext(); 
            ite++;
        }
        
        if(ref==null){
            throw new RuntimeException("No hay elemento en este index");
        }
    }
    
    //dal el numero de elementos en la lista
    public int size() {
        return count;
    }
    

    @Override
    public Boolean empty() {
        return head == null;
    }
    
    @Override
    public Boolean search(T data) {
        NodeDouble<T> ref = head;
         while(ref != null){ 
             if(ref.getData().equals(data)){
                 return true;
             }
            ref = ref.getNext();   
        }
         
        if(ref==null){
            return false; 
        }
         
        return false;
        
    }
    


    @Override
    public Boolean insert(T item) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Boolean full() {
        throw new UnsupportedOperationException("No es necesaria en esta clase.");
    }

    @Override
    public Boolean delete(T item) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void addFirst(long itemEli1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
