import java.util.List;
import java.util.Locale;

public class HelpCommand implements Command{

    private final String name = "help";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("Недопустимое число аргументов");
        } else {
            return help();
        }
    }

    @Override
    public String help() {
        List<String> list = ShellBase.getInstance().getAll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Доступные команды: ");
        for (String s: list) {
            stringBuilder.append("\n");
            stringBuilder.append(" - ");
            stringBuilder.append(s);
        }
        stringBuilder.append("\nдобавьте '/help' к названию команды, чтобы узнать подробности");
        return stringBuilder.toString();
    }
}
