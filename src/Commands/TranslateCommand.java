package Commands;

import Core.Editor;

/**
 * Команда за транслация (преместване) на една или всички фигури[cite: 135].
 */
public class TranslateCommand implements Command {
    private final Editor editor;

    /**
     * Конструктор за командата translate.
     * @param editor Редакторът, съдържащ фигурите за преместване.
     */
    public TranslateCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String getName() {
        return "translate";
    }

    /**
     * Изпълнява преместването на база на подадените аргументи[cite: 135].
     * Поддържа формати: [dx] [dy] или [номер] [dx] [dy].
     * @param args Параметри за транслация.
     * @return Съобщение за успешна транслация.
     */
    @Override
    public String execute(String[] args) {

        try {
            if (args.length == 2) {
                double dx = parseKeyValue(args[0]);
                double dy = parseKeyValue(args[1]);
                return editor.translateAll(dx, dy);

            } else if (args.length == 3) {
                int index = Integer.parseInt(args[0]);
                double dx = parseKeyValue(args[1]);
                double dy = parseKeyValue(args[2]);
                return editor.translateOne(index, dx, dy);

            } else {
                return "Error: wrong number of arguments for translate.";
            }
        } catch (Exception e) {
            return "Error: invalid arguments for translate.";
        }
    }

    /**
     * Помощен метод за извличане на числова стойност от формат ключ=стойност.
     * @param token Низ от типа "vertical=10".
     * @return Числовата стойност след знака "=".
     */
    private double parseKeyValue(String token) {
        String[] parts = token.split("=");
        return Double.parseDouble(parts[1]);
    }
}