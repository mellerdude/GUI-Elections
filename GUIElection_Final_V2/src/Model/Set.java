package Model;

import java.util.Vector;

public class Set<T extends Citizen> {
	protected Vector<T> list;

	public Set() {
		list = new Vector<T>();
	}

	public Boolean add(T obj) {
		if(!list.contains(obj)) {
			list.add(obj);
			return true;
		}
		return false;
	}

	public T get(int index) {
		return list.get(index);
	}
}
