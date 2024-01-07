package net.krlite.knowledges.mixin.client.clothconfig;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import me.shedaniel.clothconfig2.gui.ClothConfigTabButton;
import net.krlite.knowledges.config.modmenu.impl.ClothConfigTabButtonSeparator;
import net.krlite.knowledges.config.modmenu.impl.ConfigCategorySeparator;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(ClothConfigScreen.class)
public abstract class ClothConfigScreenMixin {
    @Shadow
    @Final
    private List<Pair<Text, Integer>> tabs;

    @Shadow
    @Final
    private List<ClothConfigTabButton> tabButtons;

    @Shadow
    @Final
    private Map<String, ConfigCategory> categoryMap;

    @Shadow protected abstract void init();

    @Unique
    private ClothConfigScreen screen;

    @Redirect(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 5
            )
    )
    private <E> boolean omit(List<E> instance, E e) {
        // Do nothing
        screen = ((ClothConfigTabButtonAccessor) e).getScreen();
        return false;
    }

    @Inject(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z",
                    ordinal = 1
            )
    )
    private void addCategoryTabs(CallbackInfo ci) {
        int index = 0;

        for (Iterator<Pair<Text, Integer>> var3 = tabs.iterator(); var3.hasNext(); ++index) {
            Pair<Text, Integer> tab = var3.next();
            ConfigCategory category = categoryMap.get(tab.getLeft().getString());

            if (category instanceof ConfigCategorySeparator) {
                tabButtons.add(new ClothConfigTabButtonSeparator(screen, index, -100, 43, tab.getRight(), 20, ((ConfigCategorySeparator) category).getSeparator(), category.getDescription()));
            } else {
                tabButtons.add(new ClothConfigTabButton(screen, index, -100, 43, tab.getRight(), 20, tab.getLeft(), category.getDescription()));
            }
        }
    }
}
