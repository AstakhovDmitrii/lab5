package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;

public class Remove_greater_key  implements Command {
    @Override
    public void Execute(String command) {
        try {
            String[] args = command.split(",", 2);
            if (args.length == 2) {
                for (String str : Main.tickets.getTickets().keySet()) {// получаем все ключи
                    if (args[1].compareTo(str) > 0) {//удаляем если ключ больше
                        Main.tickets.getTickets().remove(str);
                    }
                }
            } else {
                Printer.getInstance().WriteLine("введите ключ");
                Execute(getName() + "," + Printer.getInstance().ReadLine());
            }
        }
        catch (Exception we){
            Printer.getInstance().WriteLine(we.getMessage());
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
