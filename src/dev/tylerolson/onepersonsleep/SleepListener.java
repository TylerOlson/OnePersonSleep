package dev.tylerolson.onepersonsleep;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;

public class SleepListener implements Listener {

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        long time = event.getPlayer().getWorld().getTime();

        if (time > 12541 && time < 23458) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OnePersonSleep.instance, new Runnable(){
                public void run(){
                    event.getPlayer().sendMessage("delay" + time);
                    event.getPlayer().getWorld().setTime(0);
                }
            }, 5*20);
        }

        event.getPlayer().sendMessage("PlayerBedEnterEvent" + time);
    }
}
