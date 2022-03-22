import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author mario
 *
 * @param <K>
 * @param <V>
 */
public class LinkedHashMap<K, V> implements IMap<K, V> {

	private int CAPACITY = 10;
    private MapBucket3[] bucket;
    private int size = 0;

    public LinkedHashMap() {
        this.bucket = new MapBucket3[CAPACITY];
    }

    private int getHash(K key) {
        return (key.hashCode() & 0xfffffff) % CAPACITY;
    }

    private Entry3 getEntry(K key) {
        int hash = getHash(key);
        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            Entry3 Entry3 = bucket[hash].getEntries().get(i);
            if (Entry3.getKey().equals(key)) {
                return Entry3;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (containsKey(key)) {
            Entry3 entry = getEntry(key);
            entry.setValue(value);
        } else {
            int hash = getHash(key);
            if (bucket[hash] == null) {
                bucket[hash] = new MapBucket3();
            }
            bucket[hash].addEntry(new Entry3<>(key, value));
            size++;
        }
    }

    public V get(K key) {
        return containsKey(key) ? (V) getEntry(key).getValue() : null;
    }

    public boolean containsKey(K key) {
        int hash = getHash(key);
        return !(Objects.isNull(bucket[hash]) || Objects.isNull(getEntry(key)));
    }

    public void delete(K key) {
        if (containsKey(key)) {
            int hash = getHash(key);
            bucket[hash].removeEntry(getEntry(key));
            size--;
        }
    }

    public int size() {
        return size;
    }
	
}

class MapBucket3 {
    private List<Entry3> entries;

    public MapBucket3() {
        if (entries == null) {
            entries = new LinkedList<>();
        }
    }

    public List<Entry3> getEntries() {
        return entries;
    }

    public void addEntry(Entry3 entry) {
        this.entries.add(entry);
    }

    public void removeEntry(Entry3 entry) {
        this.entries.remove(entry);
    }
}

class Entry3<K, V> {
    private K key;
    private V value;

    public Entry3(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // getters & setters
    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    // hashCode & equals
}
