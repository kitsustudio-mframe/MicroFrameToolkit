package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;
import net.kitsu.mframe.Application;

import java.util.logging.Logger;

public class CommandSetting implements CommandHandler {

    public CommandSetting(){
    }
    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {
        Application.getInstance().getGlobal().update(this.buffToArray(stringBuff));
        return true;
    }

    @Override
    public String getDescription() {
        return "set program config";
    }

    private String[] buffToArray(StringBuff stringBuff){
        String[] result = new String[stringBuff.remaining()];
        for(int i=0; i< result.length; ++i){
            result[i] = stringBuff.get();
        }
        return result;
    }
}
