package sber.practice.shell;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ShellBase {

    private static ShellBase INSTANCE;
    private static List<Command> commandList;
    private static String currentDir;

    private ShellBase() {};

    public static ShellBase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShellBase();
            currentDir = System.getProperty("user.dir");
        }
        return INSTANCE;
    }

    public void setCommandList (List<Command> commandList) {
        this.commandList = commandList;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String current) {
        currentDir = current;
    }

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        for (Command command: commandList) {
            list.add(command.getName());
        }
        return list;
    }

    public String runCommand(String[] args) throws IllegalArgumentException, NotBoundException {
        Optional<Command> command = commandList.stream().filter(cmd -> cmd.getName().equals(args[0].toLowerCase(Locale.ROOT))).findAny();
        if (command.isPresent()) {
            return command.get().execute(args);
        } else {
            throw new NotBoundException(String.format("Не найдено команды с именем '%s'", args[0]));
        }
    }

}
