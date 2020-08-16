package dev.tylerolson.onepersonsleep;

import org.bukkit.plugin.java.JavaPlugin;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class OnePersonSleep extends JavaPlugin {

    public static OnePersonSleep instance;

    @Override
    public void onEnable() {
        instance = this;

        this.getConfig().addDefault("enable", true);
        this.getConfig().addDefault("debug", false);
        this.getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new SleepListener(), this);

        Metrics metrics = new Metrics(this, 8552);

        String latestVersion = getLatestVersion();
        if (latestVersion == "") {
            getLogger().info("Could not get latest version!");
        } else if (this.getDescription().getVersion().equals(latestVersion)) {
            getLogger().info("Running latest version.");
        } else if (!this.getDescription().getVersion().equals(latestVersion)) {
            getLogger().info("Update available! Latest version is v" + latestVersion + ".");
        }
    }

    public String getLatestVersion() {
        String latestVersion = "";
        try {
            URLConnection connection = new URL("https://api.spigotmc.org/legacy/update.php?resource=82804").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            latestVersion = scanner.next();
            scanner.close();
        } catch (Exception ex) {
            getLogger().warning(ex.getMessage());
        }

        return latestVersion;
    }

}
