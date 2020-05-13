package exodian.me.deathban;


import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.BanList;
import org.bukkit.entity.Player;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer();
        if((player.getGameMode() == GameMode.SPECTATOR) && (!player.hasPermission("banondeath.immune"))){
            player.getServer().getBanList(BanList.Type.NAME).addBan(player.getName(), "RIP", null, "BanOnDeath"); // NAME BAN
            player.getServer().getLogger().info("BanOnDeath has banned " + player.getName());
            if(!player.getServer().getScoreboardManager().getMainScoreboard().getTeam("Brothers").hasEntry(player.getName())){
                player.getServer().broadcast(ChatColor.DARK_RED+""+ChatColor.ITALIC+"[DeathBan] "+player.getName()+" has been automatically banned.", player.getServer().BROADCAST_CHANNEL_ADMINISTRATIVE);
                player.kickPlayer("Thanks for playing.");
            }
        }
    }
}