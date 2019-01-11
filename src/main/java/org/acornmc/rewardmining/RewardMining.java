package org.acornmc.rewardmining;

import org.acornmc.rewardmining.Events.Eventsblockbreak;
import org.bukkit.plugin.java.JavaPlugin;

public final class RewardMining extends JavaPlugin {


    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Eventsblockbreak(), this);
    }

}
