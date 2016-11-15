package ru.unn.agile.VolumeCalculator.Model;

/**3.14159265359;
 * Created by a.tarasenko on 09.11.2016.
 */
public class VolumeCalculator {

    public double getSphereVolume(final double radius) {
        checkParams("Radius must not be negative!", radius);

        return VolumeMultiplier.SPHERE * Math.PI * radius * radius * radius;
    }

    public double getCubeVolume(final double edge) {
        checkParams("Edge must not be negative!", edge);

        return edge * edge * edge;
    }

    public double getConeVolume(final double radius, final double height) {
        checkParams("Raduis and height must not be negative!", radius, height);

        return VolumeMultiplier.CONE * Math.PI * radius * radius * height;
    }

    public double getCylinderVolume(final double radius, final double height) {
        checkParams("Raduis and height must not be negative!", radius, height);

        return Math.PI * radius * radius * height;
    }

    public double getPyramidVolume(final double area, final double height) {
        checkParams("Area and height must not be negative!", area, height);

        return VolumeMultiplier.PYRAMID * area * height;
    }

    public double getTetrahedronVolume(final double edge) {
        checkParams("Edge must not be negative!", edge);

        return VolumeMultiplier.TETRAHENDRON * edge * edge * edge;
    }

    private void checkParams(final String message, final double... params) {
        for (double param : params) {
            if (param < 0) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
