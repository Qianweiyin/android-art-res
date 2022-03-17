package com.qwy.scan;

public class ResultPoint {


    private final float x;
    private final float y;

    public ResultPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public final float getX() {
        return x;
    }

    public final float getY() {
        return y;
    }


    @Override
    public final boolean equals(Object object) {
        if (object instanceof ResultPoint) {
            ResultPoint otherPoint = (ResultPoint) object;
            return x == otherPoint.x && y == otherPoint.y;
        }
        return false;
    }


    public final int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public final String toString() {
        return "(" + this.x + ',' + this.y + ')';
    }

}
