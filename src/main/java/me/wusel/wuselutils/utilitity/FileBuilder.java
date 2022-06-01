/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileBuilder {

    private YamlConfiguration cfg;

    private File file;

    public FileBuilder(String path, String fileName) {
        this.file = new File(path, fileName);
        this.cfg = YamlConfiguration
                .loadConfiguration(this.file);
    }

    public YamlConfiguration getConfiguration() {
        return cfg;
    }

    public void save() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
