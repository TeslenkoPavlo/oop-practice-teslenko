
package com.myapp.console;

import com.myapp.viewimpl.ViewableTable;

public class ConsoleViewCommand implements IConsoleCommand {
    private ViewableTable viewableTable;

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
        viewableTable.viewShow();
    }
}
