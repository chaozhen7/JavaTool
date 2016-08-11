package lruCache;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LRUCacheMap<K, V> implements Cloneable {
	@SuppressWarnings("unused")
	private int initialCapacity;
	private Map<K, V> cacheMap;

	public LRUCacheMap(final int initialCapacity) {
		this.initialCapacity = initialCapacity;
		this.cacheMap = new LinkedHashMap<K, V>(initialCapacity, 0.75f, true) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Entry<K, V> eldest) {
				// TODO Auto-generated method stub
				return this.size() > initialCapacity;
			}
		};
	}

	public V get(K key) {
		return cacheMap.get(key);
	}

	public void set(K key, V value) {
		cacheMap.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public Entry<K, V> getEntry(){
		return (Entry<K, V>) cacheMap.entrySet();
	}
	
	public Set<K> getKeySet(){
		return cacheMap.keySet();
	}
	
	public Collection<V> getValues(){
		return cacheMap.values();
	}
	
	public void clear(){
		cacheMap.clear();
	}
	
	public void forlist(){
		for(Entry<K, V> entry: cacheMap.entrySet()){
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}
	}
	
	public static void main(String args[]){
		LRUCacheMap<String, Integer> cache = new LRUCacheMap<String, Integer>(3);
		cache.set("A", 1);
		cache.set("B", 2);
		cache.set("C", 3);
		cache.set("D", 4);
		
		cache.forlist();
		
//		System.out.println(cache.get("A"));
//		System.out.println(cache.get("B"));
//		System.out.println(cache.get("C"));
//		System.out.println(cache.get("D"));


	}
	
}
