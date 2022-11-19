package thread.safe.beehives.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlockEntity.class)
public class BeehiveBlockEntityFixMixin {

    @Redirect(
            method = "writeBees",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/CompoundTag;remove(Ljava/lang/String;)V"
            )
    )
    //Empty handler to cancel the modification of the actual field
    //The removal is handled separately when the data actually needs to be read
    private void threadSafeBees$cancelRemove(CompoundTag tag, String key) {
    }

    @ModifyArg(
            method = "writeBees",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/CompoundTag;put(Ljava/lang/String;Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;"
            ),
            index = 1
    )
    //Return a (modified) copy of the tag to avoid a ConcurrentModificationException while copying the entityData of the bees
    private Tag threadSafeBees$getEntityDataCopy(Tag entityDataReference) {
        CompoundTag entityData = (CompoundTag) entityDataReference.copy();
        //Remove the UUID from the copy of the tag
        entityData.remove("UUID");
        return entityData;
    }
}
