package net.krlite.knowledges.impl.data.info.block.blockinformation;

import net.krlite.knowledges.api.representable.BlockRepresentable;
import net.krlite.knowledges.impl.data.info.block.AbstractBlockInformationData;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SaplingBlockInformationData extends AbstractBlockInformationData {
    @Override
    public Optional<MutableText> blockInformation(BlockRepresentable representable) {
        if (representable.block() instanceof SaplingBlock) {
            int stage = representable.blockState().get(SaplingBlock.STAGE);

            return Optional.of(Text.translatable(
                    localizationKey("stage"),
                    stage
            ));
        }

        return Optional.empty();
    }

    @Override
    public @NotNull String partialPath() {
        return "sapling";
    }

    @Override
    public boolean providesTooltip() {
        return true;
    }
}
