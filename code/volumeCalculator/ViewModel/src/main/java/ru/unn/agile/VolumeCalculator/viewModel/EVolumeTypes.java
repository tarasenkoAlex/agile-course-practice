package ru.unn.agile.VolumeCalculator.viewModel;

import ru.unn.agile.VolumeCalculator.Model.VolumeCalculator;

public enum EVolumeTypes {
    CUBE("Cube") {
        @Override
        public double getVolume(final double... args) {
            return CALCULATOR.getCubeVolume(args[0]);
        }
    },
    CONE("Cone") {
        @Override
        public double getVolume(final double... args) {
            return CALCULATOR.getConeVolume(args[0], args[1]);
        }
    },
    CYLINDER("Cylinder") {
        @Override
        public double getVolume(final double... args) {
            return CALCULATOR.getCylinderVolume(args[0], args[1]);
        }
    },
    PYRAMID("Pyramid") {
        @Override
        public double getVolume(final double... args) {
            return CALCULATOR.getPyramidVolume(args[0], args[1]);
        }
    },
    TETRAHEDRON("Tetrahedron") {
        @Override
        public double getVolume(final double... args) {
            return CALCULATOR.getTetrahedronVolume(args[0]);
        }
    },
    SPHERE("Sphere") {
        @Override
        public double getVolume(final double... args) {
            return CALCULATOR.getSphereVolume(args[0]);
        }
    };

    private final String name;
    private static final VolumeCalculator CALCULATOR = new VolumeCalculator();
    EVolumeTypes(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }

    public abstract double getVolume(double... args);
}
