package ru.unn.agile.matrixoperations.model;

import java.util.Arrays;

public class Matrix {

    public enum Operation {
        ADD("Add") {
            public Matrix apply(final Matrix l, final Matrix r) {
                return Matrix.sum(l, r);
            }
        },
        MULTIPLY("Multiply") {
            public Matrix apply(final Matrix l, final Matrix r) {
                return Matrix.multiply(l, r);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract Matrix apply(Matrix l, Matrix r);
    }

    private int rows;
    private int columns;
    private float[] elements;

    public Matrix(final int rows, final int columns) {
        this(rows, columns, null);
    }

    public Matrix(final int rows, final int columns, final float[] data) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Rows count must be positive.");
        }
        if (columns <= 0) {
            throw new IllegalArgumentException("Columns count must be positive.");
        }
        privateCopyData(rows, columns, data);
    }

    public Matrix(final Matrix source) {
        if (source == null) {
            throw new IllegalArgumentException("Source matrix cannot be null.");
        }
        privateCopyData(source.getRows(), source.getColumns(), source.elements);
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getSize() {
        return rows * columns;
    }

    public void updateDimensions(final int rows, final int cols) {
        privateCopyData(rows, cols, null);
    }

    public float getElement(final int index) {
        if (index < 0 || index >= rows * columns) {
            throw new IndexOutOfBoundsException();
        }
        return this.elements[index];
    }

    public void setElement(final int index, final float element) {
        if (index < 0 || index >= rows * columns) {
            throw new IndexOutOfBoundsException();
        }
        this.elements[index] = element;
    }

    public float getElement(final int rowIndex, final int columnIndex) {
            if (rowIndex < 0 || rowIndex >= rows) {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
        if (columnIndex < 0 || columnIndex >= columns) {
            throw new IndexOutOfBoundsException("Column index out of bounds");
        }
        return this.elements[rowIndex * columns + columnIndex];
    }

    public void setElement(final int rowIndex, final int columnIndex, final float element) {
        if (rowIndex < 0 || rowIndex >= rows) {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
        if (columnIndex < 0 || columnIndex >= columns) {
            throw new IndexOutOfBoundsException("Column index out of bounds");
        }
        this.elements[rowIndex * columns + columnIndex] = element;
    }

    public Matrix add(final Matrix added) {
        if (getRows() != added.getRows()) {
            throw new IllegalArgumentException("Adding matrix has unequals number of rows.");
        }
        if (getColumns() != added.getColumns()) {
            throw new IllegalArgumentException("Adding matrix has unequals number of columns.");
        }

        for (int idx = 0; idx < getSize(); idx++) {
            elements[idx] += added.getElement(idx);
        }

        return this;
    }

    public static Matrix sum(final Matrix first, final Matrix second) {
        Matrix m = new Matrix(first);
        return m.add(second);
    }

    public static Matrix multiply(final Matrix first, final Matrix second) {
        if (first.getColumns() != second.getRows()) {
            throw new IllegalArgumentException("Multiplier matrix not agreed");
        }

        Matrix result = new Matrix(first.getRows(), second.getColumns());
        for (int rowRes = 0; rowRes < result.getRows(); rowRes++) {
            for (int colRes = 0; colRes < result.getColumns(); colRes++) {
                float multRes = 0;
                for (int i = 0; i < first.getColumns(); i++) {
                    multRes += first.getElement(rowRes, i) * second.getElement(i, colRes);
                }
                result.setElement(rowRes, colRes, multRes);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("{");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                bld.append(elements[r * columns + c]);
                if (c < columns - 1) {
                    bld.append(",");
                }
            }
            if (r < rows - 1) {
                bld.append(", ");
            }
        }
        bld.append("}");
        return bld.toString();
    }

    private void privateCopyData(final int rows, final int columns, final float[] data) {
        this.rows = rows;
        this.columns = columns;
        if (data == null) {
            this.elements = new float[rows * columns];
        } else {
            this.elements = Arrays.copyOf(data, rows * columns);
        }
    }
}
