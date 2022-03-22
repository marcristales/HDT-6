import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author mario
 *
 * @param <K>
 * @param <V>
 */
public class TreeMap<K, V> implements IMap<K, V> {
	
	private int CAPACITY = 10;
    private MapBucket2[] bucket;
    private int size = 0;

    
    public TreeMap() {
        this.bucket = new MapBucket2[CAPACITY];
    }

    private int getHash(K key) {
        return (key.hashCode() & 0xfffffff) % CAPACITY;
    }

    private Entry2 getEntry(K key) {
        int hash = getHash(key);
        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            Entry2 Entry2 = bucket[hash].getEntries().get(i);
            if (Entry2.getKey().equals(key)) {
                return Entry2;
            }
        }
        return null;
    }

    
    //si la key ya existe, se actualiza el valor de esa key, de caso contrario, se agrega al bucket
    public void put(K key, V value) {
        if (containsKey(key)) {
            Entry2 entry = getEntry(key);
            entry.setValue(value);
        } else {
            int hash = getHash(key);
            if (bucket[hash] == null) {
                bucket[hash] = new MapBucket2();
            }
            bucket[hash].addEntry(new Entry2<>(key, value));
            size++;
        }
    }

    
    //Validar si existe la key, regresar data
    public V get(K key) {
        return containsKey(key) ? (V) getEntry(key).getValue() : null;
    }

    //chequea si la bucket es nula, sino, la bucket tiene esa llave
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


//Se crea un bucket para guardar todos los key values
class MapBucket2 {
    private List<Entry2> entries;

    public MapBucket2() {
        if (entries == null) {
            entries = new LinkedList<>();
        }
    }

    public List<Entry2> getEntries() {
        return entries;
    }

    public void addEntry(Entry2 entry) {
        this.entries.add(entry);
    }

    public void removeEntry(Entry2 entry) {
        this.entries.remove(entry);
    }
}

class Entry2<K, V> {
    private K key;
    private V value;

    public Entry2(K key, V value) {
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