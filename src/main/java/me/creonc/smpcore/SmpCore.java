package me.creonc.smpcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SmpCore extends JavaPlugin { //Fun Fact: This is very poorly written.


    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        // Handle the command
        if (command.getName().equals("selectclass")) {
            sender.sendMessage("Hello, world!"); //How to fix deprecated
        }
        return true;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");
        this.getCommand("selectclass").setExecutor(this); //pls
        //TODO: Register command, set executor and load abu
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Stopping Plugin");
        // TODO: Shutdown logic
    }
}
