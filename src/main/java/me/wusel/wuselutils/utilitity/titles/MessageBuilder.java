/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.titles;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;

public class MessageBuilder {

    private TextComponent component;

    public MessageBuilder(String text) {
        component.setText(text);
    }

    public MessageBuilder setHoverEvent(HoverEvent hoverEvent) {
        component.setHoverEvent(hoverEvent);
        return this;
    }

    public MessageBuilder setClickEvent(ClickEvent clickEvent) {
        component.setClickEvent(clickEvent);
        return this;
    }

    public MessageBuilder setFont(String font) {
        component.setFont(font);
        return this;
    }

    public TextComponent build() {
        return component;
    }


}
