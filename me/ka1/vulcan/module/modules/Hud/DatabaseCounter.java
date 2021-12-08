//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.io.*;
import me.ka1.vulcan.*;
import java.awt.*;

public class DatabaseCounter extends Module
{
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer x;
    Setting.Integer y;
    Setting.Boolean justnum;
    int dbs;
    
    public DatabaseCounter() {
        super("DatabaseCounter", "Displays how many databases you have loaded", Module.Category.Hud);
    }
    
    public void setup() {
        this.r = this.registerInteger("Red", "redaa", 255, 0, 255);
        this.g = this.registerInteger("Green", "greenaa", 255, 0, 255);
        this.b = this.registerInteger("Blue", "blueaa", 255, 0, 255);
        this.x = this.registerInteger("X", "xaa", 0, 0, 1280);
        this.y = this.registerInteger("Y", "yaa", 10, 0, 960);
        this.justnum = this.registerBoolean("JustNumber", "only displays the number of databases", false);
        final File file = new File(DatabaseCounter.mc.gameDir + File.separator + "Vulcan" + File.separator + "Databases");
        if (!file.exists()) {
            file.mkdir();
        }
    }
    
    public void onEnable() {
        this.dbs = 0;
        final File f = new File(DatabaseCounter.mc.gameDir + File.separator + "Vulcan" + File.separator + "Databases");
        final String[] list;
        final String[] files = list = f.list();
        for (final String file : list) {
            ++this.dbs;
        }
    }
    
    public void onRender() {
        if (this.justnum.getValue()) {
            Vulcan.fontRenderer.drawStringWithShadow(String.valueOf(this.dbs), this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()).getRGB());
        }
        else {
            Vulcan.fontRenderer.drawStringWithShadow("Databases loaded - " + this.dbs, this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()).getRGB());
        }
    }
}
