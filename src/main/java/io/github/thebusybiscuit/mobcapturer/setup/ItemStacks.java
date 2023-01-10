package io.github.thebusybiscuit.mobcapturer.setup;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

/**
 * All the {@link SlimefunItemStack}s in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public final class ItemStacks {
    public static final SlimefunItemStack MOB_CANNON = new SlimefunItemStack(
        "MOB_CANNON",
        Material.BLAZE_ROD,
        "&6生物捕捉枪",
        "",
        "&e右键点击&7射出一枚&f生物捕捉弹"
    );
    public static final SlimefunItemStack MOB_CAPTURING_PELLET = new SlimefunItemStack(
        "MOB_CAPTURING_PELLET",
        "983b30e9d135b05190eea2c3ac61e2ab55a2d81e1a58dbb26983a14082664",
        "&f生物捕捉弹",
        "",
        "&7是&6生物捕捉枪&7的弹药"
    );

    private ItemStacks() {}

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack buildMobEgg(EntityType type, String eggTexture) {
        Validate.notNull(type, "Entity type cannot be null");
        Validate.notNull(eggTexture, "Egg texture cannot be null");

        return new SlimefunItemStack(
            "MOB_EGG_" + type,
            eggTexture,
            "&a刷怪蛋 &7(" + ChatUtils.humanize(type.toString()) + ")",
            "",
            "&7对着方块右键点击此物品",
            "&7即可释放捕捉的生物"
        );
    }
}
