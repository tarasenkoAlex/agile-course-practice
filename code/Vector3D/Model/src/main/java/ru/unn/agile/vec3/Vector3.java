package ru.unn.agile.vec3;

import java.util.Locale;

public class Vector3 {
    private double x;
    private double y;
    private double z;

    private enum Vector3Status {
        ERROR_NORMALIZE {
            public String toString() {
                return "Norm of vector is small";
            }
        }
    }

    private static final int HASH_FACTOR = 31;


    public Vector3() {
        //
    }

    public Vector3(final double x,
                   final double y,
                   final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(final double[] array) {
        x = array[0];
        y = array[1];
        z = array[2];
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        final long hashX = Double.doubleToLongBits(x);
        final long hashY = Double.doubleToLongBits(y);
        final long hashZ = Double.doubleToLongBits(z);

        return HASH_FACTOR * Long.hashCode(hashX)
             + HASH_FACTOR * Long.hashCode(hashY)
             + HASH_FACTOR * Long.hashCode(hashZ);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Vector3) {
            return equals((Vector3) obj);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "(%.4f, %.4f, %.4f)", this.x, this.y, this.z);
    }

    public boolean equals(final double x,
                          final double y,
                          final double z) {
        return Math.abs(this.x - x) < Double.MIN_VALUE
            && Math.abs(this.y - y) < Double.MIN_VALUE
            && Math.abs(this.z - z) < Double.MIN_VALUE;
    }

    public boolean equals(final Vector3 vec) {
        return equals(vec.x, vec.y, vec.z);
    }

    public double getNorm() {
        return Math.sqrt(dot(this));
    }

    public void normalize() {
        final double norm = getNorm();

        if (norm < Double.MIN_VALUE) {
            throw new ArithmeticException(Vector3Status.ERROR_NORMALIZE.toString());
        }

        x /= norm;
        y /= norm;
        z /= norm;
    }

    public double dot(final Vector3 vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    public Vector3 cross(final Vector3 vec) {
        Vector3 vector = new Vector3(y * vec.z - z * vec.y,
                                     z * vec.x - x * vec.z,
                                     x * vec.y - y * vec.x);

        try {
            vector.normalize();
        } catch (Throwable t) {
            vector = new Vector3(Double.NaN, Double.NaN, Double.NaN);
        }

        return vector;
    }

    public boolean isNaN() {
        return Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z);
    }
}
