package thread.safe.beehives.mixin;

import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BeehiveBlockEntity.class)
public class BeehiveBlockEntityFixMixin {

    @ModifyArg(
            method = "writeBees",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/CompoundTag;put(Ljava/lang/String;Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;"
            ),
            index = 1
    )
    //Return a copy of the entityData instead of a direct reference to avoid a ConcurrentModificationException while saving the entityData of the bees
    private Tag threadSafeBees$getEntityDataCopy(Tag entityDataReference) {
        return entityDataReference.copy();
    }
}
