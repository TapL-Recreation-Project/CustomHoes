package me.swipez.customhoes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class CustomHoeObject implements Listener {
    private final String name;
    private Material hoeMaterial;
    private final boolean replaceMaterialOnCraft;
    private final JavaPlugin plugin;

    private static int hoesInAction = 0;
    private final int hoeID;

    private boolean isChecking = false;

    protected CustomHoeObject(String name, Material hoeMaterial, boolean replaceMaterialOnCraft, JavaPlugin plugin) {
        // Integer raises everytime the constructor is called, removing the need to specify hoe ID.
        hoesInAction++;
        hoeID = hoesInAction;
        this.name = name;
        this.hoeMaterial = hoeMaterial;
        this.plugin = plugin;
        if (hoeMaterial == null){
            this.hoeMaterial = Material.WOODEN_HOE;
        }
        // Sometimes the material of the hoe will change depending on what was used to craft it.
        this.replaceMaterialOnCraft = replaceMaterialOnCraft;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack getHoeItem(){
        ItemStack itemStack = new ItemStack(hoeMaterial);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.addEnchant(Enchantment.CHANNELING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        // Data containers are sexy.
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(new NamespacedKey(plugin, "customhoeid"), PersistentDataType.INTEGER, hoeID);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    @EventHandler
    public void onHoeCraft(PrepareItemCraftEvent event){
        if (event.getInventory().getResult() == null){
            return;
        }
        ItemStack itemStack = event.getInventory().getResult();
        PersistentDataContainer dataContainer = itemStack.getItemMeta().getPersistentDataContainer();
        if (dataContainer.has(new NamespacedKey(plugin, "customhoeid"), PersistentDataType.INTEGER)){
            int testedHoeID = dataContainer.get(new NamespacedKey(plugin, "customhoeid"), PersistentDataType.INTEGER);
            if (testedHoeID == hoeID){
                if (replaceMaterialOnCraft){
                    // We can assume the item in the 5th slot will always be a hoe. Since its the center of the crafting grid.
                    Material centerHoe = event.getInventory().getItem(5).getType();
                    itemStack.setType(centerHoe);
                }
            }
        }
    }

    @EventHandler
    public void onHoeTill(PlayerInteractEvent event){
        if (isChecking){
            return;
        }
        if (!event.hasItem()){
            return;
        }
        if (!event.hasBlock()){
            return;
        }
        ItemStack itemStack = event.getItem();
        assert itemStack != null;

        if (itemStack.getItemMeta() == null){
            return;
        }
        isChecking = true;
        PersistentDataContainer dataContainer = itemStack.getItemMeta().getPersistentDataContainer();
        if (dataContainer.has(new NamespacedKey(plugin, "customhoeid"), PersistentDataType.INTEGER)){
            int testedHoeID = dataContainer.get(new NamespacedKey(plugin, "customhoeid"), PersistentDataType.INTEGER);

            //Makes sure the hoe in question is actually the custom hoe belonging to this class.
            if (testedHoeID == hoeID){

                // Abstract method to be filled in.
                tillAction(event, event.getClickedBlock());
            }
        }
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                isChecking = false;
            }
        }.runTaskLater(plugin, 1);
    }

    public abstract void tillAction(PlayerInteractEvent event, Block interactedBlock);
}
