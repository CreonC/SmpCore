package me.creonc.smpcore;

import me.creonc.smpcore.commands.SelectClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
public final class SmpCore extends JavaPlugin {




    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        SelectClass selectClass = new SelectClass(config);

        try {
            this.getCommand("selectclass").setExecutor(selectClass);

        }
        catch (NullPointerException e){
            Bukkit.getLogger().warning("Caught nullpointer... ");
            e.printStackTrace();
            Bukkit.getLogger().warning("Plugin integrity cannot be confirmed. It is recommended to restart the server.");
        }

        //TODO: Register command, set executor and load abu
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Stopping Plugin");
        saveConfig();
    }
}
