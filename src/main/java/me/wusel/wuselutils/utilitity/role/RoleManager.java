package me.wusel.wuselutils.utilitity.role;

import me.wusel.wuselutils.firebase.FireCollection;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class RoleManager {

    private FireCollection collection;

    private HashMap<String, Role> roles;

    public RoleManager(FireCollection collection) {
        this.collection = collection;
        roles = new HashMap<>();
    }


    public void registerRole(Role role, String name) {
        roles.put(name, role);
    }


    public Role getRoleByName(String roleName) {
        Role r = null;

        if (roles.containsKey(roleName)) {
            r = roles.get(roleName);
            return r;
        }
        r = new Role("NULL", "NULL", "NULL", ChatColor.WHITE);
        return r;
    }

    public Role getRoleByPlayer(String uuid) {
        Role r = null;
        String prefix = "";
        String roleName = collection.getString(uuid, "role");
        ChatColor color;
        if (roles.containsKey(roleName)) {
            r = roles.get(roleName);
            return r;
        }
        r = new Role("NULL", "NULL", "NULL", ChatColor.WHITE);
        return r;
    }
}
