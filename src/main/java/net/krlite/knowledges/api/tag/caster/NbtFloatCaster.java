package net.krlite.knowledges.api.tag.caster;

import net.minecraft.nbt.NbtCompound;

public class NbtFloatCaster extends NbtCaster<Float> {
    public NbtFloatCaster(String identifier) {
        super(
                identifier,
                NbtCompound::getFloat,
                NbtCompound::putFloat
        );
    }
}
