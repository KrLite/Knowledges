package net.krlite.knowledges.data.info.block.blockinformation;

import net.krlite.knowledges.data.info.block.AbstractBlockInformationData;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CropBlockInformationData extends AbstractBlockInformationData {
    @Override
    public Optional<MutableText> blockInformation(BlockState blockState, PlayerEntity player) {
        if (blockState.getBlock() instanceof CropBlock) {
            int age = blockState.get(CropBlock.AGE), maxAge = CropBlock.MAX_AGE;

            return Optional.of(age == maxAge ? localize("mature") : Text.translatable(
                    localizationKey("age"),
                    blockState.get(CropBlock.AGE), CropBlock.MAX_AGE
            ));
        }

        return Optional.empty();
    }

    @Override
    public boolean providesTooltip() {
        return true;
    }

    @Override
    public @NotNull String partialPath() {
        return "crop";
    }
}
