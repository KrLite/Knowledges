package net.krlite.knowledges.impl;

import net.krlite.knowledges.api.Knowledge;
import net.krlite.knowledges.api.entrypoints.ComponentProvider;
import net.krlite.knowledges.components.ArmorDurabilityComponent;
import net.krlite.knowledges.components.CrosshairComponent;
import net.krlite.knowledges.components.info.BlockInfoComponent;
import net.krlite.knowledges.components.info.EntityInfoComponent;
import net.krlite.knowledges.components.info.FluidInfoComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DefaultComponentProvider implements ComponentProvider {
	@Override
	public @NotNull List<Class<? extends Knowledge<?>>> provide() {
		return List.of(
				CrosshairComponent.class,

				BlockInfoComponent.class,
				EntityInfoComponent.class,
				FluidInfoComponent.class,

				ArmorDurabilityComponent.class
		);
	}
}
