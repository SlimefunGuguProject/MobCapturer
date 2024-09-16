package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import lombok.experimental.UtilityClass;

/**
 * All the {@link RecipeType}s in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
@UtilityClass
public final class RecipeTypes {

    // @formatter:off
    public static final RecipeType MOB_CAPTURING = new RecipeType(
        new NamespacedKey(MobCapturer.getInstance(), "mob_capturing"),
        new CustomItemStack(
            ItemStacks.MOB_CANNON,
            "&6生物捕捉枪",
            "&7使用&6生物捕捉枪&7捕获此生物"
        )
    );
    // @formatter:on
}
