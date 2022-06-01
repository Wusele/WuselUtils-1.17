/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.titles;

import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class TitleBuilder {
    private String ttitle;

    private String tsubtitle;

    private int ttime;

    private Player player;

    public TitleBuilder(Player p) {
        this.player = p;
        this.ttime = 0;
        this.ttitle = "";
        this.tsubtitle = "";
    }

    public TitleBuilder setTitle(String title) {
        this.ttitle = title;
        return this;
    }

    public TitleBuilder setSubtitle(String subtitle) {
        this.tsubtitle = subtitle;
        return this;
    }

    public TitleBuilder setTime(int time) {
        this.ttime = time;
        return this;
    }

    public void build() {
        for (int i = 0; i < this.ttime; i++) {
            this.player.sendTitle(this.ttitle, this.tsubtitle);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
