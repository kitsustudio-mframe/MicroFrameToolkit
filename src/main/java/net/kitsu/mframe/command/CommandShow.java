package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;
import net.kitsu.mframe.Application;
import net.kitsu.mframe.util.Arguments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class CommandShow implements CommandHandler {
    private final Map<String, Consumer<Logger>> optionMap;

    public CommandShow(){
        this.optionMap = new HashMap<>();
        this.optionMap.put("config", CommandShow::showConfig);
        this.optionMap.put("ignore", CommandShow::showIgnore);
    }
    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {
        String option = "config";
        if(stringBuff.remaining() > 0)
            option = stringBuff.get().toLowerCase();

        Consumer<Logger> accept = this.optionMap.get(option);
        if(accept == null){
            logger.info("option not found.");
            return true;
        }

        accept.accept(logger);
        return true;
    }

    @Override
    public String getDescription() {
        return "show arguments";
    }

    private static void showConfig(Logger logger){
        Arguments arguments = Application.getInstance().getArguments();
        logger.info("program folder = " + arguments.getProgramFolder().toString());
        logger.info("work folder = " + arguments.getWorkFolder().toString());
        logger.info("command = " + arguments.getCommand());
        logger.info("prefix = " + arguments.getPrefix());
    }

    private static void showIgnore(Logger logger){
        List<String> folderList = Application.getInstance().getIgnoreFolder().getIgnoreFolderList();
        List<String> fileList = Application.getInstance().getIgnoreFolder().getIgnoreFileList();
        StringBuilder stringBuilder = new StringBuilder("\r\n");

        if(folderList.size() != 0){
            int i=0;
            stringBuilder.append("------------------------------------------\r\nfolder list:\r\n");
            for(String s : folderList){
                stringBuilder.append(i);
                stringBuilder.append(") ");
                stringBuilder.append(s);
                stringBuilder.append("\r\n");
                ++i;
            }
        }else{
            stringBuilder.append("------------------------------------------\r\nfolder ignore is empty\r\n");
        }

        if(fileList.size() != 0){
            int i=0;
            stringBuilder.append("------------------------------------------\r\nfile list:\r\n");
            for(String s : fileList){
                stringBuilder.append(i);
                stringBuilder.append(") ");
                stringBuilder.append(s);
                stringBuilder.append("\r\n");
                ++i;
            }
        }else{
            stringBuilder.append("------------------------------------------\r\nfile ignore is empty\r\n");
        }

        logger.info(stringBuilder.toString());
    }
}
