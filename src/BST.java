import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        K key;
        V val;
        Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // Insert or update a key-value pair
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val;
        return node;
    }


    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.val;  // key found
        }
        return null;
    }


    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            size--;
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }


    public Iterable<KVPair> iterator() {
        return new Iterable<KVPair>() {
            public Iterator<KVPair> iterator() {
                return new BSTIterator(root);
            }
        };
    }


    private class BSTIterator implements Iterator<KVPair> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node root) {
            pushAll(root);
        }

        private void pushAll(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public KVPair next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            pushAll(node.right);
            return new KVPair(node.key, node.val);
        }
    }


    public class KVPair {
        private K key;
        private V value;

        public KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}



