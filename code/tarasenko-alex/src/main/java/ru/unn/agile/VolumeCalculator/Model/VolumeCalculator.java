package ru.unn.agile.VolumeCalculator.Model;

/**3.14159265359;
 * Created by a.tarasenko on 09.11.2016.
 */
public class VolumeCalculator {

    public double getSphereVolume(final double radius) {
        if (!isCheckParams(radius)) {
            throw new IllegalArgumentException("Radius must not be negative!");
        }

        return VolumeMultiplier.SPHERE * Math.PI * radius * radius * radius;
    }

    public double getCubeVolume(final double edge) {
        if (!isCheckParams(edge)) {
            throw new IllegalArgumentException("Edge must not be negative!");
        }

        return edge * edge * edge;
    }

    public double getConeVolume(final double radius, final double height) {
        if (!isCheckParams(radius, height)) {
            throw new IllegalArgumentException("Raduis and height must not be negative!");
        }

        return VolumeMultiplier.CONE * Math.PI * radius * radius * height;
    }

    public double getCylinderVolume(final double radius, final double height) {
        if (!isCheckParams(radius, height)) {
            throw new IllegalArgumentException("Raduis and height must not be negative!");
        }

        return Math.PI * radius * radius * height;
    }

    public double getPyramidVolume(final double area, final double height) {
        if (!isCheckParams(area, height)) {
            throw new IllegalArgumentException("Area and height must not be negative!");
        }

        return VolumeMultiplier.PYRAMID * area * height;
    }

    public double getTetrahedronVolume(final double edge) {
        if (!isCheckParams(edge)) {
            throw new IllegalArgumentException("Edge must not be negative!");
        }

        return VolumeMultiplier.TETRAHENDRON * edge * edge * edge;
    }

    private boolean isCheckParams(final double... params) {
        for (double param : params) {
            if (param < 0) {
                return false;
            }
        }

        return true;
    }
}
