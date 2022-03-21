import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> implements IMap<K, V> {

	private int CAPACITY = 10;
    private MapBucket[] bucket;
    private int size = 0;
	
    public HashMap() {
    	this.bucket = new MapBucket[CAPACITY];
    }
    
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K key) {
		return null;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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