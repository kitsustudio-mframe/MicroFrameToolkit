package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;
import net.kitsu.mframe.Application;
import net.kitsu.mframe.func.PackageInfo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Logger;

public class CommandPackage implements CommandHandler {
    private final PackageInfo packageInfo;

    public CommandPackage(){
        this.packageInfo = new PackageInfo();
    }

    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {
        this.packageInfo.update();
        this.packageInfo.getIncludeList().writeAll(Application.getInstance().getConfigLoader().getPackageFormatFile());
        return true;
    }

    @Override
    public String getDescription() {
        return "package header file.";
    }

}
