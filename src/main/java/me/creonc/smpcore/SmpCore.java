package me.creonc.smpcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class SmpCore extends JavaPlugin { //Fun Fact: This is very poor written.


    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        // Handle the command
        if (command.getName().equals("SelectClass")) {
            sender.sendMessage("Hello, world!");
        }
        return true;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");
        Objects.requireNonNull(getCommand("SelectClass")).setExecutor(this);
        //TODO: Register command, set executor and load abu
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Stopping Plugin");
        // TODO: Shutdown logic
    }
}
