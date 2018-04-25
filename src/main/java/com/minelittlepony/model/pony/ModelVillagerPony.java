package com.minelittlepony.model.pony;

import com.minelittlepony.renderer.plane.PlaneRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.MathHelper;
import static com.minelittlepony.model.PonyModelConstants.*;

public class ModelVillagerPony extends ModelPlayerPony {

    public PlaneRenderer[] VillagerBagPiece;
    public PlaneRenderer VillagerApron;
    public PlaneRenderer VillagerTrinket;

    public ModelVillagerPony() {
        super(false);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        float bodySwingRotation = 0.0F;
        if (this.swingProgress > -9990.0F && !this.metadata.hasMagic()) {
            bodySwingRotation = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 3.1415927F * 2.0F) * 0.2F;
        }
        for (PlaneRenderer aVillagerBagPiece : this.VillagerBagPiece) {
            aVillagerBagPiece.rotateAngleY = bodySwingRotation * 0.2F;
        }

        this.VillagerBagPiece[4].rotateAngleY += 4.712389F;
        this.VillagerBagPiece[5].rotateAngleY += 4.712389F;
        this.VillagerBagPiece[6].rotateAngleY += 4.712389F;
        this.VillagerBagPiece[7].rotateAngleY += 4.712389F;
        this.VillagerApron.rotateAngleY = bodySwingRotation * 0.2F;
        this.VillagerTrinket.rotateAngleY = bodySwingRotation * 0.2F;
    }

    @Override
    protected void renderBody(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.renderBody(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        if (entityIn instanceof EntityVillager) {
            this.bipedBody.postRender(this.scale);
            int profession = ((EntityVillager) entityIn).getProfession();
            if (profession < 2) {
                for (PlaneRenderer aVillagerBagPiece : this.VillagerBagPiece) {
                    aVillagerBagPiece.render(this.scale);
                }
            } else if (profession == 2) {
                this.VillagerTrinket.render(this.scale);
            } else if (profession > 2) {
                this.VillagerApron.render(this.scale);
            }
        }
    }

    @Override
    protected void initTextures() {
        super.initTextures();
        this.VillagerBagPiece = new PlaneRenderer[14];
        this.VillagerBagPiece[0] = new PlaneRenderer(this, 56, 19);
        this.VillagerBagPiece[1] = new PlaneRenderer(this, 56, 19);
        this.VillagerBagPiece[2] = new PlaneRenderer(this, 56, 19);
        this.VillagerBagPiece[3] = new PlaneRenderer(this, 56, 19);
        this.VillagerBagPiece[4] = new PlaneRenderer(this, 56, 16);
        this.VillagerBagPiece[5] = new PlaneRenderer(this, 56, 16);
        this.VillagerBagPiece[6] = new PlaneRenderer(this, 56, 22);
        this.VillagerBagPiece[7] = new PlaneRenderer(this, 56, 22);
        this.VillagerBagPiece[8] = new PlaneRenderer(this, 56, 25);
        this.VillagerBagPiece[9] = new PlaneRenderer(this, 56, 25);
        this.VillagerBagPiece[10] = new PlaneRenderer(this, 59, 25);
        this.VillagerBagPiece[11] = new PlaneRenderer(this, 59, 25);
        this.VillagerBagPiece[12] = new PlaneRenderer(this, 56, 31);
        this.VillagerBagPiece[13] = new PlaneRenderer(this, 56, 31);
        this.VillagerApron = new PlaneRenderer(this, 56, 16);
        this.VillagerTrinket = new PlaneRenderer(this, 0, 3);
    }

    @Override
    protected void initPositions(float yOffset, float stretch) {
        super.initPositions(yOffset, stretch);
        this.VillagerBagPiece[0].addWestPlane(-7.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -4.0F + BODY_CENTRE_Z, 6, 8, stretch);
        this.VillagerBagPiece[0].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[1].addWestPlane(-4.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -4.0F + BODY_CENTRE_Z, 6, 8, stretch);
        this.VillagerBagPiece[1].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[2].addWestPlane(4.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -4.0F + BODY_CENTRE_Z, 6, 8, stretch);
        this.VillagerBagPiece[2].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[3].addWestPlane(7.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -4.0F + BODY_CENTRE_Z, 6, 8, stretch);
        this.VillagerBagPiece[3].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[4].addTopPlane(2.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -2.0F + BODY_CENTRE_Z, 8, 3, stretch);
        this.VillagerBagPiece[4].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[5].addTopPlane(2.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -13.0F + BODY_CENTRE_Z, 8, 3, stretch);
        this.VillagerBagPiece[5].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[6].addBottomPlane(2.0F + BODY_CENTRE_X, 1.0F + BODY_CENTRE_Y, -2.0F + BODY_CENTRE_Z, 8, 3, stretch);
        this.VillagerBagPiece[6].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[7].addBottomPlane(2.0F + BODY_CENTRE_X, 1.0F + BODY_CENTRE_Y, -13.0F + BODY_CENTRE_Z, 8, 3, stretch);
        this.VillagerBagPiece[7].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[8].addBackPlane(-7.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -4.0F + BODY_CENTRE_Z, 3, 6, stretch);
        this.VillagerBagPiece[8].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[9].addBackPlane(4.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, -4.0F + BODY_CENTRE_Z, 3, 6, stretch);
        this.VillagerBagPiece[9].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[10].addBackPlane(-7.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, 4.0F + BODY_CENTRE_Z, 3, 6, stretch);
        this.VillagerBagPiece[10].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[11].addBackPlane(4.0F + BODY_CENTRE_X, -5.0F + BODY_CENTRE_Y, 4.0F + BODY_CENTRE_Z, 3, 6, stretch);
        this.VillagerBagPiece[11].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[12].addTopPlane(-4.0F + BODY_CENTRE_X, -4.5F + BODY_CENTRE_Y, -1.0F + BODY_CENTRE_Z, 8, 1, stretch);
        this.VillagerBagPiece[13].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerBagPiece[13].addTopPlane(-4.0F + BODY_CENTRE_X, -4.5F + BODY_CENTRE_Y, 0.0F + BODY_CENTRE_Z, 8, 1, stretch);
        this.VillagerBagPiece[13].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerApron.addBackPlane(-4.0F + BODY_CENTRE_X, -4.0F + BODY_CENTRE_Y, -9.0F + BODY_CENTRE_Z, 8, 10, stretch);
        this.VillagerApron.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.VillagerTrinket.addBackPlane(-2.0F + BODY_CENTRE_X, -4.0F + BODY_CENTRE_Y, -9.0F + BODY_CENTRE_Z, 4, 5, stretch);
        this.VillagerTrinket.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
    }
}
