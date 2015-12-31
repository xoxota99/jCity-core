package com.jcity.util;

public class Pair<F, S> {
	private F first; // first member of pair
	private S second; // second member of pair

	// public static Pair<F,S> of<F,S> (F first, S second);
	public Pair() {
	}

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}
}