package org.acornmc.rewardmining.Events;

import org.acornmc.rewardmining.RewardMining;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.enchantments.Enchantment.SILK_TOUCH;

public class Eventsblockbreak implements Listener {

    Plugin plugin = RewardMining.getPlugin(RewardMining.class);

    @EventHandler
    public void Eventsblockbreak(BlockBreakEvent event) {
        Material material = event.getBlock().getType();

        if (!event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(SILK_TOUCH)) {
            if (plugin.getConfig().contains("Blocks." + material)) {
                ConfigurationSection cs = plugin.getConfig().getConfigurationSection("Blocks." + material);

                Random random = new Random();
                double randomNumber = random.nextDouble();

                List<String> mainList = new ArrayList<>(cs.getKeys(true));

                double lowestNumberAboveRandom = 1.1;
                for (int i = 1; i < cs.getKeys(true).size(); i++) {
                    double current = Double.parseDouble(mainList.get(i).replace("[", "").replace("]", ""));
                    if (current < lowestNumberAboveRandom && current > randomNumber) {
                        lowestNumberAboveRandom = current;
                    }
                }

                if (!(lowestNumberAboveRandom == 1.1)) {
                    System.out.println("lowest number not 1.1");
                    List<String> stringList = plugin.getConfig().getStringList("Blocks." + material + "." + "[" + lowestNumberAboveRandom + "]");
                    for (int i = 0; i < stringList.size(); i++) {
                        String command = stringList.get(i);
                        String modifiedCommand = command.replace("%PLAYER%", event.getPlayer().getPlayerListName());
                        getServer().dispatchCommand(getServer().getConsoleSender(), modifiedCommand);
                    }
                }
            }
        }
    }
}
