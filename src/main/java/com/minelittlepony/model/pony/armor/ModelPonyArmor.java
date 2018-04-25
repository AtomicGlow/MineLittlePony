package com.minelittlepony.model.pony.armor;

import com.minelittlepony.model.pony.ModelPlayerPony;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import static com.minelittlepony.model.PonyModelConstants.*;

public class ModelPonyArmor extends ModelPlayerPony {

    public ModelRenderer Bodypiece;
    public ModelRenderer extBody;
    public ModelRenderer[] extHead;
    public ModelRenderer[] extLegs;

    public ModelPonyArmor() {
        super(false);
        this.textureHeight = 32;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        this.checkRainboom(entityIn, limbSwingAmount);
        this.rotateHead(netHeadYaw, headPitch);
        float bodySwingRotation = 0.0F;
        if (this.swingProgress > -9990.0F && !this.metadata.hasMagic()) {
            bodySwingRotation = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 3.1415927F * 2.0F) * 0.2F;
        }

        this.bipedBody.rotateAngleY = bodySwingRotation * 0.2F;

        this.setLegs(limbSwing, limbSwingAmount, ageInTicks, entityIn);
        this.holdItem(limbSwingAmount);
        this.swingItem(entityIn, this.swingProgress);
        if (this.isSneak && !this.isFlying) {
            this.adjustBody(BODY_ROTATE_ANGLE_X_SNEAK, BODY_RP_Y_SNEAK, BODY_RP_Z_SNEAK);
            this.sneakLegs();
            this.setHead(0.0F, 6.0F, -2.0F);
        } else if (this.isRiding) {

            this.adjustBody(BODY_ROTATE_ANGLE_X_RIDING, BODY_RP_Y_RIDING, BODY_RP_Z_RIDING);
            this.bipedLeftLeg.rotationPointZ = 15;
            this.bipedLeftLeg.rotationPointY = 10;
            this.bipedLeftLeg.rotateAngleX = (float) (Math.PI * -0.25);
            this.bipedLeftLeg.rotateAngleY = (float) (Math.PI * -0.2);

            this.bipedRightLeg.rotationPointZ = 15;
            this.bipedRightLeg.rotationPointY = 10;
            this.bipedRightLeg.rotateAngleX = (float) (Math.PI * -0.25);
            this.bipedRightLeg.rotateAngleY = (float) (Math.PI * 0.2);


            this.bipedLeftArm.rotateAngleZ = (float) (Math.PI * -0.06);
            this.bipedRightArm.rotateAngleZ = (float) (Math.PI * 0.06);

        } else {

            this.adjustBody(BODY_ROTATE_ANGLE_X_NOTSNEAK, BODY_RP_Y_NOTSNEAK, BODY_RP_Z_NOTSNEAK);

            this.bipedRightLeg.rotationPointY = FRONT_LEG_RP_Y_NOTSNEAK;
            this.bipedLeftLeg.rotationPointY = FRONT_LEG_RP_Y_NOTSNEAK;
            this.swingArms(ageInTicks);
            this.setHead(0.0F, 0.0F, 0.0F);

        }


        if (this.isSleeping) {
            this.ponySleep();
        }

