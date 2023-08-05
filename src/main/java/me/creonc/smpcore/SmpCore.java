package me.creonc.smpcore;

import me.creonc.smpcore.commands.SelectClass;
import me.creonc.smpcore.tabcomplete.AutoComplete;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.util.logging.Level;

public final class SmpCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting Plugin");
        getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        SelectClass selectClass = new SelectClass(this);
        AutoComplete tabCompleter = new AutoComplete();
        try {
            this.getCommand("selectclass").setExecutor(selectClass);
            this.getCommand("selectclass").setTabCompleter(tabCompleter);
        }
        catch (NullPointerException e){
            Bukkit.getLogger().warning("Caught nullpointer... ");
            e.printStackTrace();
            Bukkit.getLogger().warning("");
            Bukkit.getLogger().log(Level.SEVERE,"Plugin had caught an nullpointer error. It's recommended to restart the server.");
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
