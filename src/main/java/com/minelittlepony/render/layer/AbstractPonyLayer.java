package com.minelittlepony.render.layer;

import com.minelittlepony.model.ponies.ModelHumanPlayer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public abstract class AbstractPonyLayer<T extends EntityLivingBase> implements LayerRenderer<T> {

    private final RenderLivingBase<? extends T> renderer;
    private LayerRenderer<T> layer;

    public AbstractPonyLayer(RenderLivingBase<? extends T> renderer, LayerRenderer<T> humanLayer) {
        this.renderer = renderer;
        this.layer = humanLayer;
    }

    public final void doRenderLayer(T entity, float limbSwing, float limbSwingAmount, float ticks, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {
        ModelBase model = renderer.getMainModel();
        if (model instanceof ModelHumanPlayer) {
            // render the human layer
            layer.doRenderLayer(entity, limbSwing, limbSwingAmount, ticks, ageInTicks, netHeadYaw, headPitch, scale);
        } else {
            // render the pony layer
            doPonyRender(entity, limbSwing, limbSwingAmount, ticks, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    protected abstract void doPonyRender(T entity, float limbSwing, float limbSwingAmount, float ticks, float ageInTicks, float netHeadYaw, float headPitch, float scale);

    protected RenderLivingBase<? extends T> getRenderer() {
        return renderer;
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
