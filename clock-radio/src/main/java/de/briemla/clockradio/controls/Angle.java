package de.briemla.clockradio.controls;

public class Angle {

	/**
	 * Converts the given UI coordinates in a coordinate system lying in the center of width/height
	 * and returns the hour which is represented by this part of the coordinate system.
	 *
	 * @param absoluteX
	 * @param absoluteY
	 * @param width
	 * @param height
	 * @return
	 */
	public static Integer toHour(double absoluteX, double absoluteY, double width, double height) {
		double centerX = width / 2.0d;
		double centerY = height / 2.0d;
		double afternoonDistance = centerX * 0.72d * 0.8d;

		double relativeX = absoluteX - centerX;
		double relativeY = (height - absoluteY) - centerY;

		double distance = length(relativeX, relativeY);
		Integer afternoonOffset = distance > afternoonDistance ? 12 : 0;
		return afternoonOffset + relativeHour(relativeX, relativeY);
	}

	private static double length(double x, double y) {
		if (x == 0d && y == 0d) {
			return 0d;
		}
		return Math.sqrt(Math.pow(x, 2d) + Math.pow(y, 2d));
	}

	private static Integer relativeHour(double relativeX, double relativeY) {
		if (relativeX == 0d) {
			return relativeY > 0d ? 0 : 6;
		}

		if (relativeX > 0d && relativeY >= 0d) {
			double angle = Math.toDegrees(Math.atan(relativeY / relativeX)) + 15d;
			int someValue = (int) angle / 30;
			int hour = 3 - someValue;
			return hour;
		}
		if (relativeX > 0d && relativeY <= 0d) {
			double angle = Math.toDegrees(Math.atan(-relativeY / relativeX)) + 15d;
			int someValue = (int) angle / 30;
			int hour = 3 + someValue;
			return hour;
		}
		if (relativeX < 0d && relativeY <= 0d) {
			double angle = Math.toDegrees(Math.atan(relativeY / relativeX)) + 15d;
			int someValue = (int) angle / 30;
			int hour = 9 - someValue;
			return hour;
		}
		if (relativeX < 0d && relativeY >= 0d) {
			double angle = Math.toDegrees(Math.atan(relativeY / -relativeX)) + 15d;
			int someValue = (int) angle / 30;
			int hour = 9 + someValue;
			return hour % 12;
		}
		return 0;
	}
}
