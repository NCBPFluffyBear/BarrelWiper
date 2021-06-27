package io.ncbpfluffybear.barrelwiper;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * A {@link SlimefunItem} that breaks and
 * deletes the contents of FluffyMachines barrels
 *
 * @author NCBPFluffyBear
 */
public class WiperItem extends SimpleSlimefunItem<ItemUseHandler> {

    protected WiperItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<Block> optB = e.getClickedBlock();
            if (!optB.isPresent()) {
                return;
            }

            Block b = optB.get();

            SlimefunItem sfItem = BlockStorage.check(b);

            if (sfItem == null) {
                return;
            }

            if (sfItem.getId().endsWith("FLUFFY_BARREL")) {
                Bukkit.getPluginManager().callEvent(new BlockBreakEvent(b, e.getPlayer()));
            }
        };
    }
}
