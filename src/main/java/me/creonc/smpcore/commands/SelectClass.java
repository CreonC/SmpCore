package me.creonc.smpcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class SelectClass implements CommandExecutor {
    private final HashMap<UUID, String> classSelections = new HashMap<>();
    private final FileConfiguration config;
    private final File configFile;

    public SelectClass(Plugin plugin) {
        this.config = plugin.getConfig();
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equals("selectclass")) {
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendPlainMessage("Only players can use this command.");
            return true;
        }

        UUID uuid = player.getUniqueId();

        if (args.length == 0) {
            sender.sendPlainMessage("Usage: /selectclass <class>");
            sender.sendPlainMessage("Available classes: berserker, alchemist, healer");
            return true;
        }

        if (args[0].equalsIgnoreCase("confirm")) {
            String className = classSelections.get(uuid);
            if (className == null) {
                sender.sendPlainMessage("You have not selected a class yet. Use /selectclass <class> to select a class.");
            } else {
                // Save the selected class to the config file
                config.set(uuid.toString(), className);
                try {
                    config.save(configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendPlainMessage("Class selection confirmed.");
                classSelections.remove(uuid);
            }
            return true;
        } else if (args[0].equalsIgnoreCase("cancel")) {
            String className = classSelections.get(uuid);
            if (className == null) {
                sender.sendPlainMessage("You have not selected a class yet. Use /selectclass <classname> to select a class.");

            } else {
                classSelections.remove(uuid);
                sender.sendPlainMessage("Class selection canceled.");
                return true;
            }

        } else if (args[0].equalsIgnoreCase("unselect")) {
            String className = classSelections.get(uuid);
            // Remove the class from the config file
            if (className == null) {

                sender.sendPlainMessage("You have not selected a class yet. Use /selectclass <class> to select a class.");
            }else {
                config.set(uuid.toString(), null);
                try {
                    config.save(configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendPlainMessage("Class selection removed.");
                return true;
            }
        }

        if (args.length != 1) {
            sender.sendPlainMessage("Usage: /selectclass <class>");
            sender.sendPlainMessage("Available classes: berserker, alchemist, healer");
            return true;
        }

        if (classSelections.containsKey(uuid)) {
            sender.sendPlainMessage("You have already selected a class! Use /selectclass unselect to unselect current class.");
            return true;
        }

        String className = switch (args[0].toLowerCase()) {
            case "berserker" -> "Berserker";
            case "alchemist" -> "Alchemist";
            case "healer" -> "Healer";
            default -> null;
        };

        if (className == null) {
            sender.sendPlainMessage("Invalid class name. Available classes: berserker, alchemist, healer");
            return true;
        }

        classSelections.put(uuid, className);

        sender.sendPlainMessage("Selected class: " + className);

        String abilities = switch (className.toLowerCase()) {
            case "berserker" -> "Abilities: Swing (axes), 10% more damage to players, Thump";
            case "alchemist" -> "Abilities: Potion Bag, God Potion";
            case "healer" -> "Abilities: +50% health regen, Spray of Life";
            default -> null;
        };

        if (abilities != null) {
            sender.sendPlainMessage(abilities);
        }

        sender.sendPlainMessage("Confirm selection? Type /selectclass confirm to confirm or /selectclass cancel to cancel.");

        return true;
    }
}