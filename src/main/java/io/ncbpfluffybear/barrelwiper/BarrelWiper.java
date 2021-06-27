package io.ncbpfluffybear.barrelwiper;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

/**
 * Example barrel wiper for CYT
 *
 * @author NCBPFluffyBear
 */
public class BarrelWiper extends JavaPlugin implements SlimefunAddon {

    public static BarrelWiper instance;

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        instance = this;

        /*
         * 1. Creating a new Category
         * This Category will use the following ItemStack
         */
        ItemStack categoryItem = new CustomItem(Material.DIAMOND, "&4Barrel Wiper");

        // Give your Category a unique id.
        NamespacedKey categoryId = new NamespacedKey(this, "barrel_wiper_category");
        Category category = new Category(categoryId, categoryItem);

        /*
         * 2. Create a new SlimefunItemStack
         * This class has many constructors, it is very important
         * that you give each item a unique id.
         */
        SlimefunItemStack slimefunItem = new SlimefunItemStack("BARREL_WIPER", Material.DIAMOND,
                "&4Barrel Wiper",
                "&cBreaks barrels and clears all items inside"
        );

        /*
         * 3. Creating a Recipe
         * The Recipe is an ItemStack Array with a length of 9.
         * It represents a Shaped Recipe in a 3x3 crafting grid.
         * The machine in which this recipe is crafted in is specified
         * further down as the RecipeType.
         */
        ItemStack[] recipe = {
                new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD),
                null, new ItemStack(Material.DIAMOND), null,
                new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD)
        };

        /*
         * 4. Registering the Item
         * Now you just have to register the item.
         * RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in
         * which this item is crafted in.
         * Recipe Types from Slimefun itself will automatically add the recipe to that machine.
         */
        new WiperItem(category, slimefunItem, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
