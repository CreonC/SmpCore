package me.creonc.smpcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
