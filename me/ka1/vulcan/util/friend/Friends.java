//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.util.friend;

import net.minecraft.client.*;
import java.util.*;

public class Friends
{
    public static Minecraft mc;
    public static List<Friend> friends;
    
    public Friends() {
        Friends.friends = new ArrayList<Friend>();
    }
    
    public static List<Friend> getFriends() {
        return Friends.friends;
    }
    
    public static boolean isFriend(final String name) {
        boolean b = false;
        for (final Friend f : getFriends()) {
            if (f.getName().equalsIgnoreCase(name)) {
                b = true;
            }
        }
        return b;
    }
    
    public Friend getFriendByName(final String name) {
        Friend fr = null;
        for (final Friend f : getFriends()) {
            if (f.getName().equalsIgnoreCase(name)) {
                fr = f;
            }
        }
        return fr;
    }
    
    public void addFriend(final String name) {
        Friends.friends.add(new Friend(name));
    }
    
    public void delFriend(final String name) {
        Friends.friends.remove(this.getFriendByName(name));
    }
    
    static {
        Friends.mc = Minecraft.getMinecraft();
    }
}
