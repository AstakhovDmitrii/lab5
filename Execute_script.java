package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;

import javax.swing.plaf.IconUIResource;
import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Execute_script implements Command {
    @Override
    public void Execute(String command) throws Exception {
        String[] strings = command.split(",", 2);
        if(strings.length == 2){
            Scanner scanner = new Scanner(new File(strings[1].trim()));// записываем файл в сканер. Пользуемся так же как и с консолью
            while (true){//бесуонечный цикл ввода команд
                try {
                    Printer.getInstance().WriteLine("введите команду");
                    String next = scanner.nextLine().trim();// очищаем начало и конец строки от пробелов, чтобы команда такого вида " Show" имела место быть
                    boolean isCommand = false;// провверка на то будет ли такая команда
                    for (Command commands : Main.commands) {// перебор всех команд. По сути это как for, только java проходит каждый обьект списка, и выдает нам его сама
                        if(!next.startsWith("Execute_script") && !next.startsWith("execute_script")) {
                            if (next.startsWith(commands.getName()) || next.startsWith(commands.getName().toLowerCase(Locale.ROOT))) {//проверка на команду(LowerCase для того чтобы можно было ввести
                                // и так Show и так show)
                                commands.Execute(next);// запуск команды
                                isCommand = true;//чтобы не выводилось, что нет такой команды
                            }
                        }
                        else{
                            Printer.getInstance().WriteLine("такой команды не должно быть");
                            break;
                        }
                    }
                    if (!isCommand) {
                        Printer.getInstance().WriteLine("нет такой команды");
                    }
                }
                catch (Exception e){
                    Printer.getInstance().WriteLine("вы каким то образом поломали программу, что ж вы за человек. Но я все сохраню, а то мало ли(((");
                }
            }
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
