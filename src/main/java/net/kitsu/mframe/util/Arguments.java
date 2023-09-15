package net.kitsu.mframe.util;

import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Data
public class Arguments {
    private File workFolder = null;
    private File programFolder = null;
    private String command = null;
    private String prefix = null;

    public Arguments(String[] args){
        this.programFolder = new File(System.getProperty("java.class.path")).getParentFile();

        Arguments.convertArguments(this, args);

        if(workFolder == null)
            workFolder = new File(System.getProperty("user.dir"));

        if(prefix == null)
            prefix = "";

        if(this.command == null)
            this.command = "TERMINAL";
    }

    public static void convertArguments(Arguments clazz, String[] args){
        Map<String, BiConsumer<Arguments, String>> optionMap = Arguments.getOptionMap();

        if(args.length >= 1)
            clazz.command = args[0];

        for(int i=1; i<args.length; ++i){
            if(args[i].charAt(0) == '-'){
                BiConsumer<Arguments, String> method = optionMap.get(args[i]);
                if(method == null)
                    continue;

                ++i;
                if(i>args.length)
                    break;

                method.accept(clazz, args[i]);
            }
        }
    }

    public static Map<String, BiConsumer<Arguments, String>> getOptionMap(){
        Map<String, BiConsumer<Arguments, String>> result = new HashMap<>();
        result.put("-path", Arguments::argsPath);
        result.put("-prefix", Arguments::argsPrefix);
        return  result;
    }

    private static void argsPath(Arguments args, String path){
        args.workFolder = new File(path);
    }

    private static void argsPrefix(Arguments clazz, String arg){
        clazz.prefix = arg;
    }
}
