package me.creonc.smpcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class SelectClass implements CommandExecutor {
    private final HashMap<UUID, String> classSelections = new HashMap<>();
    private final FileConfiguration config;

    public SelectClass(FileConfiguration config) {
        this.config = config;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equals("selectclass")) {
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        UUID uuid = player.getUniqueId();

        if (args.length == 0) {
            sender.sendMessage("Usage: /selectclass <class>");
            sender.sendMessage("Available classes: berserker, alchemist, healer");
            return true;
        }

        if (args[0].equalsIgnoreCase("confirm")) {
            String className = classSelections.get(uuid);
            if (className == null) {
                sender.sendMessage("You have not selected a class yet. Use /selectclass <class> to select a class.");
            } else {
                // Save the selected class to the config file
                config.set(uuid.toString(), className);
                sender.sendMessage("Class selection confirmed.");
                classSelections.remove(uuid);
            }
            return true;
        } else if (args[0].equalsIgnoreCase("cancel")) {
            classSelections.remove(uuid);
            sender.sendMessage("Class selection canceled.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /selectclass <class>");
            sender.sendMessage("Available classes: berserker, alchemist, healer");
            return true;
        }

        String className = switch (args[0].toLowerCase()) {
            case "berserker" -> "Berserker";
            case "alchemist" -> "Alchemist";
            case "healer" -> "Healer";
            default -> null;
        };

        if (className == null) {
            sender.sendMessage("Invalid class name. Available classes: berserker, alchemist, healer");
            return true;
        }

        classSelections.put(uuid, className);

        sender.sendMessage("Selected class: " + className);

        String abilities = switch (className.toLowerCase()) {
            case "berserker" -> "Abilities: Swing (axes), 10% more damage to players, Thump";
            case "alchemist" -> "Abilities: Potion Bag, God Potion";
            case "healer" -> "Abilities: +50% health regen, Spray of Life";
            default -> null;
        };

        if (abilities != null) {
            sender.sendMessage(abilities);
        }

        sender.sendMessage("Confirm selection? Type /selectclass confirm to confirm or /selectclass cancel to cancel.");

        return true;
    }
}