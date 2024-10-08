package ru.specialist;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.*;


public class Cache<K, V> {
	
	volatile private  Map<K,V> map = new HashMap<K,V>();
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Lock lockRead = lock.readLock();
	private Lock lockWrite = lock.writeLock();
	
	
	public V get(K key) {
		lockRead.lock();
		try {
			return map.get(key);
		}
		finally {
			lockRead.unlock();
		}
	}
	
	public void put(K key, V value) {
		lockWrite.lock();
		try {
			map.put(key,  value);
		}
		finally {
			lockWrite.unlock();
		}
	}	

}
