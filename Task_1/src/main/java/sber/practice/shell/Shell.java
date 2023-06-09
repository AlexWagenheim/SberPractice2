package sber.practice.shell;

import java.rmi.NotBoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Shell {

    public static void main(String[] args) {
        ShellBase shellBase = ShellBase.getInstance();
        shellBase.setCommandList(Arrays.stream(new Command[]{
                new DateCommand(),
                new TimeCommand(),
                new PwdCommand(),
                new LsCommand(),
                new CdCommand(),
                new HelpCommand(),
                new QuitCommand()
        }).toList());
        Scanner input = new Scanner(System.in);

        while (true) {
            String commandLine = input.nextLine().trim();
            String[] command = commandLine.split(" ");
            try {
                System.out.println(shellBase.runCommand(command));
            } catch (IllegalArgumentException | NotBoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
