package Shapes;

public abstract class Shape{
    private String hexColor;


    protected Shape(String color){
        this.hexColor = color;
    }

    public String getColor() {
        return hexColor;
    }

    public abstract void shift(double x, double y);
    public abstract String convertToSvg();
    public abstract String describe();

    public abstract boolean isContainedInRect(double rx, double ry, double rw, double rh);
}
