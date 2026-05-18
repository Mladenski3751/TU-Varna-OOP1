package commands;

import core.Editor;

/**
 * Команда за транслация (преместване) на една или всички фигури.
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
     * Изпълнява преместването на база на подадените аргументи.
     * Поддържа формати: [dx] [dy] или [номер] [dx] [dy].
     * @param args Параметри за транслация.
     * @return Съобщение за успешна транслация.
     */
    @Override
    public String execute(String[] args) {

        try {
            if (args.length == 2) {
                double vertical   = parseKey(args, "vertical");
                double horizontal = parseKey(args, "horizontal");
                return editor.translateAll(horizontal, vertical);

            } else if (args.length == 3) {
                int index         = Integer.parseInt(args[0]);
                double vertical   = parseKey(args, "vertical");
                double horizontal = parseKey(args, "horizontal");
                return editor.translateOne(index, horizontal, vertical);

            } else {
                return "Error: wrong number of arguments for translate.";
            }
        } catch (Exception e) {
            return "Error: invalid arguments for translate.";
        }
    }

    /**
     * Търси токен по ключ и връща числовата му стойност.
     */
    private double parseKey(String[] args, String key) {
        for (String arg : args) {
            if (arg.startsWith(key + "=")) {
                return Double.parseDouble(arg.split("=")[1]);
            }
        }
        return 0.0;
    }
}