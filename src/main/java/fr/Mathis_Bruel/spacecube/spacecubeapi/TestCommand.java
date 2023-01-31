package fr.Mathis_Bruel.spacecube.spacecubeapi;

import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Games;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.games.Stats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("§a§l[SpaceCubeAPI] §aTest command executed !");

        return true;
    }
}
