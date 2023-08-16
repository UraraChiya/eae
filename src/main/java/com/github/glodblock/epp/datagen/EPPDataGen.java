package com.github.glodblock.epp.datagen;

import com.github.glodblock.epp.EPP;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EPP.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EPPDataGen {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent dataEvent) {
        var gen = dataEvent.getGenerator();
        var file = dataEvent.getExistingFileHelper();
        var block = new EPPBlockTagProvider(gen, file);
        gen.addProvider(true, block);
        gen.addProvider(true, new EPPRecipeProvider(gen));
        gen.addProvider(true, new EPPLootTableProvider(gen.getOutputFolder()));
        gen.addProvider(true, new EPPItemTagsProvider(gen, block, file));
    }

}