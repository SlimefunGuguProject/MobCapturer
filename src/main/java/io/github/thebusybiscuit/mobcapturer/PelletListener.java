package io.github.thebusybiscuit.mobcapturer;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;

public class PelletListener implements Listener {

    private final MobCapturer plugin;

    public PelletListener(@Nonnull MobCapturer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onProjectileHit(@Nonnull EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && e.getEntity() instanceof LivingEntity && e.getDamager().hasMetadata("mob_capturing_cannon")) {
            Snowball s = (Snowball) e.getDamager();

            if (canCapture((Player) s.getShooter(), e.getEntity().getLocation())) {
                Optional<ItemStack> optional = plugin.capture((LivingEntity) e.getEntity());

                if (optional.isPresent()) {
                    e.getDamager().removeMetadata("mob_capturing_cannon", plugin);
                    e.getEntity().remove();
                    e.getEntity().getWorld().dropItemNaturally(((LivingEntity) e.getEntity()).getEyeLocation(), optional.get());
                }
            }
        }
    }

    protected boolean canCapture(Player p, Location l) {
        return Slimefun.getProtectionManager().hasPermission(p, l, Interaction.ATTACK_ENTITY);
    }

}
