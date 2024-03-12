package net.krlite.knowledges.impl.entrypoint.data.info.block;

import net.krlite.knowledges.api.entrypoint.DataProvider;
import net.krlite.knowledges.impl.data.info.base.block.BlockInformationData;
import net.krlite.knowledges.impl.data.info.block.blockinformation.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlockInformationDataProvider implements DataProvider<BlockInformationData> {
    @Override
    public @NotNull List<Class<? extends BlockInformationData>> provide() {
        return List.of(
                NoteBlockInformationData.class,
                BannerBlockInformationData.class,
                ComposterBlockInformationData.class,
                RedstoneWireBlockInformationData.class,
                CropBlockInformationData.class,
                SaplingBlockInformationData.class,
                BeehiveBlockInformationData.class
        );
    }
}
