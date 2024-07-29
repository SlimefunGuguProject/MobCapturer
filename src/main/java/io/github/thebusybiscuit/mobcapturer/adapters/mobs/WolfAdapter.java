package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Wolf.Variant;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.adapters.InventoryAdapter;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;

import net.guizhanss.guizhanlib.java.BooleanHelper;
import net.guizhanss.guizhanlib.minecraft.helper.DyeColorHelper;

public class WolfAdapter extends AbstractTameableAdapter<Wolf> implements InventoryAdapter<Wolf> {

    private final Registry wolfVariantRegistry;

    public WolfAdapter() {
        super(Wolf.class);

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20_5)) {
            wolfVariantRegistry = RegistryAccess.registryAccess().getRegistry(RegistryKey.WOLF_VARIANT);
        } else {
            wolfVariantRegistry = null;
        }
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "项圈颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("collarColor").getAsString()));
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("sitting").getAsBoolean()));
        } else {
            lore.add(ChatColor.GRAY + "愤怒: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("angry").getAsBoolean()));
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Wolf entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAngry(json.get("angry").getAsBoolean());
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));

        // variant added in 1.20.5
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20_5) && json.has("variant")) {
            entity.setVariant((Variant) wolfVariantRegistry.get(NamespacedKey.fromString(json.get("variant").getAsString())));
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Wolf entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("angry", entity.isAngry());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());

        // variant added in 1.20.5
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20_5)) {
            json.addProperty("variant", entity.getVariant().getKey().toString());
        }

        return json;
    }

    @Override
    public void applyInventory(Wolf entity, Map<String, ItemStack> inventory) {
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20_5)) {
            ItemStack wolfArmor = inventory.get("body");
            entity.getEquipment().setItem(EquipmentSlot.BODY, wolfArmor);
        }
    }

    @Nonnull
    @Override
    public Map<String, ItemStack> saveInventory(@Nonnull Wolf entity) {
        Map<String, ItemStack> inventory = new HashMap<>();

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20_5)) {
            ItemStack wolfArmor = entity.getEquipment().getItem(EquipmentSlot.BODY);
            inventory.put("body", wolfArmor);
        }

        return inventory;
    }
}
