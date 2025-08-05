package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import io.github.thebusybiscuit.mobcapturer.adapters.InventoryAdapter;
import org.bukkit.entity.HappyGhast;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class HappyGhastAdapter extends AnimalsAdapter<HappyGhast> implements InventoryAdapter<HappyGhast> {

    public HappyGhastAdapter() {
        super(HappyGhast.class);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyInventory(HappyGhast entity, Map<String, ItemStack> inventory) {
        entity.getEquipment().setItem(EquipmentSlot.BODY, inventory.get("harness"));
    }

    @Nonnull
    @Override
    public Map<String, ItemStack> saveInventory(@Nonnull HappyGhast entity) {
        Map<String, ItemStack> inventory = new HashMap<>();

        inventory.put("harness", entity.getEquipment().getItem(EquipmentSlot.BODY));

        return inventory;
    }

}
