package net.krlite.knowledges.api;

import net.krlite.knowledges.Knowledges;
import net.krlite.knowledges.core.config.WithIndependentConfigPage;
import net.krlite.knowledges.core.data.DataProtocol;
import net.krlite.knowledges.core.localization.Localizable;
import net.krlite.knowledges.core.path.WithPath;

import java.util.ArrayList;
import java.util.List;

public interface Data<K extends Knowledge> extends DataProtocol<K>, WithPath, Localizable.WithName, WithIndependentConfigPage {
    @Override
    default String localizationKey(String... paths) {
        List<String> fullPaths = new ArrayList<>(List.of(path()));
        fullPaths.addAll(List.of(paths));

        return Knowledges.DATA.localizationKey(this, fullPaths.toArray(String[]::new));
    }
}
