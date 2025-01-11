package me.minecraftmeg.minecraft_MEG;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.entity.Player;

public final class Minecraft_MEG extends JavaPlugin implements Listener   {
    private WebSockeServer webSocketServer;

    @Override
    public void onEnable() {
        getLogger().info("MocsMcPlugin has been enabled");
        // WebSocketサーバーの起動
        webSocketServer = new WebSockeServer(this);
        webSocketServer.start();
        getLogger().info("MocsMcPlugin: player name disabled");
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("WebSocketPlugin has been disabled");
        if (webSocketServer != null) {
            webSocketServer.stop();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        getLogger().info("MocsMcPlugin: player name set empty");
        event.getPlayer().setPlayerListName(" ");
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // リスポーンしたプレイヤーを取得
        Player player = event.getPlayer();
        getLogger().info("MocsMcPlugin: player hunger set 1");
        // 1ティック遅らせて空腹度を設定
        Bukkit.getScheduler().runTaskLater(this, () -> player.setFoodLevel(1), 1L);
        // 1ティック遅らせてHPを1に設定
        Bukkit.getScheduler().runTaskLater(this, () -> player.setHealth(1.0), 1L);
    }
}



