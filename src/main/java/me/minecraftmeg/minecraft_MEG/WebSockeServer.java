package me.minecraftmeg.minecraft_MEG;

import java.net.InetSocketAddress;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WebSockeServer extends WebSocketServer {
    private final JavaPlugin plugin;
    public WebSockeServer(JavaPlugin plugin) {
        super(new InetSocketAddress(23343));
        this.plugin = plugin;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + handshake.getResourceDescriptor());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // 受信したメッセージを処理
        String[] parts = message.split(" ");
        if (parts.length >= 3 && (parts[0].equalsIgnoreCase("minecraft:give")|| parts[0].equalsIgnoreCase("ei")|| parts[0].equalsIgnoreCase("uf")|| parts[0].equalsIgnoreCase("tellraw")|| parts[0].equalsIgnoreCase("give")|| parts[0].equalsIgnoreCase("msg")|| parts[0].equalsIgnoreCase("tp") || parts[0].equalsIgnoreCase("si"))) {
            System.out.println("Received command: " + message);
            Bukkit.getScheduler().runTask(plugin, () -> {
                System.out.println("Executing command: " + message);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
            });
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully");
    }

    public void stop() {
        try {
            super.stop();
            System.out.println("WebSocket server stopped successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}