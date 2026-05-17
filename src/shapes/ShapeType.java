package shapes;

/**
 * Изброен тип за поддържаните видове фигури.
 * Използва се в ShapeFactory за избягване на сравнение на стрингове.
 */
public enum ShapeType {

    /** Кръг. */
    CIRCLE("circle"),

    /** Отсечка. */
    LINE("line"),

    /** Правоъгълник. */
    RECTANGLE("rectangle");

    /** Текстовото представяне на типа, използвано в командите и SVG файловете. */
    private final String label;

    /**
     * @param label текстовото представяне на типа
     */
    ShapeType(String label) {
        this.label = label;
    }

    /**
     * Връща текстовото представяне на типа.
     *
     * @return label стринга
     */
    public String getLabel() {
        return label;
    }

    /**
     * Търси ShapeType по текстово представяне.
     * Връща null ако не е намерен съответстващ тип.
     *
     * @param label текстът за търсене
     * @return съответният ShapeType или null
     */
    public static ShapeType fromLabel(String label) {
        for (ShapeType type : values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        return null;
    }
}