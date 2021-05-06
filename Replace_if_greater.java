package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;
import com.company.Models.Ticket;

public class Replace_if_greater  implements Command {
    @Override
    public void Execute(String command) {
        String[] args = command.split(",",2);
        if(args.length == 2){
            Ticket ticket = Main.Set_Fields();
            if(Main.tickets.getTickets().get(args[1]) != null) {
                if (Main.tickets.getTickets().get(args[1]).compareTo(ticket) > 0) {
                    Main.tickets.getTickets().replace(args[1], ticket);
                    Printer.getInstance().WriteLine("успех");
                }
                else{
                    Printer.getInstance().WriteLine("неудача");
                }
            }
            else{
                Printer.getInstance().WriteLine("такого нет");
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
