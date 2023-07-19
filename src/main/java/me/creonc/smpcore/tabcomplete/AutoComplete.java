package me.creonc.smpcore.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("selectclass")) {
            return null;
        }

        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("berserker");
            completions.add("alchemist");
            completions.add("healer");
            return completions;
        } else if (args.length == 2) {
            List<String> completions = new ArrayList<>();
            completions.add("confirm");
            completions.add("cancel");
            completions.add("unselect");
            return completions;
        }

        return null;
    }
}
