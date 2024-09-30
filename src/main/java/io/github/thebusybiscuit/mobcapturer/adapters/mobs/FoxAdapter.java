package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Fox.Type;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.adapters.InventoryAdapter;

import net.guizhanss.minecraft.guizhanlib.gugu.java.BooleanHelper;
import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity.FoxHelper;

public class FoxAdapter extends AnimalsAdapter<Fox> implements InventoryAdapter<Fox> {

    public FoxAdapter() {
        super(Fox.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "种类: " + ChatColor.WHITE + FoxHelper.getTypeName(json.get("foxType").getAsString()));
        if (json.get("crouching").getAsBoolean()) {
            lore.add(ChatColor.GRAY + "潜行中: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("crouching").getAsBoolean()));
        } else if (json.get("sitting").getAsBoolean()) {
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("sitting").getAsBoolean()));
        } else if (json.get("sleeping").getAsBoolean()) {
            lore.add(ChatColor.GRAY + "睡觉中: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("sleeping").getAsBoolean()));
        }


        JsonElement firstElement = json.get("firstTrustedPlayerName");
        JsonElement secondElement = json.get("secondTrustedPlayerName");
        if (!firstElement.isJsonNull()) {
            if (secondElement.isJsonNull()) {
                lore.add(ChatColor.GRAY + "信任的玩家: " + ChatColor.WHITE + firstElement.getAsString());
            } else {
                lore.add(ChatColor.GRAY + "信任的玩家: " + ChatColor.WHITE + firstElement.getAsString() + ", " + secondElement.getAsString());
            }
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Fox entity, JsonObject json) {
        super.apply(entity, json);

        entity.setFoxType(Type.valueOf(json.get("foxType").getAsString()));
        entity.setCrouching(json.get("crouching").getAsBoolean());
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setSleeping(json.get("sleeping").getAsBoolean());


        JsonElement element = json.get("firstTrustedPlayerUUID");
        if (!element.isJsonNull()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(element.getAsString()));
            entity.setFirstTrustedPlayer(player);
        }
        element = json.get("secondTrustedPlayerUUID");
        if (!element.isJsonNull()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(element.getAsString()));
            entity.setSecondTrustedPlayer(player);
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Fox entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("foxType", entity.getFoxType().name());
        json.addProperty("crouching", entity.isCrouching());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("sleeping", entity.isSleeping());
        json.addProperty("firstTrustedPlayerUUID", entity.getFirstTrustedPlayer() == null ? null : entity.getFirstTrustedPlayer().getUniqueId().toString());
        json.addProperty("firstTrustedPlayerName", entity.getFirstTrustedPlayer() == null ? null : entity.getFirstTrustedPlayer().getName());
        json.addProperty("secondTrustedPlayerUUID", entity.getSecondTrustedPlayer() == null ? null : entity.getSecondTrustedPlayer().getUniqueId().toString());
        json.addProperty("secondTrustedPlayerName", entity.getSecondTrustedPlayer() == null ? null : entity.getSecondTrustedPlayer().getName());

        return json;
    }

    @Nonnull
    @Override
    public Map<String, ItemStack> saveInventory(@Nonnull Fox entity) {
        Map<String, ItemStack> inv = new HashMap<>();

        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            inv.put("mainHand", equipment.getItemInMainHand());
        }

        return inv;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyInventory(Fox entity, Map<String, ItemStack> inventory) {
        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            equipment.setItemInMainHand(inventory.get("mainHand"));
        }
    }

}
