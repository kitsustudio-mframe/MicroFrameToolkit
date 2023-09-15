package net.kitsu.mframe;
import lombok.Getter;
import net.kitsu.lib.util.format.LoggerFormat;
import net.kitsu.lib.util.terminal.Terminal;
import net.kitsu.mframe.command.*;
import net.kitsu.mframe.util.ConfigLoader;
import net.kitsu.mframe.util.Arguments;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public final class Application extends Terminal implements Runnable{
    @Getter
    private static Application instance;

    @Getter
    private final Arguments arguments;
    @Getter
    private final Logger logger;
    @Getter
    private final IgnoreList ignoreFolder;
    @Getter
    private final ConfigLoader configLoader;
    private final Scanner scanner;
    private boolean start;

    public Application(String[] args){
        Application.instance = this;
        this.logger = this.setupLogger();
        this.scanner = new Scanner(System.in);
        this.arguments = new Arguments(args);
        this.configLoader = new ConfigLoader();

        this.commandHandlerMap.put("package", new CommandPackage());
        this.commandHandlerMap.put("exit", new CommandExit());
        this.commandHandlerMap.put("show", new CommandShow());
        this.commandHandlerMap.put("set", new CommandSet());
        this.commandHandlerMap.put("reload", new CommandReload());
        this.commandHandlerMap.put("regedit", new CommandRegister());
        this.start = true;

        this.ignoreFolder = new IgnoreList();
        this.ignoreFolder.reload(this.configLoader.getIgnoreFolder(), this.configLoader.getIgnoreFile());
    }

    public void stop(){
        this.start = false;
    }
    public void run() {
        while(this.start){
            this.logger.info("> ");
            this.execute(this.scanner.nextLine(), this.logger);
        }
    }

    private Logger setupLogger(){
        Logger result = Logger.getGlobal();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LoggerFormat());
        result.setUseParentHandlers(false);
        result.addHandler(consoleHandler);
        return result;
    }
}
