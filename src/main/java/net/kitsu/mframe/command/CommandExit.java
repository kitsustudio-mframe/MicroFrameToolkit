package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;
import net.kitsu.mframe.Application;

import java.util.logging.Logger;

public class CommandExit implements CommandHandler {
    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {
        Application.getInstance().stop();
        logger.info("bye");
        return true;
    }

    @Override
    public String getDescription() {
        return "exit program.";
    }
}
