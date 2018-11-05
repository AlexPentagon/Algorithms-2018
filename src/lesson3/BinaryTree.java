package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> parent = null;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;

        }
        else if (comparison < 0) {
            assert closest.left == null;
            newNode.parent = closest;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            newNode.parent = closest;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current;
        Stack<Node<T>> stack;

        private BinaryTreeIterator() {
            Node<T> node = root;
            stack = new Stack<>();
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            Node<T> node = stack.pop();                 //R=O(logN)
            current = node;
            if (node.right != null) {
                node = node.right;
                while (node != null) {                  // T=O(N);
                    stack.push(node);
                    node = node.left;
                }
            }
            return current;
        }
//         Итог:    T=O(N);
//                  R=O(logN)

        @Override
        public boolean hasNext() {
            //return findNext() != null;
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull

    // standard function
    public SortedSet<T> subSet(T fromElement, T toElement) throws IllegalArgumentException,NullPointerException {
        return subSet(fromElement,true,toElement,false);
    }

    public SortedSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive)
            throws IllegalArgumentException,NullPointerException {

        SortedSet<T> result = new BinaryTree<>();
        BinaryTreeIterator i = new BinaryTreeIterator();

        if(fromElement.compareTo(toElement) > 0 ||
        !this.contains(toElement) || !this.contains(fromElement)) throw new IllegalArgumentException();

        while(i.hasNext()){

           T next = i.next();
           if(next.compareTo(toElement) == 0) {
               if(toInclusive) result.add(next);
               break;
            }
            if(next.compareTo(fromElement) >= 0){
                if(fromInclusive) result.add(next);
                else fromInclusive = true;
            }
        }
        return result;
    }
//         Итог:    T=O(N);
//                  R=O(N)
    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return subSet(first(),toElement);
    }
//         Итог:    T=O(N);
//                  R=O(N)
    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return subSet(fromElement,true,last(),true);
    }
//         Итог:    T=O(N);
//                  R=O(N)
    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
