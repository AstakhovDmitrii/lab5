package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;

public class Remove_key  implements Command {
    @Override
    public void Execute(String command) {
        String[] args = command.split(",",2);
        if(args.length == 2){
            if(Main.tickets.getTickets().remove(args[1], Main.tickets.getTickets().get(args[1]))){
                Printer.getInstance().WriteLine("удаление успешно");
            }
            else{
                Printer.getInstance().WriteLine("удаление не удалось");
            }
        }
        else{
            Printer.getInstance().WriteLine("введите ключ");
            Execute(getName() + "," + Printer.getInstance().ReadLine());
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
