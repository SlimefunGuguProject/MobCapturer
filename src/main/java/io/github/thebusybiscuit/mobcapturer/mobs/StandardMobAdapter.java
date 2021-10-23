package io.github.thebusybiscuit.mobcapturer.mobs;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import org.bukkit.entity.Mob;

public class StandardMobAdapter<T extends Mob> implements MobAdapter<T> {

    private final Class<T> entityClass;

    public StandardMobAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
