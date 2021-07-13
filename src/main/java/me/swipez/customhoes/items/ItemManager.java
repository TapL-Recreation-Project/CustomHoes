package me.swipez.customhoes.items;

import me.swipez.customhoes.CustomHoeObject;
import me.swipez.customhoes.CustomHoes;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.text.NumberFormat;
import java.util.*;

public class ItemManager {

    static Random random = new Random();

    private static List<Material> allMaterials = Arrays.asList(Material.values());
    private static List<PotionEffectType> allPotionEffects = Arrays.asList(PotionEffectType.values());

    public static CustomHoeObject UPGRADER_HOE = new CustomHoeObject(ChatColor.GOLD+"Upgrader Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            Material replaceMaterial = null;
            Material blockMaterial = interactedBlock.getType();
            if (blockMaterial.equals(Material.COAL_ORE)){
                replaceMaterial = Material.COAL_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_COAL_ORE)){
                replaceMaterial = Material.COAL_BLOCK;
            }
            if (blockMaterial.equals(Material.IRON_ORE)){
                replaceMaterial = Material.IRON_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_IRON_ORE)){
                replaceMaterial = Material.IRON_BLOCK;
            }
            if (blockMaterial.equals(Material.COPPER_ORE)){
                replaceMaterial = Material.COPPER_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_COPPER_ORE)){
                replaceMaterial = Material.COPPER_BLOCK;
            }
            if (blockMaterial.equals(Material.GOLD_ORE)){
                replaceMaterial = Material.GOLD_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_GOLD_ORE)){
                replaceMaterial = Material.GOLD_BLOCK;
            }
            if (blockMaterial.equals(Material.REDSTONE_ORE)){
                replaceMaterial = Material.REDSTONE_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_REDSTONE_ORE)){
                replaceMaterial = Material.REDSTONE_BLOCK;
            }
            if (blockMaterial.equals(Material.EMERALD_ORE)){
                replaceMaterial = Material.EMERALD_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_EMERALD_ORE)){
                replaceMaterial = Material.EMERALD_BLOCK;
            }
            if (blockMaterial.equals(Material.LAPIS_ORE)){
                replaceMaterial = Material.LAPIS_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_LAPIS_ORE)){
                replaceMaterial = Material.LAPIS_BLOCK;
            }
            if (blockMaterial.equals(Material.DIAMOND_ORE)){
                replaceMaterial = Material.DIAMOND_BLOCK;
            }
            if (blockMaterial.equals(Material.DEEPSLATE_DIAMOND_ORE)){
                replaceMaterial = Material.DIAMOND_BLOCK;
            }
            if (blockMaterial.equals(Material.NETHER_GOLD_ORE)){
                replaceMaterial = Material.GOLD_BLOCK;
            }
            if (blockMaterial.equals(Material.NETHER_QUARTZ_ORE)){
                replaceMaterial = Material.QUARTZ_BLOCK;
            }

            if (blockMaterial.toString().toLowerCase().contains("plank")){
                String[] split = blockMaterial.toString().split("_");
                String actualName = split[0]+"_LOG";

                replaceMaterial = Material.valueOf(actualName);
            }

            Player player = event.getPlayer();
            if (replaceMaterial != null){
                interactedBlock.setType(replaceMaterial);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                interactedBlock.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, interactedBlock.getLocation(), 15);
            }
        }
    };

    public static CustomHoeObject BREAD_HOE = new CustomHoeObject(ChatColor.YELLOW+"Bread Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                if (interactedBlock.getRelative(BlockFace.UP).getType().isAir()){

                    interactedBlock.setType(Material.FARMLAND);

                    Block aboveBlock = interactedBlock.getRelative(BlockFace.UP);
                    aboveBlock.setType(Material.WHEAT);

                    Ageable ageable = (Ageable) aboveBlock.getBlockData();
                    ageable.setAge(ageable.getMaximumAge());
                    aboveBlock.setBlockData(ageable);

                    Farmland farmland = (Farmland) interactedBlock.getBlockData();
                    farmland.setMoisture(7);
                    interactedBlock.setBlockData(farmland);

                    aboveBlock.getWorld().dropItem(aboveBlock.getLocation(), new ItemStack(Material.BREAD));
                }
            }
        }
    };

    // This kind of sounds like the ironed iron pickaxe I made for McBirken. Fumaz is a sussy baka.
    public static CustomHoeObject DIAMOND_DIAMOND_HOE = new CustomHoeObject(ChatColor.AQUA+"Diamond Diamond Hoe", Material.DIAMOND_HOE, false, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                interactedBlock.setType(Material.DIAMOND_BLOCK);
            }
        }
    };

    // This kind of sounds like the ironed iron pickaxe I made for McBirken. Fumaz is a sussy baka.
    public static CustomHoeObject GOLDEN_GOLDEN_HOE = new CustomHoeObject(ChatColor.GOLD+"Golden Golden Hoe", Material.GOLDEN_HOE, false, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                interactedBlock.setType(Material.GOLD_BLOCK);
            }
        }
    };

    public static CustomHoeObject ENCHANT_HOE = new CustomHoeObject(ChatColor.BLUE+"Enchant Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                Player player = event.getPlayer();
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                interactedBlock.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, interactedBlock.getLocation(), 10);
                List<Integer> slotsWithItems = new ArrayList<>();
                for (int i = 0; i < player.getInventory().getSize(); i++){
                    if (player.getInventory().getItem(i) != null){
                        slotsWithItems.add(i);
                    }
                }
                ItemStack itemStack = player.getInventory().getItem(slotsWithItems.get(random.nextInt(slotsWithItems.size())));
                while (!EnchantmentTarget.ARMOR.includes(Objects.requireNonNull(itemStack)) && !EnchantmentTarget.TOOL.includes(Objects.requireNonNull(itemStack)) && !EnchantmentTarget.BOW.includes(Objects.requireNonNull(itemStack)) && !EnchantmentTarget.WEAPON.includes(Objects.requireNonNull(itemStack))){
                    itemStack = player.getInventory().getItem(random.nextInt(player.getInventory().getSize()));
                }
                List<Enchantment> allEnchantments = Arrays.asList(Enchantment.values());
                Enchantment pickedEnchant = allEnchantments.get(random.nextInt(allEnchantments.size()));
                while (!pickedEnchant.canEnchantItem(itemStack)){
                    pickedEnchant = allEnchantments.get(random.nextInt(allEnchantments.size()));
                }

                if (!itemStack.getItemMeta().hasDisplayName() || !itemStack.getItemMeta().getDisplayName().contains("Hoe")){
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    int level = 1;
                    if (itemStack.containsEnchantment(pickedEnchant)){
                        level = itemStack.getEnchantmentLevel(pickedEnchant)+1;
                    }
                    itemMeta.addEnchant(pickedEnchant, level, true);
                    itemStack.setItemMeta(itemMeta);

                    convertToLore(itemStack);
                }
            }
        }
    };

    public static CustomHoeObject GLOW_HOE = new CustomHoeObject(ChatColor.BLUE+"Glow Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                interactedBlock.setType(Material.FARMLAND);
                Player player = event.getPlayer();
                replaceNearbyGrass(interactedBlock, 20, Material.GRASS_BLOCK, Material.GLOWSTONE);
                interactedBlock.getWorld().spawnEntity(interactedBlock.getLocation().add(0,1,0), EntityType.GLOW_SQUID);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
            }
        }
    };

    public static CustomHoeObject EFFECTING_HOE = new CustomHoeObject(ChatColor.DARK_PURPLE+"Effecting Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                Player player = event.getPlayer();
                player.addPotionEffect(new PotionEffect(allPotionEffects.get(random.nextInt(allPotionEffects.size())), 8*20, 0, true, true, true));
                AreaEffectCloud areaEffectCloud = (AreaEffectCloud) interactedBlock.getWorld().spawnEntity(interactedBlock.getLocation().add(0,1,0), EntityType.AREA_EFFECT_CLOUD);
                areaEffectCloud.setRadius(1);
                areaEffectCloud.setColor(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                areaEffectCloud.setDuration(40);

                player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
            }
        }
    };

    public static CustomHoeObject RANDOMIZER_HOE = new CustomHoeObject(ChatColor.LIGHT_PURPLE+"Randomizer Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                interactedBlock.setType(Material.CHEST);
                Chest chest = (Chest) interactedBlock.getState();
                chest.getBlockInventory().setItem(13, new ItemStack(allMaterials.get(random.nextInt(allMaterials.size()))));

            }
        }
    };

    public static CustomHoeObject HOE_SQUARED = new CustomHoeObject(ChatColor.RED+"Hoe²", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            hoeSquaredAction(interactedBlock, 20);
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ITEM_HOE_TILL, 1, 1);
            }
            else {
                event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GRASS_PLACE, 1, 1);
            }
        }
    };

    public static CustomHoeObject HEALTHY_HOE = new CustomHoeObject(ChatColor.RED+"Healthy Hoe", null, true, CustomHoes.getPlugin()) {
        @Override
        public void tillAction(PlayerInteractEvent event, Block interactedBlock) {
            if (!interactedBlock.getRelative(BlockFace.UP).getType().isAir()){
                return;
            }
            if (interactedBlock.getType().equals(Material.GRASS_BLOCK) || interactedBlock.getType().equals(Material.DIRT)){
                event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
                double determinedHealth = event.getPlayer().getHealth()+2;
                if (determinedHealth > 20){
                    determinedHealth = 20;
                }
                event.getPlayer().setHealth(determinedHealth);
                interactedBlock.getWorld().spawnParticle(Particle.HEART, interactedBlock.getLocation().add(0,1,0), 1);
            }
        }
    };


    public static void initRecipes(){
        registerGenericSurround("upgrader_hoe", new RecipeChoice.MaterialChoice(Material.FURNACE), getAllHoes(), UPGRADER_HOE.getHoeItem());
        registerGenericSurround("bread_hoe", new RecipeChoice.MaterialChoice(Material.HAY_BLOCK), getAllHoes(), BREAD_HOE.getHoeItem());
        registerGenericSurround("diamond_diamond_hoe", new RecipeChoice.MaterialChoice(Material.DIAMOND), new RecipeChoice.MaterialChoice(Material.DIAMOND_HOE), DIAMOND_DIAMOND_HOE.getHoeItem());
        registerGenericSurround("enchant_hoe", new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), getAllHoes(), ENCHANT_HOE.getHoeItem());
        registerSplit("glow_hoe", new RecipeChoice.MaterialChoice(Material.TORCH), new RecipeChoice.MaterialChoice(Material.INK_SAC), getAllHoes(), GLOW_HOE.getHoeItem());
        registerGenericSurround("hoe_squared", getAllHoes(), getAllHoes(), HOE_SQUARED.getHoeItem());
        registerGenericSurround("healthy_hoe", new RecipeChoice.MaterialChoice(Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE), getAllHoes(), HEALTHY_HOE.getHoeItem());
        registerGenericSurround("golden_golden_hoe", new RecipeChoice.MaterialChoice(Material.GOLD_INGOT), getAllHoes(), GOLDEN_GOLDEN_HOE.getHoeItem());
        registerSplit("randomizer_hoe", new RecipeChoice.MaterialChoice(Material.REDSTONE), new RecipeChoice.MaterialChoice(Material.CHEST), getAllHoes(), RANDOMIZER_HOE.getHoeItem());

        //Water bottle, ignore
        ItemStack bottle = new ItemStack(Material.POTION, 1);
        ItemMeta meta = bottle.getItemMeta();
        PotionMeta pmeta = (PotionMeta) meta;
        PotionData pdata = new PotionData(PotionType.WATER);
        pmeta.setBasePotionData(pdata);
        bottle.setItemMeta(meta);

        registerGenericSurround("effecting_hoe", new RecipeChoice.ExactChoice(bottle), getAllHoes(), EFFECTING_HOE.getHoeItem());
    }

    private static void registerGenericSurround(String key, RecipeChoice surroundChoice, RecipeChoice internalChoice, ItemStack result){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(CustomHoes.getPlugin(), key), result)
                .shape("LLL","LCL","LLL")
                .setIngredient('L', surroundChoice)
                .setIngredient('C', internalChoice);
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerSplit(String key, RecipeChoice firstChoice, RecipeChoice secondChoice, RecipeChoice internalChoice, ItemStack result){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(CustomHoes.getPlugin(), key), result)
                .shape("LLL","LCS","SSS")
                .setIngredient('L', firstChoice)
                .setIngredient('S', secondChoice)
                .setIngredient('C', internalChoice);
        Bukkit.addRecipe(shapedRecipe);
    }

    private static RecipeChoice getAllHoes(){
        return new RecipeChoice.MaterialChoice(Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE);
    }

    private static Integer hoeSquaredAction(Block block, int range, Material... materials) {
        int blocksAndOres = 0;

        int firstx = block.getLocation().getBlockX() - range;
        int firsty = block.getLocation().getBlockY() - range;
        int firstz = block.getLocation().getBlockZ() - range;

        int secondx = block.getLocation().getBlockX() + range;
        int secondy = block.getLocation().getBlockY() + range;
        int secondz = block.getLocation().getBlockZ() + range;

        Material originalMaterial = block.getType();

        for (int x = firstx; x < secondx; x++) {
            for (int y = firsty; y < secondy; y++) {
                for (int z = firstz; z < secondz; z++) {
                    Block testBlock = block.getWorld().getBlockAt(x,y,z);
                    if (originalMaterial.equals(Material.GRASS_BLOCK) || originalMaterial.equals(Material.DIRT)){
                        if (testBlock.getType().equals(Material.GRASS_BLOCK) || testBlock.getType().equals(Material.DIRT)){
                            testBlock.setType(Material.FARMLAND);
                        }
                    }
                    else {
                        int highestY = block.getWorld().getHighestBlockAt(x,z).getY();
                        Block highBlock = block.getWorld().getHighestBlockAt(x,z);
                        if (block.getY() == highestY){
                            if (highestY <= block.getY()+1 && highBlock.getType().equals(block.getType())){
                                if (!highBlock.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK) && !highBlock.getRelative(BlockFace.DOWN).getType().equals(Material.DIRT) && !highBlock.getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)){
                                    highBlock.setType(Material.GRASS_BLOCK);
                                }
                            }
                            else {
                                Block testHigh = highBlock.getRelative(BlockFace.UP);
                                while (!testHigh.getType().isAir()){
                                    testHigh = testHigh.getRelative(BlockFace.UP);
                                }
                                if (!testHigh.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK) && !testHigh.getRelative(BlockFace.DOWN).getType().equals(Material.DIRT) && !testHigh.getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)){
                                    testHigh.setType(Material.GRASS_BLOCK);
                                }
                            }
                        }
                        else {
                            Block testHigh = highBlock.getRelative(BlockFace.UP);
                            while (!testHigh.getType().isAir()){
                                testHigh = testHigh.getRelative(BlockFace.UP);
                            }
                            if (!testHigh.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK) && !testHigh.getRelative(BlockFace.DOWN).getType().equals(Material.DIRT) && !testHigh.getRelative(BlockFace.DOWN).getType().equals(Material.FARMLAND)){
                                testHigh.setType(Material.GRASS_BLOCK);
                            }
                        }
                    }
                }
            }
        }
        return blocksAndOres;
    }

    private static Integer replaceNearbyGrass(Block block, int range, Material... materials) {
        int blocksAndOres = 0;

        int firstx = block.getLocation().getBlockX() - range;
        int firsty = block.getLocation().getBlockY() - range;
        int firstz = block.getLocation().getBlockZ() - range;

        int secondx = block.getLocation().getBlockX() + range;
        int secondy = block.getLocation().getBlockY() + range;
        int secondz = block.getLocation().getBlockZ() + range;

        List<Material> materialList = Arrays.asList(materials);

        for (int x = firstx; x < secondx; x++) {
            for (int y = firsty; y < secondy; y++) {
                for (int z = firstz; z < secondz; z++) {
                    Block testBlock = block.getWorld().getBlockAt(x,y,z);
                    if (testBlock.getType().equals(Material.GRASS_BLOCK)){
                        Material chosenMaterial = materialList.get(random.nextInt(materialList.size()));
                        testBlock.setType(chosenMaterial);
                    }
                }
            }
        }
        return blocksAndOres;
    }


    public static void convertToLore(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        Map<Enchantment, Integer> allEnchantments = meta.getEnchants();
        for (Map.Entry<Enchantment, Integer> enchantLore : allEnchantments.entrySet()) {
            NumberFormat myFormat = NumberFormat.getInstance();
            myFormat.setGroupingUsed(true);
            int randomLevel = enchantLore.getValue();
            String[] lowercaseWords = enchantLore.getKey().toString().replace("Enchantment[minecraft:", "").replace(", ", "").replace(enchantLore.getKey().getName(), "").replace("]", "").replace("_", " ").split(" ");
            List<String> uppercaseWords = new ArrayList<>();
            for (String s : lowercaseWords) {
                uppercaseWords.add(s.replaceFirst(s.charAt(0) + "", Character.toUpperCase(s.charAt(0)) + ""));
            }
            if (!list.contains(ChatColor.GRAY + String.join(" ", uppercaseWords) + " " + myFormat.format(randomLevel))){
                list.add(ChatColor.GRAY + String.join(" ", uppercaseWords) + " " + myFormat.format(randomLevel));
            }
        }
        meta.setLore(list);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
    }
}
