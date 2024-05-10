package com.github.glodblock.extendedae.client.gui;

import appeng.client.gui.implementations.UpgradeableScreen;
import appeng.client.gui.style.PaletteColor;
import appeng.client.gui.style.ScreenStyle;
import appeng.client.gui.widgets.ProgressBar;
import com.github.glodblock.extendedae.client.button.ActionEPPButton;
import com.github.glodblock.extendedae.client.button.EPPIcon;
import com.github.glodblock.extendedae.common.tileentities.TileExMolecularAssembler;
import com.github.glodblock.extendedae.container.ContainerExMolecularAssembler;
import com.github.glodblock.extendedae.network.EAENetworkServer;
import com.github.glodblock.extendedae.network.packet.CGenericPacket;
import com.github.glodblock.extendedae.network.packet.CUpdatePage;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiExMolecularAssembler extends UpgradeableScreen<ContainerExMolecularAssembler> {

    private final ProgressBar pb;
    private final ActionEPPButton next;
    private final ActionEPPButton pre;

    public GuiExMolecularAssembler(ContainerExMolecularAssembler menu, Inventory playerInventory, Component title, ScreenStyle style) {
        super(menu, playerInventory, title, style);
        this.pb = new ProgressBar(this.menu, style.getImage("progressBar"), ProgressBar.Direction.VERTICAL);
        this.widgets.add("progressBar", this.pb);
        this.next = new ActionEPPButton(b -> EAENetworkServer.INSTANCE.sendToServer(new CUpdatePage(() -> (this.menu.page + 1) % TileExMolecularAssembler.MAX_THREAD)), EPPIcon.RIGHT);
        this.pre = new ActionEPPButton(b -> EAENetworkServer.INSTANCE.sendToServer(new CUpdatePage(() -> (this.menu.page - 1) % TileExMolecularAssembler.MAX_THREAD)), EPPIcon.LEFT);
        this.next.setMessage(Component.translatable("gui.extendedae.ex_molecular_assembler.next"));
        this.pre.setMessage(Component.translatable("gui.extendedae.ex_molecular_assembler.pre"));
        addToLeftToolbar(this.next);
        addToLeftToolbar(this.pre);
    }

    @Override
    protected void updateBeforeRender() {
        super.updateBeforeRender();
        this.next.setVisibility(true);
        this.pre.setVisibility(true);
        if (this.menu.page == 0) {
            this.pre.setVisibility(false);
        }
        if (this.menu.page == TileExMolecularAssembler.MAX_THREAD - 1) {
            this.next.setVisibility(false);
        }
        EAENetworkServer.INSTANCE.sendToServer(new CGenericPacket("show"));
        this.menu.showPage();
        this.pb.setFullMsg(Component.literal(this.menu.getCurrentProgress() + "%"));
    }

    @Override
    public void drawFG(GuiGraphics guiGraphics, int offsetX, int offsetY, int mouseX, int mouseY) {
        guiGraphics.drawString(
                this.font,
                Component.translatable("gui.extendedae.ex_molecular_assembler.number", this.menu.page + 1),
                8,
                18,
                style.getColor(PaletteColor.DEFAULT_TEXT_COLOR).toARGB(),
                false
        );
    }

}