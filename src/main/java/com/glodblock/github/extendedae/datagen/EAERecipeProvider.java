package com.glodblock.github.extendedae.datagen;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import appeng.datagen.providers.tags.ConventionTags;
import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipeBuilder;
import appeng.recipes.transform.TransformCircumstance;
import appeng.recipes.transform.TransformRecipeBuilder;
import com.glodblock.github.appflux.common.AFItemAndBlock;
import com.glodblock.github.appflux.util.AFTags;
import com.glodblock.github.extendedae.ExtendedAE;
import com.glodblock.github.extendedae.common.EAEItemAndBlock;
import com.glodblock.github.extendedae.recipe.CircuitCutterRecipeBuilder;
import com.glodblock.github.extendedae.recipe.CrystalAssemblerRecipeBuilder;
import com.glodblock.github.extendedae.util.EAETags;
import com.glodblock.github.extendedae.xmod.ModConstants;
import gripe._90.megacells.definition.MEGAItems;
import gripe._90.megacells.definition.MEGATags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;

public class EAERecipeProvider extends RecipeProvider {

    static String C = "has_item";

    public EAERecipeProvider(PackOutput p) {
        super(p);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput c) {
        // Extended Pattern Provider
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_PATTERN_PROVIDER)
                .input(ConventionTags.PATTERN_PROVIDER)
                .input(AEItems.CAPACITY_CARD, 3)
                .input(Blocks.CRAFTING_TABLE, 3)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR)
                .input(ConventionTags.GLASS_CABLE, 6)
                .save(c, ExtendedAE.id("assembler/ex_pattern_provider"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.PATTERN_PROVIDER_UPGRADE)
                .requires(EAETags.EX_PATTERN_PROVIDER)
                .requires(Tags.Items.INGOTS)
                .unlockedBy(C, has(EAETags.EX_PATTERN_PROVIDER))
                .save(c, ExtendedAE.id("ex_pattern_provider_upgrade"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.EX_PATTERN_PROVIDER_PART)
                .requires(EAEItemAndBlock.EX_PATTERN_PROVIDER)
                .unlockedBy(C, has(EAEItemAndBlock.EX_PATTERN_PROVIDER_PART))
                .save(c, ExtendedAE.id("ex_pattern_provider_part"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.EX_PATTERN_PROVIDER)
                .requires(EAEItemAndBlock.EX_PATTERN_PROVIDER_PART)
                .unlockedBy(C, has(EAEItemAndBlock.EX_PATTERN_PROVIDER))
                .save(c, ExtendedAE.id("ex_pattern_provider_alt"));

        // Extended Interface
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_INTERFACE)
                .input(ConventionTags.INTERFACE)
                .input(AEItems.CAPACITY_CARD, 3)
                .input(ConventionTags.GLASS, 3)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR)
                .input(ConventionTags.GLASS_CABLE, 6)
                .save(c, ExtendedAE.id("assembler/ex_interface"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.INTERFACE_UPGRADE)
                .requires(EAETags.EX_INTERFACE)
                .requires(Tags.Items.INGOTS)
                .unlockedBy(C, has(EAETags.EX_INTERFACE))
                .save(c, ExtendedAE.id("ex_interface_upgrade"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.EX_INTERFACE_PART)
                .requires(EAEItemAndBlock.EX_INTERFACE)
                .unlockedBy(C, has(EAEItemAndBlock.EX_INTERFACE_PART))
                .save(c, ExtendedAE.id("ex_interface_part"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.EX_INTERFACE)
                .requires(EAEItemAndBlock.EX_INTERFACE_PART)
                .unlockedBy(C, has(EAEItemAndBlock.EX_INTERFACE_PART))
                .save(c, ExtendedAE.id("ex_interface_alt"));

        // Infinity Cell
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.INFINITY_CELL.getRecordCell(AEFluidKey.of(Fluids.WATER)))
                .pattern("CWC")
                .pattern("WXW")
                .pattern("III")
                .define('C', AEBlocks.QUARTZ_GLASS)
                .define('W', Items.WATER_BUCKET)
                .define('X', AEItems.CELL_COMPONENT_16K)
                .define('I', ConventionTags.DIAMOND)
                .unlockedBy(C, has(EAEItemAndBlock.INFINITY_CELL))
                .save(c, ExtendedAE.id("water_cell"));
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.INFINITY_CELL.getRecordCell(AEItemKey.of(Blocks.COBBLESTONE)))
                .pattern("CLC")
                .pattern("WXW")
                .pattern("III")
                .define('C', AEBlocks.QUARTZ_GLASS)
                .define('L', Items.LAVA_BUCKET)
                .define('W', Items.WATER_BUCKET)
                .define('X', AEItems.CELL_COMPONENT_16K)
                .define('I', ConventionTags.DIAMOND)
                .unlockedBy(C, has(EAEItemAndBlock.INFINITY_CELL))
                .save(c, ExtendedAE.id("cobblestone_cell"));

        // Extended IO Bus
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_EXPORT_BUS)
                .input(AEParts.EXPORT_BUS)
                .input(AEItems.SPEED_CARD, 3)
                .input(Blocks.PISTON, 2)
                .input(AEItems.FORMATION_CORE)
                .save(c, ExtendedAE.id("assembler/ex_export_bus"));
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_IMPORT_BUS)
                .input(AEParts.IMPORT_BUS)
                .input(AEItems.SPEED_CARD, 3)
                .input(Blocks.PISTON, 2)
                .input(AEItems.ANNIHILATION_CORE)
                .save(c, ExtendedAE.id("assembler/ex_import_bus"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.IO_BUS_UPGRADE)
                .requires(EAEItemAndBlock.EX_IMPORT_BUS)
                .requires(Tags.Items.INGOTS)
                .unlockedBy(C, has(EAEItemAndBlock.EX_IMPORT_BUS))
                .save(c, ExtendedAE.id("ex_bus_upgrade"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.IO_BUS_UPGRADE)
                .requires(EAEItemAndBlock.EX_EXPORT_BUS)
                .requires(Tags.Items.INGOTS)
                .unlockedBy(C, has(EAEItemAndBlock.EX_EXPORT_BUS))
                .save(c, ExtendedAE.id("ex_bus_upgrade_alt"));

        // Extended Pattern Access Terminal
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_PATTERN_TERMINAL)
                .input(AEParts.PATTERN_ACCESS_TERMINAL)
                .input(Blocks.REDSTONE_LAMP)
                .input(AEItems.LOGIC_PROCESSOR, 2)
                .input(AEBlocks.QUARTZ_FIXTURE, 4)
                .save(c, ExtendedAE.id("assembler/ex_pattern_access_terminal"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.PATTERN_UPGRADE)
                .requires(EAEItemAndBlock.EX_PATTERN_TERMINAL)
                .requires(Tags.Items.INGOTS)
                .unlockedBy(C, has(EAEItemAndBlock.EX_PATTERN_TERMINAL))
                .save(c, ExtendedAE.id("ex_pattern_access_terminal_upgrade"));

        // ME Packing Tape
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.PACKING_TAPE)
                .pattern("FG ")
                .pattern("PIP")
                .pattern(" GF")
                .define('I', ConventionTags.IRON_INGOT)
                .define('P', Items.PAPER)
                .define('G', Items.SLIME_BALL)
                .define('F', ConventionTags.FLUIX_DUST)
                .unlockedBy(C, has(EAEItemAndBlock.PACKING_TAPE))
                .save(c, ExtendedAE.id("tape"));

        // Wireless Connector
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.WIRELESS_CONNECTOR, 2)
                .input(EAEItemAndBlock.MACHINE_FRAME)
                .input(ConventionTags.SMART_DENSE_CABLE, 2)
                .input(AEItems.WIRELESS_RECEIVER, 2)
                .input(AEItems.WIRELESS_BOOSTER, 3)
                .save(c, ExtendedAE.id("assembler/wireless_connector"));
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.WIRELESS_TOOL)
                .input(AEItems.WIRELESS_RECEIVER)
                .input(ConventionTags.IRON_INGOT, 2)
                .input(AEItems.CALCULATION_PROCESSOR)
                .input(AEItems.SINGULARITY)
                .save(c, ExtendedAE.id("assembler/wireless_kit"));

        // Ingredient Buffer
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.INGREDIENT_BUFFER)
                .pattern("IKI")
                .pattern("G G")
                .pattern("IKI")
                .define('I', ConventionTags.IRON_INGOT)
                .define('K', AEItems.CELL_COMPONENT_1K)
                .define('G', AEBlocks.QUARTZ_GLASS)
                .unlockedBy(C, has(EAEItemAndBlock.INGREDIENT_BUFFER))
                .save(c, ExtendedAE.id("ingredient_buffer"));

        // Extended ME Drive
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_DRIVE)
                .input(AEBlocks.DRIVE)
                .input(ConventionTags.GLASS_CABLE, 2)
                .input(AEItems.CAPACITY_CARD)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR)
                .save(c, ExtendedAE.id("assembler/ex_drive"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.DRIVE_UPGRADE)
                .requires(EAEItemAndBlock.EX_DRIVE)
                .requires(Tags.Items.INGOTS)
                .unlockedBy(C, has(EAEItemAndBlock.EX_DRIVE))
                .save(c, ExtendedAE.id("ex_drive_upgrade"));

        // Pattern Modifier
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.PATTERN_MODIFIER)
                .pattern("GPG")
                .pattern(" L ")
                .define('G', ConventionTags.dye(DyeColor.GREEN))
                .define('P', AEItems.BLANK_PATTERN)
                .define('L', AEItems.LOGIC_PROCESSOR)
                .unlockedBy(C, has(EAEItemAndBlock.PATTERN_MODIFIER))
                .save(c, ExtendedAE.id("pattern_modifier"));

        // Extended Molecular Assembler
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_ASSEMBLER)
                .input(AEBlocks.MOLECULAR_ASSEMBLER, 4)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR, 4)
                .input(ConventionTags.FLUIX_DUST, 4)
                .input(AEItems.ENGINEERING_PROCESSOR, 3)
                .input(AEItems.SPEED_CARD)
                .save(c, ExtendedAE.id("assembler/ex_molecular_assembler"));

        // Extended Inscriber
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_INSCRIBER)
                .input(AEBlocks.INSCRIBER)
                .input(AEItems.CAPACITY_CARD, 3)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR)
                .save(c, ExtendedAE.id("assembler/ex_inscriber"));

        // Extended Charger
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_CHARGER)
                .input(AEBlocks.CHARGER)
                .input(AEItems.CAPACITY_CARD)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR)
                .save(c, ExtendedAE.id("assembler/ex_charger"));

        // Tag Storage Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.TAG_STORAGE_BUS)
                .pattern(" L ")
                .pattern("RBR")
                .pattern(" K ")
                .define('L', AEItems.LOGIC_PROCESSOR)
                .define('R', ConventionTags.REDSTONE)
                .define('B', AEParts.STORAGE_BUS)
                .define('K', Items.BOOK)
                .unlockedBy(C, has(EAEItemAndBlock.TAG_STORAGE_BUS))
                .save(c, ExtendedAE.id("tag_storage_bus"));

        // Tag Export Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.TAG_EXPORT_BUS)
                .pattern(" L ")
                .pattern("RBR")
                .pattern(" K ")
                .define('L', AEItems.LOGIC_PROCESSOR)
                .define('R', ConventionTags.REDSTONE)
                .define('B', AEParts.EXPORT_BUS)
                .define('K', Items.BOOK)
                .unlockedBy(C, has(EAEItemAndBlock.TAG_EXPORT_BUS))
                .save(c, ExtendedAE.id("tag_export_bus"));

        // Threshold Level Emitter
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.THRESHOLD_LEVEL_EMITTER)
                .pattern("RER")
                .pattern(" C ")
                .define('R', Items.REDSTONE_TORCH)
                .define('E', AEParts.LEVEL_EMITTER)
                .define('C', AEItems.CALCULATION_PROCESSOR)
                .unlockedBy(C, has(EAEItemAndBlock.THRESHOLD_LEVEL_EMITTER))
                .save(c, ExtendedAE.id("threshold_level_emitter"));

        // Mod Storage Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.MOD_STORAGE_BUS)
                .pattern(" C ")
                .pattern("RBR")
                .pattern(" K ")
                .define('C', AEItems.CALCULATION_PROCESSOR)
                .define('R', ConventionTags.REDSTONE)
                .define('B', AEParts.STORAGE_BUS)
                .define('K', Items.BOOK)
                .unlockedBy(C, has(EAEItemAndBlock.MOD_STORAGE_BUS))
                .save(c, ExtendedAE.id("mod_storage_bus"));

        // Mod Export Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.MOD_EXPORT_BUS)
                .pattern(" C ")
                .pattern("RBR")
                .pattern(" K ")
                .define('C', AEItems.CALCULATION_PROCESSOR)
                .define('R', ConventionTags.REDSTONE)
                .define('B', AEParts.EXPORT_BUS)
                .define('K', Items.BOOK)
                .unlockedBy(C, has(EAEItemAndBlock.MOD_EXPORT_BUS))
                .save(c, ExtendedAE.id("mod_export_bus"));

        // Active Formation Plane
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.ACTIVE_FORMATION_PLANE)
                .input(AEParts.FORMATION_PLANE)
                .input(Blocks.PISTON, 3)
                .input(AEItems.FORMATION_CORE, 2)
                .input(EAEItemAndBlock.EX_EXPORT_BUS)
                .save(c, ExtendedAE.id("assembler/active_formation_plane"));

        // ME Caner
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.CANER)
                .input(EAEItemAndBlock.MACHINE_FRAME)
                .input(EAEItemAndBlock.INGREDIENT_BUFFER)
                .input(AEItems.FORMATION_CORE)
                .input(AEItems.ANNIHILATION_CORE)
                .save(c, ExtendedAE.id("assembler/caner"));

        // ME Precise Export Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.PRECISE_EXPORT_BUS)
                .pattern("PBP")
                .define('B', EAEItemAndBlock.EX_EXPORT_BUS)
                .define('P', AEItems.CALCULATION_PROCESSOR)
                .unlockedBy(C, has(EAEItemAndBlock.EX_EXPORT_BUS))
                .save(c, ExtendedAE.id("precise_export_bus"));

        // Wireless Ex PAT
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.WIRELESS_EX_PAT)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .define('A', AEItems.WIRELESS_RECEIVER)
                .define('B', EAEItemAndBlock.EX_PATTERN_TERMINAL)
                .define('C', AEBlocks.DENSE_ENERGY_CELL)
                .unlockedBy(C, has(EAEItemAndBlock.EX_PATTERN_TERMINAL))
                .save(c, ExtendedAE.id("wireless_ex_pat"));

        // Extended IO Port
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.EX_IO_PORT)
                .input(AEBlocks.IO_PORT)
                .input(AEItems.SPEED_CARD, 4)
                .input(AEItems.LOGIC_PROCESSOR, 3)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR, 2)
                .save(c, ExtendedAE.id("assembler/ex_io_port"));

        // Crystal Fixer
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.CRYSTAL_FIXER)
                .input(EAEItemAndBlock.MACHINE_FRAME)
                .input(ConventionTags.ALL_CERTUS_QUARTZ, 4)
                .input(AEParts.QUARTZ_FIBER, 2)
                .save(c, ExtendedAE.id("assembler/crystal_fixer"));

        // Precise Storage Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.PRECISE_STORAGE_BUS)
                .pattern("PBP")
                .define('B', AEParts.STORAGE_BUS)
                .define('P', AEItems.CALCULATION_PROCESSOR)
                .unlockedBy(C, has(AEParts.STORAGE_BUS))
                .save(c, ExtendedAE.id("precise_storage_bus"));

        // Threshold Export Bus
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.THRESHOLD_EXPORT_BUS)
                .pattern("LCE")
                .define('L', AEParts.LEVEL_EMITTER)
                .define('C', AEItems.LOGIC_PROCESSOR)
                .define('E', AEParts.EXPORT_BUS)
                .unlockedBy(C, has(AEParts.LEVEL_EMITTER))
                .save(c, ExtendedAE.id("threshold_export_bus"));

        // Fishbig
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.FISHBIG)
                .pattern("FFF")
                .pattern("F F")
                .pattern("FFF")
                .define('F', Items.PUFFERFISH)
                .unlockedBy(C, has(EAEItemAndBlock.FISHBIG))
                .save(c, ExtendedAE.id("fishbig"));

        // Entro Seed
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.ENTRO_SEED)
                .requires(Tags.Items.SAND)
                .requires(ConventionTags.ENDER_PEARL_DUST)
                .requires(ConventionTags.ENDER_PEARL_DUST)
                .requires(ConventionTags.ENDER_PEARL_DUST)
                .requires(ConventionTags.REDSTONE)
                .requires(ConventionTags.REDSTONE)
                .requires(ConventionTags.GLOWSTONE)
                .requires(ConventionTags.GLOWSTONE)
                .requires(AEItems.SKY_DUST)
                .unlockedBy(C, has(ConventionTags.ENDER_PEARL_DUST))
                .save(c, ExtendedAE.id("entro_seed"));

        // Entro Dust
        InscriberRecipeBuilder
                .inscribe(EAETags.ENTRO_CRYSTAL, EAEItemAndBlock.ENTRO_DUST, 1)
                .setMode(InscriberProcessType.INSCRIBE)
                .save(c, ExtendedAE.id("inscriber/crush_entro"));

        // Entro Crystal
        TransformRecipeBuilder
                .transform(c,
                        ExtendedAE.id("transform/fix_entro"),
                        EAEItemAndBlock.ENTRO_CRYSTAL, 1,
                        TransformCircumstance.fluid(FluidTags.WATER),
                        Ingredient.of(EAETags.ENTRO_DUST),
                        Ingredient.of(AEItems.FLUIX_CRYSTAL)
                );

        // Entro Ingot
        TransformRecipeBuilder
                .transform(c,
                        ExtendedAE.id("transform/entro_ingot"),
                        EAEItemAndBlock.ENTRO_INGOT, 1,
                        TransformCircumstance.fluid(FluidTags.WATER),
                        Ingredient.of(EAETags.ENTRO_DUST),
                        Ingredient.of(ConventionTags.GOLD_INGOT),
                        Ingredient.of(Items.LAPIS_LAZULI)
                );

        // Machine Frame
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.MACHINE_FRAME)
                .pattern("ECE")
                .pattern("IGI")
                .pattern("ECE")
                .define('E', EAETags.ENTRO_INGOT)
                .define('C', ConventionTags.COPPER_INGOT)
                .define('I', ConventionTags.IRON_INGOT)
                .define('G', AEBlocks.QUARTZ_GLASS)
                .unlockedBy(C, has(EAETags.ENTRO_INGOT))
                .save(c, ExtendedAE.id("machine_frame"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.MACHINE_FRAME)
                .pattern("EIE")
                .pattern("CGC")
                .pattern("EIE")
                .define('E', EAETags.ENTRO_INGOT)
                .define('C', ConventionTags.COPPER_INGOT)
                .define('I', ConventionTags.IRON_INGOT)
                .define('G', AEBlocks.QUARTZ_GLASS)
                .unlockedBy(C, has(EAETags.ENTRO_INGOT))
                .save(c, ExtendedAE.id("machine_frame_mirror"));

        // Crystal Assembler
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.CRYSTAL_ASSEMBLER)
                .pattern(" T ")
                .pattern("LML")
                .pattern("CKC")
                .define('T', AEParts.CRAFTING_TERMINAL)
                .define('L', AEItems.LOGIC_PROCESSOR)
                .define('M', EAEItemAndBlock.MACHINE_FRAME)
                .define('C', ConventionTags.GLASS_CABLE)
                .define('K', AEBlocks.SKY_STONE_TANK)
                .unlockedBy(C, has(EAEItemAndBlock.MACHINE_FRAME))
                .save(c, ExtendedAE.id("crystal_assembler"));

        // Concurrent Processor
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.CONCURRENT_PROCESSOR_PRESS)
                .input(Items.ENDER_EYE, 3)
                .input(EAETags.ENTRO_CRYSTAL, 4)
                .input(AEItems.SILICON_PRESS)
                .save(c, ExtendedAE.id("assembler/concurrent_press"));

        InscriberRecipeBuilder
                .inscribe(EAETags.ENTRO_CRYSTAL, EAEItemAndBlock.CONCURRENT_PROCESSOR_PRINT, 1)
                .setTop(Ingredient.of(EAEItemAndBlock.CONCURRENT_PROCESSOR_PRESS))
                .setMode(InscriberProcessType.INSCRIBE)
                .save(c, ExtendedAE.id("inscriber/concurrent_print"));

        InscriberRecipeBuilder.inscribe(ConventionTags.REDSTONE, EAEItemAndBlock.CONCURRENT_PROCESSOR, 1)
                .setTop(Ingredient.of(EAEItemAndBlock.CONCURRENT_PROCESSOR_PRINT))
                .setBottom(Ingredient.of(AEItems.SILICON_PRINT))
                .setMode(InscriberProcessType.PRESS)
                .save(c, ExtendedAE.id("inscriber/concurrent_process"));

        // Entro Block
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.ENTRO_BLOCK)
                .pattern("CC")
                .pattern("CC")
                .define('C', EAETags.ENTRO_CRYSTAL)
                .unlockedBy(C, has(EAETags.ENTRO_CRYSTAL))
                .save(c, ExtendedAE.id("entro_block"));

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.ENTRO_CRYSTAL, 4)
                .requires(EAETags.ENTRO_BLOCK)
                .unlockedBy(C, has(EAETags.ENTRO_BLOCK))
                .save(c, ExtendedAE.id("entro_block_unpack"));

        // Budding Block
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.MOSTLY_ENTROIZED_FLUIX_BUDDING)
                .input(EAEItemAndBlock.ENTRO_SEED)
                .input(AEBlocks.FLUIX_BLOCK)
                .fluid(Fluids.WATER, 200)
                .save(c, ExtendedAE.id("assembler/budding"));

        // Silicon BLock
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, EAEItemAndBlock.SILICON_BLOCK)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ConventionTags.SILICON)
                .unlockedBy(C, has(ConventionTags.SILICON))
                .save(c, ExtendedAE.id("silicon_block"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, AEItems.SILICON, 9)
                .requires(EAETags.SILICON_BLOCK)
                .unlockedBy(C, has(EAETags.SILICON_BLOCK))
                .save(c, ExtendedAE.id("silicon_decompress"));

        // Circuit Cutter
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.CIRCUIT_CUTTER)
                .input(EAEItemAndBlock.MACHINE_FRAME)
                .input(AEItems.ENGINEERING_PROCESSOR, 8)
                .input(AEItems.CALCULATION_PROCESSOR_PRESS)
                .input(AEItems.ENGINEERING_PROCESSOR_PRESS)
                .input(AEItems.LOGIC_PROCESSOR_PRESS)
                .input(AEItems.SILICON_PRESS)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR_PRESS)
                .input(Blocks.STONECUTTER)
                .save(c, ExtendedAE.id("assembler/circuit_cutter"));

        // Oversize Interface
        CrystalAssemblerRecipeBuilder
                .assemble(EAEItemAndBlock.OVERSIZE_INTERFACE)
                .input(EAETags.EX_INTERFACE)
                .input(EAEItemAndBlock.INGREDIENT_BUFFER)
                .input(EAEItemAndBlock.CONCURRENT_PROCESSOR, 2)
                .input(AEItems.ANNIHILATION_CORE, 2)
                .input(AEItems.FORMATION_CORE, 2)
                .save(c, ExtendedAE.id("assembler/oversize_interface"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.OVERSIZE_INTERFACE_PART)
                .requires(EAEItemAndBlock.OVERSIZE_INTERFACE)
                .unlockedBy(C, has(EAEItemAndBlock.OVERSIZE_INTERFACE))
                .save(c, ExtendedAE.id("oversize_interface_part"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, EAEItemAndBlock.OVERSIZE_INTERFACE)
                .requires(EAEItemAndBlock.OVERSIZE_INTERFACE_PART)
                .unlockedBy(C, has(EAEItemAndBlock.OVERSIZE_INTERFACE_PART))
                .save(c, ExtendedAE.id("oversize_interface_alt"));

        transformation(c);
        circuit(c);

        if (ExtendedAE.isLoad(ModConstants.MEGA)) {
            maga(c);
        }
        if (ExtendedAE.isLoad(ModConstants.APPFLUX)) {
            appflux(c);
        }

    }

    private void transformation(@NotNull RecipeOutput c) {
        // Fluix
        CrystalAssemblerRecipeBuilder
                .assemble(AEItems.FLUIX_CRYSTAL, 8)
                .input(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED, 4)
                .input(ConventionTags.REDSTONE, 4)
                .input(ConventionTags.NETHER_QUARTZ, 4)
                .fluid(Fluids.WATER, 100)
                .save(c, ExtendedAE.id("assembler/fluix_transformation"));
    }

    private void circuit(@NotNull RecipeOutput c) {
        // Four AE Processors
        CircuitCutterRecipeBuilder
                .cut(AEItems.ENGINEERING_PROCESSOR_PRINT, 9)
                .input(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .save(c, ExtendedAE.id("cutter/engineering_processor"));
        CircuitCutterRecipeBuilder
                .cut(AEItems.LOGIC_PROCESSOR_PRINT, 9)
                .input(Tags.Items.STORAGE_BLOCKS_GOLD)
                .save(c, ExtendedAE.id("cutter/logic_processor"));
        CircuitCutterRecipeBuilder
                .cut(AEItems.CALCULATION_PROCESSOR_PRINT, 4)
                .input(AEBlocks.QUARTZ_BLOCK)
                .save(c, ExtendedAE.id("cutter/calculation_processor"));
        CircuitCutterRecipeBuilder
                .cut(AEItems.SILICON_PRINT, 9)
                .input(EAETags.SILICON_BLOCK)
                .save(c, ExtendedAE.id("cutter/silicon_print"));

        // Entro Processor
        CircuitCutterRecipeBuilder
                .cut(EAEItemAndBlock.CONCURRENT_PROCESSOR_PRINT, 4)
                .input(EAETags.ENTRO_BLOCK)
                .save(c, ExtendedAE.id("cutter/concurrent_processor"));

        // Troll
        CircuitCutterRecipeBuilder
                .cut(Items.PUFFERFISH, 8)
                .input(EAEItemAndBlock.FISHBIG)
                .save(c, ExtendedAE.id("cutter/fishbig_destroy"));

    }

    private void appflux(@NotNull RecipeOutput c) {
        // Redstone Crystal
        CrystalAssemblerRecipeBuilder
                .assemble(AFItemAndBlock.REDSTONE_CRYSTAL, 4)
                .input(Tags.Items.STORAGE_BLOCKS_REDSTONE, 4)
                .input(AEBlocks.FLUIX_BLOCK, 4)
                .input(AFTags.DIAMOND_DUST, 4)
                .fluid(Fluids.WATER, 100)
                .save(c.withConditions(mod(ModConstants.APPFLUX)), ExtendedAE.id("assembler/redstone_crystal"));
    }

    private void maga(@NotNull RecipeOutput c) {
        // Sky steel
        CrystalAssemblerRecipeBuilder
                .assemble(MEGAItems.SKY_STEEL_INGOT, 8)
                .input(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED, 4)
                .input(ConventionTags.IRON_INGOT, 4)
                .input(AEItems.SKY_DUST, 4)
                .fluid(Fluids.LAVA, 100)
                .save(c.withConditions(mod(ModConstants.MEGA)), ExtendedAE.id("assembler/sky_steel"));

        // Accumulation Processor
        CircuitCutterRecipeBuilder
                .cut(MEGAItems.ACCUMULATION_PROCESSOR_PRINT, 9)
                .input(MEGATags.SKY_STEEL_BLOCK_ITEM)
                .save(c.withConditions(mod(ModConstants.MEGA)), ExtendedAE.id("cutter/accumulation_processor"));
    }

    private ICondition mod(String modid) {
        return new ModLoadedCondition(modid);
    }

}
