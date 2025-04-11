package net.mrbeelo.bsmpc.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.mrbeelo.bsmpc.BsmpC;

public class ModEntityModelLayers {
    public static final EntityModelLayer SNEK =
            new EntityModelLayer(BsmpC.id("snek"), "main");

    public static final EntityModelLayer BLOB =
            new EntityModelLayer(BsmpC.id("blob"), "main");

    public static final EntityModelLayer PYRO =
            new EntityModelLayer(BsmpC.id("pyro"), "main");

    public static final EntityModelLayer PROTECTOR =
            new EntityModelLayer(BsmpC.id("protector"), "main");
}
