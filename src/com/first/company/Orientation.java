package com.first.company;

import java.io.ObjectStreamException;
import java.io.Serializable;




//序列化时，如何处理好单例 和类型安全的枚举
public class Orientation implements Serializable {
	public static final Orientation HORIZATION = new Orientation(1);
	public static final Orientation VERTICAL = new Orientation(2);
	private int direct = 0;

	private Orientation(int i) {
		direct = i;
	}

	protected Object readResolve() throws ObjectStreamException {

		if (direct == 1) {
			return Orientation.HORIZATION;
		} else if (direct == 2) {
			return Orientation.VERTICAL;
		}

		return null;

	}

}