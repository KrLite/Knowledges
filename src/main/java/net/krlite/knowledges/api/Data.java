package net.krlite.knowledges.api;

import net.krlite.knowledges.Knowledges;
import net.krlite.knowledges.core.LocalizableWithName;
import net.krlite.knowledges.core.Target;
import net.krlite.knowledges.core.WithPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Data<T extends Enum<T> & Target, P, R> extends WithPath, LocalizableWithName, Target.Consumer<T> {
    Optional<R> get(P param);

    Class<? extends Knowledge<?>> targetClass();

    default Optional<Knowledge<?>> targetKnowledge() {
        return Knowledges.COMPONENTS.byClass(targetClass());
    }

    @Override
    default String localizationKey(String... paths) {
        List<String> fullPaths = new ArrayList<>(List.of(path()));
        fullPaths.addAll(List.of(paths));

        return Knowledges.DATA.localizationKey(this, fullPaths.toArray(String[]::new));
    }
}
