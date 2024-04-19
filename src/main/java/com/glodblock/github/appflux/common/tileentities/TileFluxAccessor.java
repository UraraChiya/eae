package com.glodblock.github.appflux.common.tileentities;

import appeng.api.networking.GridFlags;
import appeng.api.networking.IGridNode;
import appeng.api.networking.security.IActionSource;
import appeng.api.networking.storage.IStorageService;
import appeng.api.networking.ticking.IGridTickable;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.api.networking.ticking.TickingRequest;
import appeng.blockentity.grid.AENetworkBlockEntity;
import com.glodblock.github.appflux.common.AFItemAndBlock;
import com.glodblock.github.appflux.common.me.energy.CapAdaptor;
import com.glodblock.github.appflux.common.me.energy.EnergyDistributor;
import com.glodblock.github.appflux.util.AFUtil;
import com.glodblock.github.glodium.util.GlodUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileFluxAccessor extends AENetworkBlockEntity implements IGridTickable {

    public TileFluxAccessor(BlockPos pos, BlockState blockState) {
        super(GlodUtil.getTileType(TileFluxAccessor.class, TileFluxAccessor::new, AFItemAndBlock.FLUX_ACCESSOR), pos, blockState);
        this.getMainNode().setFlags(GridFlags.REQUIRE_CHANNEL);
        this.getMainNode().setIdlePowerUsage(1.0).addService(IGridTickable.class, this);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        CapAdaptor.Factory<T> handler = CapAdaptor.find(cap);
        if (handler != null) {
            return LazyOptional.of(() -> handler.create(this.getStorage(), this.getSource()));
        }
        return super.getCapability(cap, side);
    }

    private IStorageService getStorage() {
        if (this.getGridNode() != null) {
            return this.getGridNode().getGrid().getStorageService();
        }
        return null;
    }

    private IActionSource getSource() {
        return IActionSource.ofMachine(this);
    }

    @Override
    public TickingRequest getTickingRequest(IGridNode node) {
        return new TickingRequest(1, 1, false, false);
    }

    @Override
    public TickRateModulation tickingRequest(IGridNode node, int ticksSinceLastCall) {
        var storage = this.getStorage();
        var gird = AFUtil.getGrid(this, null);
        if (storage != null && this.level != null) {
            for (var d : Direction.values()) {
                var te = this.level.getBlockEntity(this.worldPosition.offset(d.getNormal()));
                var thatGrid = AFUtil.getGrid(te, d.getOpposite());
                if (te != null && thatGrid != gird && !AFUtil.isBlackListTE(te, d.getOpposite())) {
                    EnergyDistributor.send(te, d.getOpposite(), storage, this.getSource());
                }
            }
        }
        return TickRateModulation.SAME;
    }

}