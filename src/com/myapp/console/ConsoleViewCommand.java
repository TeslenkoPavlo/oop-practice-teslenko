
package com.myapp.console;

import com.myapp.viewimpl.ViewableTable;

/**
 * Консольна команда для перегляду таблиці
 * @author xone
 * @version 1.0
 */
public class ConsoleViewCommand implements IConsoleCommand {
    private ViewableTable viewableTable;
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public ConsoleViewCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }

    @Override
    public char getKey() {
        return 'д';
    }

    @Override
    public String toString() {
        return "'д'ивитися";
    }

    @Override
    public void execute() {
        System.out.println(ANSI_CYAN + "╔═════════════════════════════════════════════════╗");
        System.out.println("║ 🔍 Перегляд таблиці координат 📊                ║");
        System.out.println("╚═════════════════════════════════════════════════╝" + ANSI_RESET);
        
        // Виклик методу перегляду таблиці
        viewableTable.viewShow();
    }
}
