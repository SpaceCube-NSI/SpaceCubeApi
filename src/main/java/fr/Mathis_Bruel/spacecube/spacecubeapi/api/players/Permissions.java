package fr.Mathis_Bruel.spacecube.spacecubeapi.api.players;

import org.bukkit.entity.Player;

public class Permissions {

    public static boolean hasPermissions(SpaceCubePlayer scp, String permission){
        return scp.getPermissions().contains(permission);
    }

    public static boolean hasPermissions(Player player, String permission){
        return hasPermissions(SpaceCubePlayer.getSCP(player), permission);
    }


}
