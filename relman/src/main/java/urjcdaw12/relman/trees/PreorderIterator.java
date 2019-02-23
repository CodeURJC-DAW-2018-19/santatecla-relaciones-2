package urjcdaw12.relman.trees;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Predicate;

public class PreorderIterator<E> implements Iterator<Position<E>> {
    private final Tree<E> tree;
    private final Stack<Position<E>> stack;
    private final Predicate<E> predicate;


    public PreorderIterator(Tree<E> tree, Position<E> start) {
        stack = new Stack<>();
        this.tree=tree;
        stack.push(start);
        predicate=null;
    }

    public PreorderIterator(Tree<E> tree, Position<E> start, Predicate<E> predicate) {
        this.predicate=predicate;
        stack = new Stack<>();
        this.tree=tree;
    }

    public PreorderIterator(Tree<E> tree) {
        stack = new Stack<>();
        this.tree=tree;
        stack.push(this.tree.root());
        predicate=null;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Position<E> next() {
        if (stack.isEmpty()){
            throw new NoSuchElementException();
        }
        Position<E> aux= stack.pop();
       // Collections.reverse((List<?>) tree.children(aux));  //Damos la vuelta a la lista de hijos para que entren de derecha a izquierda
        ArrayList<Position<E>> arrayAux= new ArrayList<>();
        for (Position<E> p:tree.children(aux)){ //Copiamos los hijos en un array auxiliar
            arrayAux.add(p);
        }
        Collections.reverse(arrayAux); //Damos la vuelta a la lista de hijos auxiliar para que entren de derecha a izquierda
        for (Position<E> p:arrayAux){
            stack.push(p);
        }
        if (predicate!=null) {
            if (predicate.test(aux.getElement())) {
                return aux;
            }
            return next();
        }else{
            return aux;
        }
    }



}
