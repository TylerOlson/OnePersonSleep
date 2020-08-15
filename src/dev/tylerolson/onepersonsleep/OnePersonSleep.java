package dev.tylerolson.onepersonsleep;

import org.bukkit.plugin.java.JavaPlugin;

public class OnePersonSleep extends JavaPlugin {

    public static OnePersonSleep instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new SleepListener(), this);
    }
}
