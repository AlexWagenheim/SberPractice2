import java.util.Locale;

public class QuitCommand implements Command{

    private final String name = "quit";

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
            throw new IllegalArgumentException("Недопустимый аргумент. Введите 'quit /help' для получения справки");
        } else {
            System.exit(0);
            return null;
        }
    }

    @Override
    public String help() {
        return "Команда 'quit' - завершает работу приложения.";
    }
}