        this.aimBow(leftArmPose, rightArmPose, ageInTicks);


    }

    @Override
    protected void setHead(float posX, float posY, float posZ) {
        this.bipedHead.setRotationPoint(posX, posY, posZ);
        this.bipedHeadwear.setRotationPoint(posX, posY, posZ);
        this.extHead[0].setRotationPoint(posX, posY, posZ);
        this.extHead[1].setRotationPoint(posX, posY, posZ);
    }

    @Override
    protected void rotateHead(float horz, float vert) {
        super.rotateHead(horz, vert);

        float headRotateAngleX = this.bipedHead.rotateAngleX;
        float headRotateAngleY = this.bipedHead.rotateAngleY;

        this.extHead[0].rotateAngleY = headRotateAngleY;
        this.extHead[0].rotateAngleX = headRotateAngleX;
        this.extHead[1].rotateAngleY = headRotateAngleY;
        this.extHead[1].rotateAngleX = headRotateAngleX;
    }

    @Override
    protected void adjustBody(float rotateAngleX, float rotationPointY, float rotationPointZ) {
        this.bipedBody.rotateAngleX = rotateAngleX;
        this.bipedBody.rotationPointY = rotationPointY;
        this.bipedBody.rotationPointZ = rotationPointZ;
        this.Bodypiece.rotateAngleX = rotateAngleX;
        this.Bodypiece.rotationPointY = rotationPointY;
        this.Bodypiece.rotationPointZ = rotationPointZ;
        this.extBody.rotateAngleX = rotateAngleX;
        this.extBody.rotationPointY = rotationPointY;
        this.extBody.rotationPointZ = rotationPointZ;
    }

    @Override
    protected void renderHead(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.bipedHead.render(this.scale);
        this.extHead[0].render(this.scale);
        this.extHead[1].render(this.scale);
        this.bipedHeadwear.render(this.scale);
    }

    @Override
    protected void renderNeck() {
    }

    @Override
    protected void renderBody(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.bipedBody.render(this.scale);
        this.Bodypiece.render(this.scale);
        this.extBody.render(this.scale);
    }

    @Override
    protected void renderLegs() {
        if (!isSneak) {
            boolean isLegs = this.extBody.showModel;
            this.extBody.showModel = true;
            this.extBody.postRender(this.scale);
            this.extBody.showModel = isLegs;
        }
        this.bipedLeftArm.render(this.scale);
        this.bipedRightArm.render(this.scale);
        this.bipedLeftLeg.render(this.scale);
        this.bipedRightLeg.render(this.scale);
        this.extLegs[0].render(this.scale);
        this.extLegs[1].render(this.scale);
    }

    @Override
    protected void initTextures() {
        this.extHead = new ModelRenderer[2];
        this.extLegs = new ModelRenderer[2];
        this.initHeadTextures();
        this.initBodyTextures();
        this.initLegTextures();
    }

    @Override
    protected void initHeadTextures() {
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.extHead[0] = new ModelRenderer(this, 0, 0);
        this.extHead[1] = new ModelRenderer(this, 0, 4);
    }

    @Override
    protected void initBodyTextures() {
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.Bodypiece = new ModelRenderer(this, 0, 0);
        this.extBody = new ModelRenderer(this, 16, 8);
    }

    @Override
    protected void initLegTextures() {
        this.bipedRightArm = new ModelRenderer(this, 0, 16);
        this.bipedLeftArm = new ModelRenderer(this, 0, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.steveRightArm = new ModelRenderer(this, 0, 16);
        this.unicornArmRight = new ModelRenderer(this, 0, 16);
        this.unicornArmLeft = new ModelRenderer(this, 0, 16);
        this.extLegs[0] = new ModelRenderer(this, 48, 8);
        this.extLegs[1] = new ModelRenderer(this, 48, 8);
        this.extLegs[1].mirror = true;
    }

    @Override
    protected void initPositions(float yOffset, float stretch) {
        this.initHeadPositions(yOffset, stretch);
        this.initBodyPositions(yOffset, stretch);
        this.initLegPositions(yOffset, stretch);
    }

    @Override
    protected void initHeadPositions(float yOffset, float stretch) {
        this.bipedHead.addBox(-4.0F + HEAD_CENTRE_X, -4.0F + HEAD_CENTRE_Y, -4.0F + HEAD_CENTRE_Z, 8, 8, 8, stretch * 1.1F);
        this.bipedHead.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.extHead[0].addBox(-4.0F + HEAD_CENTRE_X, -6.0F + HEAD_CENTRE_Y, 1.0F + HEAD_CENTRE_Z, 2, 2, 2, stretch * 0.5F);
        this.extHead[0].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.extHead[1].addBox(2.0F + HEAD_CENTRE_X, -6.0F + HEAD_CENTRE_Y, 1.0F + HEAD_CENTRE_Z, 2, 2, 2, stretch * 0.5F);
        this.extHead[1].setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.bipedHeadwear.addBox(-4.0F + HEAD_CENTRE_X, -4.0F + HEAD_CENTRE_Y, -4.0F + HEAD_CENTRE_Z, 8, 8, 8, stretch * 1.1F + 0.5F);
        this.bipedHeadwear.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
    }

    @Override
    protected void initBodyPositions(float yOffset, float stretch) {
        this.bipedBody.addBox(-4.0F, 4.0F, -2.0F, 8, 8, 4, stretch);
        this.bipedBody.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.Bodypiece.addBox(-4.0F, 4.0F, 6.0F, 8, 8, 8, stretch);
        this.Bodypiece.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
        this.extBody.addBox(-4.0F, 4.0F, -2.0F, 8, 8, 16, stretch);
        this.extBody.setRotationPoint(HEAD_RP_X, HEAD_RP_Y + yOffset, HEAD_RP_Z);
    }

    @Override
    protected void initLegPositions(float yOffset, float stretch) {
        super.initLegPositions(yOffset, stretch);
        this.extLegs[0].addBox(-2.0F + THIRDP_ARM_CENTRE_X, -6.0F + THIRDP_ARM_CENTRE_Y, -2.0F + THIRDP_ARM_CENTRE_Z, 4, 12, 4, stretch);
        this.extLegs[0].setRotationPoint(-3.0F, 0.0F + yOffset, 0.0F);
        this.extLegs[1].addBox(-2.0F + THIRDP_ARM_CENTRE_X, -6.0F + THIRDP_ARM_CENTRE_Y, -2.0F + THIRDP_ARM_CENTRE_Z, 4, 12, 4, stretch);
        this.extLegs[1].setRotationPoint(3.0F, 0.0F + yOffset, 0.0F);
    }

    protected void syncLegs() {
        this.extLegs[0].rotateAngleX = this.bipedRightLeg.rotateAngleX;
        this.extLegs[0].rotateAngleY = this.bipedRightLeg.rotateAngleY;
        this.extLegs[0].rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
        this.extLegs[0].rotationPointX = this.bipedRightLeg.rotationPointX;
        this.extLegs[0].rotationPointY = this.bipedRightLeg.rotationPointY;
        this.extLegs[0].rotationPointZ = this.bipedRightLeg.rotationPointZ;
        this.extLegs[1].rotateAngleX = this.bipedLeftLeg.rotateAngleX;
        this.extLegs[1].rotateAngleY = this.bipedLeftLeg.rotateAngleY;
        this.extLegs[1].rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
        this.extLegs[1].rotationPointX = this.bipedLeftLeg.rotationPointX;
        this.extLegs[1].rotationPointY = this.bipedLeftLeg.rotationPointY;
        this.extLegs[1].rotationPointZ = this.bipedLeftLeg.rotationPointZ;
    }

    @Override
    protected void rotateLegs(float move, float swing, float tick, Entity entity) {
        super.rotateLegs(move, swing, tick, entity);
        this.syncLegs();
    }

    @Override
    protected void adjustLegs() {
        super.adjustLegs();
        this.syncLegs();
    }

    @Override
    protected void sneakLegs() {
        super.sneakLegs();
        this.syncLegs();
    }

    @Override
    protected void ponySleep() {
        super.ponySleep();
        this.syncLegs();
    }

    public void setVisible(boolean invisible) {
        super.setVisible(invisible);
        this.Bodypiece.showModel = invisible;
        extBody.showModel = invisible;
        for (ModelRenderer m : extHead) {
            m.showModel = invisible;
        }
        for (ModelRenderer m : extLegs) {
            m.showModel = invisible;
        }
    }
}
