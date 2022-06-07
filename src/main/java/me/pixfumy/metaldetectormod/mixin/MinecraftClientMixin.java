package me.pixfumy.metaldetectormod.mixin;

import me.pixfumy.metaldetectormod.MetalDetectorMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow public ClientWorld world;
    @Shadow public ClientPlayerEntity player;
    private int ticksSinceLastMetalDetectorSound = 0;
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V"))
    private void onRenderPie(CallbackInfo ci) {
        int type = (new Random()).nextInt(3) + 1;
        SoundEvent toPlay = MetalDetectorMod.METAL_DETECTOR_1;
        switch (type) {
            case 1:
                toPlay = MetalDetectorMod.METAL_DETECTOR_1;
                break;
            case 2:
                toPlay = MetalDetectorMod.METAL_DETECTOR_3;
                break;
            case 3:
                toPlay = MetalDetectorMod.METAL_DETECTOR_3;
                break;
        }
        if (ticksSinceLastMetalDetectorSound >= 60) {
            this.world.playSound(this.player, this.player.getBlockPos(), toPlay, SoundCategory.VOICE, 1, 1);
            ticksSinceLastMetalDetectorSound = 0;
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void incrementTicks(CallbackInfo ci) {
        ticksSinceLastMetalDetectorSound++;
    }
}
