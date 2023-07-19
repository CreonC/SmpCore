package me.creonc.smpcore;

import me.creonc.smpcore.commands.SelectClass;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {




    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");

        try {
            this.getCommand("selectclass").setExecutor(new SelectClass());

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
        // TODO: Shutdown logic
    }
}
