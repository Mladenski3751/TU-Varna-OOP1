package Shapes;

/**
 * Абстрактен клас, представляващ базова геометрична фигура.
 * Дефинира общите интерфейси за всички фигури в SVG редактора.
 */
public abstract class Shape {
    private String color;

    /**
     * Конструктор за създаване на фигура със специфичен цвят.
     * @param color Цвят на фигурата (напр. "red", "green").
     */
    protected Shape(String color) {
        this.color = color;
    }

    /**
     * Връща цвета на фигурата.
     * @return Текстово представяне на цвета.
     */
    public String getColor() {
        return color;
    }

    /**
     * Помощен метод за форматиране на числа (премахва .0, ако числото е цяло).
     * @param val Стойността за форматиране.
     * @return Форматиран низ.
     */
    protected String fmt(double val) {
        if (val == (long) val) {
            return String.valueOf((long) val);
        }
        return String.valueOf(val);
    }

    /**
     * Транслира (премества) фигурата по координатите X и Y.
     * @param dx Стойност за преместване по хоризонтала.
     * @param dy Стойност за преместване по вертикала.
     */
    public abstract void shift(double dx, double dy);

    /**
     * Генерира SVG елемент като низ за запис във файл.
     * @return XML/SVG таг на фигурата.
     */
    public abstract String convertToSvg();

    /**
     * Връща текстово описание на фигурата за командата 'print'.
     * @return Описание на параметрите на фигурата.
     */
    public abstract String describe();

    /**
     * Проверява дали фигурата се съдържа изцяло в даден правоъгълен регион.
     * @param rx X координата на региона.
     * @param ry Y координата на региона.
     * @param rw Ширина на региона.
     * @param rh Височина на региона.
     * @return true, ако е вътре в региона; false в противен случай.
     */
    public abstract boolean isContainedInRect(double rx, double ry, double rw, double rh);
}