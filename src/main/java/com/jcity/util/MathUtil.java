package com.jcity.util;

import javax.vecmath.*;

public class MathUtil {

	/**
	 * PI / 180
	 */
	public static final double PI180 = Math.PI / 180; // PI / 180

	/**
	 * Give distance between two 3Points in the XY plane.
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double distanceXY(Point3d p1, Point3d p2) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Normalize and rotate a vector in place.
	 * 
	 * @param alpha
	 *            - angle to rotate in degrees.
	 * @param vec
	 *            - normalized vector.
	 */
	public static void rotateNormalizedVector(double angle, Vector2d vec) {
		vec.x = Math.cos(angle * PI180);
		vec.y = Math.sin(angle * PI180);
	}

	/**
	 * Calculate angle of a vector to x axis.
	 * 
	 * @param x
	 *            vector
	 * @param y
	 *            vector
	 * @return angle in degrees
	 **/
	public static double angle(Vector2d dir) {
		// assumption: vector is normalized
		if ((dir.x >= 0.0) && (dir.y >= 0.0))
			return Math.asin(dir.y) / PI180; // sector I
		if ((dir.x < 0.0) && (dir.y >= 0.0))
			return 180.0 - Math.asin(dir.y) / PI180; // sector II
		if ((dir.x < 0.0) && (dir.y < 0.0))
			return 180.0 + Math.asin(-dir.y) / PI180; // sector III
		if ((dir.x >= 0.0) && (dir.y < 0.0))
			return 360.0 - Math.asin(-dir.y) / PI180; // sector IV
		return 0.0; // will never be reached
	}

	/**
	 * Return the slope (in the Z-Axis) between two points.
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static double gradient(Point3d start, Point3d end) {
		return Math.abs((end.z - start.z)) / distanceXY(start, end);
	}

	/**
	 * Returns a point calculated from a origin, distance and direction. z is
	 * always zero.
	 * 
	 * @param l
	 *            length or distance
	 * @param startPos
	 *            origin
	 * @param dir
	 *            direction
	 * @param endPos
	 *            position
	 */
	public static Point3d calculatePoint(double l, Point3d startPos, Vector2d dir) {
		// assumption: vector is normalized
		return new Point3d(startPos.x + l * dir.x, startPos.y + l * dir.y, 0);
	}

	public static Quat4d quatFromAngles(float xAngle, float yAngle, float zAngle) {

		Quat4d q = new Quat4d();
		float angle;
		float sinY, sinZ, sinX, cosY, cosZ, cosX;
		angle = zAngle * 0.5f;
		sinZ = (float) Math.sin(angle);
		cosZ = (float) Math.cos(angle);
		angle = yAngle * 0.5f;
		sinY = (float) Math.sin(angle);
		cosY = (float) Math.cos(angle);
		angle = xAngle * 0.5f;
		sinX = (float) Math.sin(angle);
		cosX = (float) Math.cos(angle);

		// variables used to reduce multiplication calls.
		float cosYXcosZ = cosY * cosZ;
		float sinYXsinZ = sinY * sinZ;
		float cosYXsinZ = cosY * sinZ;
		float sinYXcosZ = sinY * cosZ;

		q.w = (cosYXcosZ * cosX - sinYXsinZ * sinX);
		q.x = (cosYXcosZ * sinX + sinYXsinZ * cosX);
		q.y = (sinYXcosZ * cosX + cosYXsinZ * sinX);
		q.z = (cosYXsinZ * cosX - sinYXcosZ * sinX);

		q.normalize();

		return q;
	}

}
