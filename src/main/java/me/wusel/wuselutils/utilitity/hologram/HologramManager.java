/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.hologram;

import me.wusel.wuselutils.utilitity.FileBuilder;

public class HologramManager {
    public void loadHologramsFromFile(String path, String file) {
        FileBuilder fb = new FileBuilder(path, file);
        for (String name : fb.getConfiguration().getKeys(false)) {
            HologramBuilder hb = new HologramBuilder(name, fb.getConfiguration().getString(String.valueOf(name) + ".World"), fb.getConfiguration().getDouble(String.valueOf(name) + ".X"), fb.getConfiguration().getDouble(String.valueOf(name) + ".Y"), fb.getConfiguration().getDouble(String.valueOf(name) + ".Z"));
            for (String line : fb.getConfiguration().getStringList(String.valueOf(name) + ".Lines"))
                hb.addLine(line);
            hb.spawn();
        }
    }
}
