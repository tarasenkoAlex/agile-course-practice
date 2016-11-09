package ru.unn.agile.VolumeCalculator.Model;

/**3.14159265359;
 * Created by a.tarasenko on 09.11.2016.
 */
public class VolumeCalculator {

    private static final double PI = 3.14159265359;
    private static final double SPHERE_VOLUME_MULTIPLIER = 1.33333333333;
    private static final double CONE_VOLUME_MULTIPLIER = 0.33333333333;
    private static final double PYRAMID_VOLUME_MULTIPLIER = 0.33333333333;
    private static final double TETRAHENDRON_VOLUME_MULTIPLIER = 0.11785113019;


    public double getSphereValue(final double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius must not be negative!");
        }

        return SPHERE_VOLUME_MULTIPLIER * PI * radius * radius * radius;
    }

    public double getCubeValue(final double edge) {
        if (edge < 0) {
            throw new IllegalArgumentException("Edge must not be negative!");
        }

        return edge * edge * edge;
    }

    public double getConeValue(final double radius, final double height) {
        if (radius < 0 || height < 0) {
            throw new IllegalArgumentException("Raduis and height must not be negative!");
        }

        return CONE_VOLUME_MULTIPLIER * PI * radius * radius * height;
    }

    public double getCylinderValue(final double radius, final double height) {
        if (radius < 0 || height < 0) {
            throw new IllegalArgumentException("Raduis and height must not be negative!");
        }

        return PI * radius * radius * height;
    }

    public double getPyramidValue(final double area, final double height) {
        if (area < 0 || height < 0) {
            throw new IllegalArgumentException("Area and height must not be negative!");
        }

        return PYRAMID_VOLUME_MULTIPLIER * area * height;
    }

    public double getTetrahedronValue(final double edge) {
        if (edge < 0) {
            throw new IllegalArgumentException("Edge must not be negative!");
        }

        return TETRAHENDRON_VOLUME_MULTIPLIER * edge * edge * edge;
    }
}
