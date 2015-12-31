package com.jcity.util;

public class IntPair {
	// Doing this, rather than IntPair<T,J>, since we only ever use it for
	// integers, and comparison operators were causing problems when using boxed
	// integers.
	public int first;
	public int second;

	public IntPair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public IntPair() {
	}

}
