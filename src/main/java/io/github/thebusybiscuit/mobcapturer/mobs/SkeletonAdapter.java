package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.AbstractSkeleton;

public class SkeletonAdapter<T extends AbstractSkeleton> extends AbstractHumanoidAdapter<T> {

    public SkeletonAdapter(Class<T> entityClass) {
        super(entityClass);
    }

}
