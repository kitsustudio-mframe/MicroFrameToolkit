package net.kitsu.mframe.func;

import lombok.Getter;
import net.kitsu.mframe.Application;
import net.kitsu.mframe.IgnoreList;
import net.kitsu.mframe.func.pkg.IncludeList;

import java.io.File;
import java.util.logging.Logger;

public class PackageInfo {

    @Getter
    private IncludeList includeList;
    public Logger getLogger(){
        return Application.getInstance().getLogger();
    }

    public IgnoreList getIgnoreFolder(){
        return Application.getInstance().getIgnoreFolder();
    }

    public File getWorkFolder(){
        return Application.getInstance().getArguments().getWorkFolder();
    }

    public void update(){
        this.includeList = new IncludeList(this.getWorkFolder(), this.getIgnoreFolder());
    }
}
