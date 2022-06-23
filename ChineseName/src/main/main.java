package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin implements Listener {
    private static main instance;
    private static YamlConfiguration settings;

    public main() {
    }

    public static main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        File file = new File(main.getInstance().getDataFolder(), "settings.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            main.getInstance().saveResource("settings.yml", false);
        }
        //settings = YamlConfiguration.loadConfiguration(file);
        /*config.options().copyDefaults();
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        this.getCommand("cn").setExecutor(new _setName_());
        this.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage("§b================================");
        Bukkit.getConsoleSender().sendMessage("§b[ChineseName]中文名插件已加载");
        Bukkit.getConsoleSender().sendMessage("§b 作者:小雨        QQ:3066156386");
        Bukkit.getConsoleSender().sendMessage("§b爱发电 https://afdian.net/@ixiaoyu");
        Bukkit.getConsoleSender().sendMessage("§b================================");
    }

    public void onDisable() {
        System.out.println("[ChineseName]插件已卸载");
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        File file = new File(main.getInstance().getDataFolder(), "settings.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (this.getConfig().getString(e.getPlayer().getName()) != null) {
            e.getPlayer().setDisplayName(this.getConfig().getString(e.getPlayer().getName()) + ChatColor.RESET);
            if (config.getBoolean("tablist")) {
                e.getPlayer().setPlayerListName(this.getConfig().getString(e.getPlayer().getName()) + ChatColor.RESET);
            }
        }
    }
}
