package Estructuras;

/**
 *
 * @author ABRAHAM
 * @param <T>
 */
public class StackRef<T>  implements  Stack<T>{
    
    private Node<T> top;

    public T getTop() {
        return top.getData();
    }
    private int N;

    //Da el numero de elementos que tiene el Stack
    public int getN() {
        return N;
    }
    
    public StackRef(){
        top = null;
        N = 0;
    }
    

    @Override
    public Boolean empty() {
        return top == null;
    }

    @Override
    public Boolean full() {
        //Este metodo no es necesario en este tipo de stack
        return null;
    }

    @Override
    public T pop() {
        if(empty()){
           throw new RuntimeException("El stack ya esta vacio");           
        }
        T ref = top.getData();
        top = top.getNext();
        N--;
        return ref;
    }

    @Override
    public void push(T item) {
        
        Node<T> newp = new Node<T>(item);
        newp.setNext(top);
        top = newp;
        N++;
        
    }
    
    
    public void print(){
        Node<T> ref=top;
        while(ref !=null){
            System.out.print(ref.getData()+" ");
            ref = ref.getNext();
        }
        System.out.println(" ");
    
    }
    
    
}