package me.creonc.smpcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SelectClass implements CommandExecutor {
    private String selectedClass;

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equals("selectclass")) {
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("Usage: /selectclass <class>");
            sender.sendMessage("Available classes: berserker, alchemist, healer");
            return true;
        }

        if (args[0].equalsIgnoreCase("confirm")) {
            if (selectedClass == null) {
                sender.sendMessage("No class selected. Use the /selectclass command to select a class.");
                return true;
            }

            // Apply the selected class to the player
            // ...

            sender.sendMessage("Selected class confirmed: " + selectedClass);
            selectedClass = null;

            return true;
        } else if (args[0].equalsIgnoreCase("cancel")) {
            if (selectedClass == null) {
                sender.sendMessage("No class selected. Use the /selectclass command to select a class.");
                return true;
            }

            sender.sendMessage("Selected class canceled: " + selectedClass);
            selectedClass = null;

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
        selectedClass = className;

        return true;
    }
}
