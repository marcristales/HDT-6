import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author mario
 *
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements IMap<K, V> {

	private int CAPACITY = 10;
    private MapBucket[] bucket;
    private int size = 0;
	
    public HashMap() {
    	this.bucket = new MapBucket[CAPACITY];
    }
    
    private int getHash(K key) {
        return (key.hashCode() & 0xfffffff) % CAPACITY;
    }
    
    private Entry getEntry(K key) {
        int hash = getHash(key);
        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            Entry entry = bucket[hash].getEntries().get(i);
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;
    }
    
	@Override
	public void put(K key, V value) {
		
		if(containsKey(key)) {
            Entry entry = getEntry(key);
            entry.setValue(value);
        } else {
            int hash = getHash(key);
            if(bucket[hash] == null) {
                bucket[hash] = new MapBucket();
            }
            bucket[hash].addEntry(new Entry<>(key, value));
            size++;
        }
		
		
	}

	@Override
	public V get(K key) {
		return containsKey(key) ? (V) getEntry(key).getValue() : null;
	}

	public boolean containsKey(K key) {
        int hash = getHash(key);
        return !(Objects.isNull(bucket[hash]) || Objects.isNull(getEntry(key)));
    }
	
	@Override
	public void delete(K key) {
		
		if(containsKey(key)) {
            int hash = getHash(key);
            bucket[hash].removeEntry(getEntry(key));
            size--;
        }
		
	}

	@Override
	public int size() {
		return size;
	}

}

class MapBucket {
    private List<Entry> entries;

    public MapBucket() {
        if (entries == null) {
            entries = new LinkedList<>();
        }
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public void removeEntry(Entry entry) {
        this.entries.remove(entry);
    }
}

class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
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
    
}