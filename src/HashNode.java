// HashNode class definition
class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Overriding toString for easy debugging
    @Override
    public String toString() {
        return "{" + key + "=" + value + '}';
    }
}





