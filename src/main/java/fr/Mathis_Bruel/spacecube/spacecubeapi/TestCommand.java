package fr.Mathis_Bruel.spacecube.spacecubeapi;

import fr.Mathis_Bruel.spacecube.spacecubeapi.api.Ranks.Ranks;
import fr.Mathis_Bruel.spacecube.spacecubeapi.api.players.SpaceCubePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Ranks rank = new Ranks().createRank("Membre", "[Membre] ", " ", " ", "&2", "&7", true);
        sender.sendMessage(rank.getName());

        return false;
    }
}
