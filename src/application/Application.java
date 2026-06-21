package application;

import commands.*;
import core.Editor;
import io.SvgFileHandler;
import shapes.Shape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Основен клас на приложението.
 * Управлява Command Line Interface (CLI) и жизнения цикъл на SVG редактора.
 * Системните команди са регистрирани като анонимни имплементации на Command,
 * което елиминира if-else веригите и позволява полиморфично разпращане.
 */
public class Application {
    private final Editor editor = new Editor();
    private final SvgFileHandler fileHandler = new SvgFileHandler();
    private final CommandRegistry registry = new CommandRegistry();

    private String currentFile = null;
    private boolean fileOpen = false;
    private boolean running = true;

    /**
     * Инициализира приложението и регистрира всички команди полиморфично.
     * Системните команди (open, close, save, exit) са анонимни Command обекти.
     */
    public Application() {
        registry.register(new Command() {
            @Override public String getName() { return "open"; }
            @Override public String execute(String[] args) {
                if (args.length < 1) return "Error: open requires a file path.";
                String path = args[0];
                editor.clear();
                try {
                    List<Shape> shapes = fileHandler.load(path);
                    for (Shape s : shapes) editor.addShape(s);
                } catch (Exception ignored) {}
                currentFile = path;
                fileOpen = true;
                return "Successfully opened " + path;
            }
        });

        registry.register(new Command() {
            @Override public String getName() { return "close"; }
            @Override public String execute(String[] args) {
                String name = currentFile;
                editor.clear();
                currentFile = null;
                fileOpen = false;
                return "Successfully closed " + name;
            }
        });

        registry.register(new Command() {
            @Override public String getName() { return "save"; }
            @Override public String execute(String[] args) {
                try {
                    fileHandler.save(currentFile, editor.toSvg());
                    return "Successfully saved the changes to " + currentFile;
                } catch (Exception e) {
                    return "Error: could not save file.";
                }
            }
        });

        registry.register(new Command() {
            @Override public String getName() { return "save as"; }
            @Override public String execute(String[] args) {
                if (args.length < 1) return "Error: save as requires a file path.";
                try {
                    fileHandler.save(args[0], editor.toSvg());
                    return "Successfully saved " + args[0];
                } catch (Exception e) {
                    return "Error: could not save to " + args[0];
                }
            }
        });

        registry.register(new Command() {
            @Override public String getName() { return "exit"; }
            @Override public String execute(String[] args) {
                running = false;
                return "Exiting the program...";
            }
        });

        registry.register(new HelpCommand());
        registry.register(new PrintCommand(editor));
        registry.register(new CreateCommand(editor));
        registry.register(new EraseCommand(editor));
        registry.register(new TranslateCommand(editor));
        registry.register(new WithinCommand(editor));
    }

    /**
     * Стартира безкраен цикъл за четене и изпълнение на потребителски команди.
     */
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (running) {
            System.out.print("> ");
            System.out.flush();

            String input;
            try {
                input = reader.readLine();
            } catch (Exception e) {
                System.out.println("Error reading input.");
                break;
            }

            if (input == null) break;
            input = input.trim();
            if (input.isEmpty()) continue;

            System.out.println(dispatch(input));
        }
    }

    /**
     * Разпраща входния низ към подходящата команда полиморфично.
     * Единственият специален случай е "save as" - двусловна команда.
     * @param input Целият команден низ от потребителя.
     * @return Резултатът от командата.
     */
    private String dispatch(String input) {
        String[] tokens = input.split("\\s+");
        String name = tokens[0].toLowerCase();

        if (name.equals("save") && tokens.length > 1 && tokens[1].equalsIgnoreCase("as")) {
            String[] args = tokens.length > 2 ? new String[]{tokens[2]} : new String[0];
            return executeCommand("save as", args);
        }

        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);
        return executeCommand(name, args);
    }

    /**
     * Изпълнява команда от регистъра с проверка за отворен файл.
     * Командите изискващи файл са дефинирани в fileRequiredCommands.
     * @param name Името на командата.
     * @param args Аргументите.
     * @return Резултатът от командата.
     */
    private String executeCommand(String name, String[] args) {
        if (!registry.has(name)) {
            return "Error: unknown command \"" + name + "\".";
        }
        if (requiresOpenFile(name) && !fileOpen) {
            return "Error: no file is currently open.";
        }
        return registry.get(name).execute(args);
    }

    private static final Set<String> FILE_COMMANDS = new HashSet<>(Arrays.asList(
            "print", "create", "erase", "translate", "within", "close", "save", "save as"
    ));

    /**
     * Проверява дали дадена команда изисква отворен файл.
     * @param name Името на командата.
     * @return true ако командата изисква отворен файл.
     */
    private boolean requiresOpenFile(String name) {
        return FILE_COMMANDS.contains(name);
    }

    /**
     * Точка за стартиране на приложението.
     * @param args Командни параметри (не се използват).
     */
    public static void main(String[] args) {
        new Application().run();
    }
}