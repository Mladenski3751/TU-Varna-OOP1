package Commands;

import Core.Editor;
import java.util.List;

/**
 * Команда за извеждане на фигури, намиращи се в определен регион[cite: 136].
 */
public class WithinCommand implements Command {
    private final Editor editor;

    /**
     * Конструктор за командата within.
     * @param editor Редакторът, в който ще се търси.
     */
    public WithinCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String getName() {
        return "within";
    }

    /**
     * Проверява кои фигури се съдържат изцяло в указания регион[cite: 136].
     * @param args [0] тип регион, [1-4] координати и размери.
     * @return Списък с намерените фигури.
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 5) {
            return "Error: within requires type and 4 coordinates.";
        }

        String regionType = args[0];

        if (regionType.equals("rectangle")) {
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            double w = Double.parseDouble(args[3]);
            double h = Double.parseDouble(args[4]);

            List<String> found = editor.findWithinRect(x, y, w, h);
            if (found.isEmpty()) {
                return "No figures are located within " + regionType + " "
                        + args[1] + " " + args[2] + " " + args[3] + " " + args[4];
            }
            StringBuilder sb = new StringBuilder();
            for (String f : found) {
                sb.append(f).append("\n");
            }
            return sb.toString().trim();
        }

        return "Error: unsupported region type \"" + regionType + "\".";
    }
}