package me.swipez.customhoes;

import me.swipez.customhoes.items.ItemManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomHoes extends JavaPlugin {

    private static CustomHoes customHoes;

    @Override
    public void onEnable() {
        customHoes = this;
        ItemManager.initRecipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CustomHoes getPlugin() {
        return customHoes;
    }
}
