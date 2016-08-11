package copyOnWrite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年8月11日 下午4:58:27
 * @version 1.0
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
	
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
	private volatile Map<K, V> internalMap;

	public CopyOnWriteMap() {
		internalMap = new HashMap<K, V>(DEFAULT_INITIAL_CAPACITY);
	}
	
	public CopyOnWriteMap(int initialCapacity) {
		internalMap = new HashMap<K, V>(initialCapacity);
	}

	public V put(K key, V value) {

		synchronized (this) {
			Map<K, V> newMap = new HashMap<K, V>(internalMap);
			V val = newMap.put(key, value);
			internalMap = newMap;
			return val;
		}
	}

	public V get(Object key) {
		return internalMap.get(key);
	}

	public void putAll(Map<? extends K, ? extends V> newData) {
		synchronized (this) {
			Map<K, V> newMap = new HashMap<K, V>(internalMap);
			newMap.putAll(newData);
			internalMap = newMap;
		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
