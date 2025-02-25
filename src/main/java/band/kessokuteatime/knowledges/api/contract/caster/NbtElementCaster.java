package band.kessokuteatime.knowledges.api.contract.caster;

import band.kessokuteatime.knowledges.api.contract.caster.base.NbtCaster;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public class NbtElementCaster extends NbtCaster<NbtElement> {
    public NbtElementCaster(String identifier) {
        super(
                identifier,
                NbtCompound::get,
                NbtCompound::put
        );
    }
}
