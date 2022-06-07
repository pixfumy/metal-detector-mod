package me.pixfumy.metaldetectormod.mixin;

import me.pixfumy.metaldetectormod.MetalDetectorMod;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Shadow @Final private MinecraftClient client;
    private boolean lastProfilerEnabled;

    @Inject(method = "onKey", at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;debugProfilerEnabled:Z", opcode = Opcodes.PUTFIELD))
    private void beforeOpenF3(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        lastProfilerEnabled = this.client.options.debugProfilerEnabled;
    }

    @Inject(method = "onKey", at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;debugProfilerEnabled:Z", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER))
    private void afterOpenF3(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        if (!this.client.options.debugProfilerEnabled && lastProfilerEnabled) {
            this.client.world.playSound(this.client.player, this.client.player.getBlockPos(), MetalDetectorMod.METAL_DETECTOR_OFF, SoundCategory.VOICE, 1, 1);
        }
    }
}
