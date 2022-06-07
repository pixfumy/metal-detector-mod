package me.pixfumy.metaldetectormod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MetalDetectorMod implements ClientModInitializer {
    public static SoundEvent METAL_DETECTOR_OFF = new SoundEvent(new Identifier("metaldetectormod:metaldetectoroff"));
    public static SoundEvent METAL_DETECTOR_1 = new SoundEvent(new Identifier("metaldetectormod:metaldetector1"));
    public static SoundEvent METAL_DETECTOR_2 = new SoundEvent(new Identifier("metaldetectormod:metaldetector2"));
    public static SoundEvent METAL_DETECTOR_3 = new SoundEvent(new Identifier("metaldetectormod:metaldetector3"));
    @Override
    public void onInitializeClient() {
        Registry.register(Registry.SOUND_EVENT, "metaldetectormod:metaldetectoroff", METAL_DETECTOR_OFF);
        Registry.register(Registry.SOUND_EVENT, "metaldetectormod:metaldetector1", METAL_DETECTOR_1);
        Registry.register(Registry.SOUND_EVENT, "metaldetectormod:metaldetector2", METAL_DETECTOR_2);
        Registry.register(Registry.SOUND_EVENT, "metaldetectormod:metaldetector3", METAL_DETECTOR_3);
    }
}
