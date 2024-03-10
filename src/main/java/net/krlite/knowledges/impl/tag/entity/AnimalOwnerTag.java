package net.krlite.knowledges.impl.tag.entity;

import net.krlite.knowledges.KnowledgesClient;
import net.krlite.knowledges.api.representable.EntityRepresentable;
import net.krlite.knowledges.api.tag.AdditionalEntityTag;
import net.krlite.knowledges.api.tag.caster.NbtCaster;
import net.krlite.knowledges.api.tag.caster.NbtStringCaster;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AnimalOwnerTag implements AdditionalEntityTag {
    public static final NbtStringCaster OWNER = new NbtStringCaster("Owner");

    @Override
    public boolean isApplicableTo(Entity entity) {
        return entity instanceof Ownable;
    }

    @Override
    public void append(NbtCompound data, EntityRepresentable representable) {
        MinecraftServer server = representable.world().getServer();
        Entity entity = representable.entity();
        if (server != null && server.isHost(representable.player().getGameProfile()) && entity instanceof TameableEntity)
            return;

        if (entity instanceof Ownable ownable && ownable.getOwner() != null) {
            UUID ownerUuid = ownable.getOwner().getUuid();
            KnowledgesClient.CACHE_USERNAME.get(ownerUuid).ifPresent(name ->
                    OWNER.put(data, name)
            );
        }
    }

    @Override
    public @NotNull String partialPath() {
        return "animal_owner";
    }
}
