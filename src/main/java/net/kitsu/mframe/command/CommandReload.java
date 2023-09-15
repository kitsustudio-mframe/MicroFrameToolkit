package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;
import net.kitsu.mframe.Application;

import java.util.logging.Logger;

public class CommandReload implements CommandHandler {
    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {
        String folder = Application.getInstance().getConfigLoader().getIgnoreFolder();
        String file = Application.getInstance().getConfigLoader().getIgnoreFile();
        Application.getInstance().getIgnoreFolder().reload(folder, file);
        logger.info("reload successful.");
        return true;
    }

    @Override
    public String getDescription() {
        return "reload ignore file";
    }
}
