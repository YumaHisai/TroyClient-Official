/*
 * Decompiled with CFR 0.150.
 */
package it.md_4.troy.ui.guis.uts;

import net.minecraft.client.Minecraft;

public class UUID {
    public static String getUUID() {
        return java.util.UUID.nameUUIDFromBytes(String.valueOf(new StringBuilder().append("OfflinePlayer:").append(Minecraft.getMinecraft().getFakeNick())).getBytes()).toString().replace("-", "");
    }
}

