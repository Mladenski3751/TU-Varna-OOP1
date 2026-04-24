package Shapes;

public abstract class Shape {
    private String color;

    protected Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    protected String fmt(double val) {
        if (val == (long) val) {
            return String.valueOf((long) val);
        }
        return String.valueOf(val);
    }

    public abstract void shift(double dx, double dy);
    public abstract String convertToSvg();
    public abstract String describe();
    public abstract boolean isContainedInRect(double rx, double ry, double rw, double rh);

}