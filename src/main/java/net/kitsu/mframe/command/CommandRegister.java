package net.kitsu.mframe.command;

import net.kitsu.lib.util.buffer.StringBuff;
import net.kitsu.lib.util.terminal.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class CommandRegister implements CommandHandler {
    private final String TEXT_BASH = "%systemroot%\\system32\\Reg.exe add \"HKCR\\Directory\\Background\\shell\\KitToolShell\\command\" /ve /t REG_SZ /d \"\\\"%~dp0jdk\\bin\\java.exe\\\" \\\"-jar\\\" \\\"%~dp0MicroFrameToolkit.jar\\\"\" /f";

    @Override
    public boolean onCommand(StringBuff stringBuff, Logger logger) {

        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", this.TEXT_BASH);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
            int exitCode = process.waitFor();
            logger.info("exit code = " + exitCode);
        } catch (IOException | InterruptedException e) {
            logger.info(e.toString());
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "register context menu.";
    }
}
