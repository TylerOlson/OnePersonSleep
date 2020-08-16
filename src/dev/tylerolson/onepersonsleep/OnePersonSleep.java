package dev.tylerolson.onepersonsleep;

import org.bukkit.plugin.java.JavaPlugin;

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
    }
}
