package me.creonc.smpcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SelectClass implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("selectclass")) {
            if (args.length != 1) {
                sender.sendMessage("Usage: /selectclass <class>");
                sender.sendMessage("Available classes: berserker, alchemist, healer");
                return true;
            }

            String className = args[0].toLowerCase();
            if (!(className.equals("berserker") || className.equals("alchemist") || className.equals("healer"))) {
                sender.sendMessage("Invalid class name. Available classes: berserker, alchemist, healer");
                return true;
            }

            sender.sendMessage("Selected class: " + className);

            // Print abilities for the selected class
            switch (className) {
                case "berserker" -> sender.sendMessage("Abilities: Swing (axes), 10% more damage to players, Thump");
                case "alchemist" -> sender.sendMessage("Abilities: Potion Bag, God Potion");
                case "healer" -> sender.sendMessage("Abilities: +50% health regen, Spray of Life");
            }

            // Ask player to confirm their selection
            sender.sendMessage("Confirm selection? Type /confirm to confirm or /cancel to cancel.");

            return true;
        }
        return false;
    }
}
