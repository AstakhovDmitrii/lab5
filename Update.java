package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;
import com.company.Models.Ticket;

import java.util.Map;

public class Update implements Command {
    @Override
    public void Execute(String command) {
        String[] args = command.split(",",2);
        if(args.length == 2){
            for (Map.Entry<String, Ticket> t : Main.tickets.getTickets().entrySet()) {
                if(t.getValue().getId().equals(Integer.parseInt(args[1]))){
                    Main.tickets.getTickets().replace(t.getKey(), Main.Set_Fields());
                    Printer.getInstance().WriteLine("успех");
                    break;
                }
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
