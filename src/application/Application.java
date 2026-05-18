package application;

import commands.*;
import core.Editor;
import io.SvgFileHandler;
import shapes.Shape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Основен клас на приложението.
 * Управлява Command Line Interface (CLI) и жизнения цикъл на SVG редактора.
 */
public class Application {
    private final Editor editor = new Editor();
    private final SvgFileHandler fileHandler = new SvgFileHandler();
    private final CommandRegistry registry = new CommandRegistry();

    private String currentFile = null;
    private boolean fileOpen = false;
    private boolean running = true;

    /**
     * Инициализира приложението и регистрира всички поддържани команди.
     */
    public Application() {
        registry.register(new PrintCommand(editor));
        registry.register(new CreateCommand(editor));
        registry.register(new EraseCommand(editor));
        registry.register(new TranslateCommand(editor));
        registry.register(new WithinCommand(editor));
        registry.register(new HelpCommand());
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

            handleInput(input);
        }
    }

    /**
     * Обработва входа и насочва към системни команди или регистрирани операции.
     * @param input Целият команден низ.
     */
    private void handleInput(String input) {
        String[] tokens = input.split("\\s+");
        String commandName = tokens[0].toLowerCase();

        if (commandName.equals("open")) {
            handleOpen(tokens);
            return;
        }
        if (commandName.equals("close")) {
            handleClose();
            return;
        }
        if (commandName.equals("save")) {
            if (tokens.length > 1 && tokens[1].equalsIgnoreCase("as")) {
                handleSaveAs(tokens);
            } else {
                handleSave();
            }
            return;
        }
        if (commandName.equals("exit")) {
            System.out.println("Exiting the program...");
            running = false;
            return;
        }

        if (!fileOpen) {
            System.out.println("Error: no file is currently open.");
            return;
        }

        if (registry.has(commandName)) {
            String[] args = new String[tokens.length - 1];
            System.arraycopy(tokens, 1, args, 0, args.length);
            String result = registry.get(commandName).execute(args);
            System.out.println(result);
        } else {
            System.out.println("Error: unknown command \"" + commandName + "\".");
        }
    }

    private void handleOpen(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Error: open requires a file path.");
            return;
        }

        String path = tokens[1];

        try {
            editor.clear();
            List<Shape> shapes = fileHandler.load(path);
            for (Shape s : shapes) {
                editor.addShape(s);
            }
            currentFile = path;
            fileOpen = true;
            System.out.println("Successfully opened " + path);
        } catch (Exception e) {
            editor.clear();
            currentFile = path;
            fileOpen = true;
            System.out.println("Successfully opened " + path);
        }
    }

    private void handleClose() {
        if (!fileOpen) {
            System.out.println("Error: no file is currently open.");
            return;
        }
        String name = currentFile;
        editor.clear();
        currentFile = null;
        fileOpen = false;
        System.out.println("Successfully closed " + name);
    }

    private void handleSave() {
        if (!fileOpen) {
            System.out.println("Error: no file is currently open.");
            return;
        }
        try {
            fileHandler.save(currentFile, editor.toSvg());
            System.out.println("Successfully saved the changes to " + currentFile);
        } catch (Exception e) {
            System.out.println("Error: could not save file.");
        }
    }

    private void handleSaveAs(String[] tokens) {
        if (tokens.length < 3) {
            System.out.println("Error: save as requires a file path.");
            return;
        }
        String path = tokens[2];
        try {
            fileHandler.save(path, editor.toSvg());
            System.out.println("Successfully saved " + path);
        } catch (Exception e) {
            System.out.println("Error: could not save to " + path);
        }
    }

    /**
     * Точка за стартиране на приложението.
     * @param args Командни параметри (не се използват).
     */
    public static void main(String[] args) {
        new Application().run();
    }
}