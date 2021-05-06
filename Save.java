package com.company.Commands;

import com.company.Command;
import com.company.Converter;
import com.company.Main;
import com.company.Models.Printer;

public class Save implements Command {
    @Override
    public void Execute(String command) throws Exception {
        String[] strings = command.split(",", 2);
        if(strings.length == 2){
            Converter.getInstance().write(strings[1]);
        }
        else{
            Printer.getInstance().WriteLine("введите путь");
            Execute(getName() + "," + Printer.getInstance().ReadLine());
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
