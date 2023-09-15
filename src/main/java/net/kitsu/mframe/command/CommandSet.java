package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;
import net.kitsu.mframe.Application;
import net.kitsu.mframe.util.Arguments;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

public class CommandSet implements CommandHandler {
    private final Map<String, BiConsumer<Arguments, String>> optionMap;

    public CommandSet(){
        this.optionMap = Arguments.getOptionMap();
    }
    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {
        if(stringBuff.remaining() < 2){
            return false;
        }

        String key = stringBuff.get();
        String arg = stringBuff.get();
        if((key == null) || (arg == null))
            return false;

        BiConsumer<Arguments, String> option = this.optionMap.get(key);
        if(option == null)
            return false;

        option.accept(Application.getInstance().getArguments(), arg);
        logger.info("setting successful.");
        return true;
    }

    @Override
    public String getDescription() {
        return "set program config";
    }
}
