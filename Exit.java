package com.company.Commands;

import com.company.Command;
import com.company.Models.Printer;

public class Exit implements Command {
    @Override
    public void Execute(String command) {
        try {
            System.exit(0);
        }
        catch (Exception e){
            Printer.getInstance().WriteLine(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
