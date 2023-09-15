package net.kitsu.mframe;

import lombok.Getter;

import java.util.*;

public class IgnoreList {
    @Getter
    private final Map<String, Object> ignoreFolderMap;
    private final Map<String, Object> ignoreFileMap;

    public IgnoreList() {
        this.ignoreFolderMap = new HashMap<>();
        this.ignoreFileMap = new HashMap<>();
    }

    public boolean isIgnoreFile(String folderName) {
        return this.ignoreFileMap.containsKey(folderName);
    }

    public List<String> getIgnoreFileList() {
        return new ArrayList<>(this.ignoreFileMap.keySet());
    }

    public boolean isIgnoreFolder(String folderName) {
        return this.ignoreFolderMap.containsKey(folderName);
    }

    public List<String> getIgnoreFolderList() {
        return new ArrayList<>(this.ignoreFolderMap.keySet());
    }

    public void reloadFolder(String source) {
        String[] list = source.split("\\r?\\n");

        this.ignoreFolderMap.clear();

        try {
            for (String name : list) {
                if (name.charAt(0) == '#')
                    continue;

                this.ignoreFolderMap.put(name, null);
            }
        } catch (Throwable ignore) {
        }
    }

    public void reloadFile(String source) {
        String[] list = source.split("\\r?\\n");

        this.ignoreFileMap.clear();
        this.ignoreFileMap.put("package-info.h", null);

        try {
            for (String name : list) {
                if (name.charAt(0) == '#')
                    continue;

                this.ignoreFileMap.put(name, null);
            }
        } catch (Throwable ignore) {
        }
    }

    public void reload(String folder, String file){
        this.reloadFile(file);
        this.reloadFolder(folder);
    }
}
