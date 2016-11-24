package ru.unn.agile.vector3d.model;

import java.util.Locale;

public class Vector3D {
    private final double x;
    private final double y;
    private final double z;

    private static final int HASH_FACTOR = 31;

    public Vector3D() {
        this(0, 0, 0);
    }

    public Vector3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(final double[] array) {
        x = array[0];
        y = array[1];
        z = array[2];
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
        if (obj instanceof Vector3D) {
            return equals((Vector3D) obj);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "(%.4f, %.4f, %.4f)", this.x, this.y, this.z);
    }

    public boolean equals(final double x, final double y, final double z) {
        return Math.abs(this.x - x) < Double.MIN_VALUE
            && Math.abs(this.y - y) < Double.MIN_VALUE
            && Math.abs(this.z - z) < Double.MIN_VALUE;
    }

    public boolean equals(final Vector3D vec) {
        return equals(vec.x, vec.y, vec.z);
    }

    public double getNorm() {
        return Math.sqrt(dot(this));
    }

    public Vector3D normalize() {
        final double norm = getNorm();

        if (norm < Double.MIN_VALUE) {
            return new Vector3D(0, 0, 0);
        } else {
            return new Vector3D(x / norm, y / norm, z / norm);
        }
    }

    public double dot(final Vector3D vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    public Vector3D cross(final Vector3D vec) {
        Vector3D vector = new Vector3D(y * vec.z - z * vec.y,
                                       z * vec.x - x * vec.z,
                                       x * vec.y - y * vec.x);

        vector.normalize();

        return vector;
    }
}
