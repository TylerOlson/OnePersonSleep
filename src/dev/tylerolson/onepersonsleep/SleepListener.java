package dev.tylerolson.onepersonsleep;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepListener implements Listener {

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (OnePersonSleep.instance.getConfig().getBoolean("enable") == false)
            return;

        long time = event.getPlayer().getWorld().getTime();

        if (time > 12541 && time < 23458) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OnePersonSleep.instance, new Runnable(){
                public void run() {
                    if(event.getPlayer().isSleeping()) {
                        event.getPlayer().getWorld().setTime(0);
                        event.getPlayer().setStatistic(Statistic.TIME_SINCE_REST, 0);
                        if (OnePersonSleep.instance.getConfig().getBoolean("debug")) {
                            event.getPlayer().sendMessage("Still sleeping");
                        }
                    }

                    if (OnePersonSleep.instance.getConfig().getBoolean("debug")) {
                        event.getPlayer().sendMessage("delay" + time);
                    }
                }
            }, 5*20);
        }

        if (OnePersonSleep.instance.getConfig().getBoolean("debug")) {
            event.getPlayer().sendMessage("PlayerBedEnterEvent" + time);
        }
    }
}
