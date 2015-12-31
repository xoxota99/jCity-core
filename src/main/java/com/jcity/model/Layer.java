package com.jcity.model;

import java.awt.*;

import javax.vecmath.*;

/**
 * Represents a Layer of grid-based integer data in the World. Essentially a
 * 2-dimensional array of integers, with some utility functions.
 */
public class Layer {

	private int[][] values;
	private Image image;
	private double resolutionX;
	private double resolutionY;

	public Layer(int width, int height, double resolutionX, double resolutionY) {
		values = new int[width][height];
		this.resolutionX=resolutionX;
		this.resolutionY=resolutionY;
	}

	public int getWidth(){
		return values.length;
	}
	public int getHeight(){
		return values[0].length;
	}
	public Layer() {
	}

	/**
	 * Returns a value of an image map at an given world position. Bilinear
	 * interpolation is used and the return value is between 0.0 and 1.0.
	 * 
	 * @param x
	 *            position in world coordinates
	 * @param y
	 *            position in world coordinates
	 * @return the interpolated value from the map at coordinates (x, y).
	 **/
	public double getInterpolatedValue(double x, double y) {

		double value = 0D;
		double xratio = ((double) values.length - 1) / resolutionX;
		double yratio = ((double) values[0].length - 1) / resolutionY;
		double mx = x * xratio;
		double my = y * yratio;
		double u = mx - (int) mx;
		double v = my - (int) my;
		int xl = (int) (mx < 0 ? 0 : mx >= values.length ? values.length-1 : mx);
		int xr = xl + 1;
		int yb = (int) (my < 0 ? 0 : my >= values[0].length ? values[0].length-1 : my);
		int yt = yb + 1;

		// t2 ---------- t4
		// | |
		// | +(mx,my) |
		// | |
		// t1 ---------- t3
		int t1, t2, t3, t4;

		t1 = values[xl][yb];
		try {
			t2 = values[xl][yt];
		} catch (Exception e) {
			t2 = t1;
		}
		try {
			t3 = values[xr][yb];
		} catch (Exception e) {
			t3 = t1;
		}
		try {
			t4 = values[xr][yt];
		} catch (Exception e) {
			t4 = t2;
		}

		// do bilinear interpolation
		value = ((1.0 - u) * (1.0 - v) * t1 + u * (1.0 - v) * t3 + (1.0 - u) * v * t2 + u * v * t4) / 255.0;
		return value;
	}

	/**
	 * Returns a value of an image map at an given world position. Value is
	 * between 0 and 255.
	 * 
	 * @param x
	 *            position in world coordinates (0..RESOLUTION_X)
	 * @param y
	 *            position in world coordinates (0..RESOLUTION_Y)
	 * 
	 * @return the value in the map at coordinates (x, y). Values are 0..255
	 **/
	public int getValue(double x, double y) {
		double xratio = ((double) values.length - 1) / resolutionX;
		double yratio = ((double) values[0].length - 1) / resolutionY;
		int mx = (int) (x * xratio);
		int my = (int) (y * yratio);
		mx = (int) (mx < 0 ? 0 : mx >= values.length ? values.length-1 : mx);
		my = (int) (my < 0 ? 0 : my >= values[0].length ? values[0].length-1 : my);

		if (values[mx][my] < 0 || values[mx][my] > 255) {
			throw new IllegalArgumentException("Map value at (" + x + ", " + y + ") > 255!!!!");
		}
		return values[mx][my];
	}

	public int getValue(Tuple2d t) {
		return getValue(t.x, t.y);
	}

	public int getValue(Tuple3d t) {
		return getValue(t.x, t.y);
	}

	public double getInterpolatedValue(Tuple2d t) {
		return getInterpolatedValue(t.x, t.y);
	}

	public double getInterpolatedValue(Tuple3d t) {
		return getInterpolatedValue(t.x, t.y);
	}

	public int[][] getValues() {
		return values;
	}

	public void setValues(int[][] values) {
		this.values = values;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getResolutionX() {
		return resolutionX;
	}

	public double getResolutionY() {
		return resolutionY;
	}
}
