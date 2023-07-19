package me.creonc.smpcore;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {


    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        // Handle the command
        if (command.getName().equals("selectclass")) {
            sender.sendMessage("Hello, world!"); //How to fix deprecated
            return true;
        }
        return true;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");

        try {
            this.getCommand("selectclass").setExecutor(this::onCommand);

        }
        catch (NullPointerException e){
            Bukkit.getLogger().warning("Caught nullpointer from plugin");
            e.printStackTrace();
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
