import java.io.File;
import java.util.Collection;
import java.util.Locale;

public class LsCommand implements Command{

    private final String name = "ls";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 2) {
            throw new IllegalArgumentException("Недопустимое число аргументов");
        } else if (args.length == 2) {
            switch (args[1].toLowerCase(Locale.ROOT)) {
                case ("/help"):
                    return help();
            }
            throw new IllegalArgumentException("Недопустимый аргумент. Введите 'ls /help' для получения справки");
        } else {
            File f = new File(ShellBase.getInstance().getCurrentDir());
            StringBuilder stringBuilder = new StringBuilder();
            for (File file: f.listFiles()) {
                stringBuilder.append(file.getName());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public String help() {
        return "Команда 'ls' - выводит список всех файлов папок в текущей рабочей директории.\n" +
                "   /help - вывести спраку по команде 'ls'";
    }
}
