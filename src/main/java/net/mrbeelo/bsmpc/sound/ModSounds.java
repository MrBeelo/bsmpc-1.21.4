package net.mrbeelo.bsmpc.sound;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.mrbeelo.bsmpc.BsmpC;


public class ModSounds {

    //REGISTERING

    public static final SoundEvent RUBY_BLOCK_BREAK = register("ruby_block_break");
    public static final SoundEvent RUBY_BLOCK_STEP = register("ruby_block_step");
    public static final SoundEvent RUBY_BLOCK_PLACE = register("ruby_block_place");
    public static final SoundEvent RUBY_BLOCK_HIT = register("ruby_block_hit");
    public static final SoundEvent RUBY_BLOCK_FALL = register("ruby_block_fall");
    public static final SoundEvent HIGH = register("high");
    public static final SoundEvent PEW = register("pew");
    public static final SoundEvent MAGEMPTY = register("magempty");
    public static final SoundEvent BEEP = register("beep");
    public static final SoundEvent GNOME = register("gnome");
    public static final SoundEvent CORRECT = register("correct");
    public static final SoundEvent INCORRECT = register("incorrect");
    public static final SoundEvent BANG = register("bang");
    public static final SoundEvent DOODOO = register("doodoo");
    public static final SoundEvent MUA = register("mua");
    public static final SoundEvent AAAU = register("aaau");
    public static final SoundEvent OOF = register("oof");
    public static final SoundEvent FISH = register("fish");
    public static final SoundEvent MEEP = register("meep");
    public static final SoundEvent SLAP = register("slap");
    public static final SoundEvent BZZZ = register("bzzz");
    public static final SoundEvent QUACK = register("quack");
    public static final SoundEvent XO = register("xo");
    public static final RegistryKey<JukeboxSong> XO_KEY = of("xo");

    public static final BlockSoundGroup RUBY_BLOCK_SOUNDS = new BlockSoundGroup(1f, 1f,
            ModSounds.RUBY_BLOCK_BREAK, ModSounds.RUBY_BLOCK_STEP, ModSounds.RUBY_BLOCK_PLACE,
            ModSounds.RUBY_BLOCK_HIT, ModSounds.RUBY_BLOCK_FALL);

    //METHODS

    private static RegistryKey<JukeboxSong> of(String name) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, BsmpC.id(name));
    }

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, BsmpC.id(name), SoundEvent.of(BsmpC.id(name)));
    }

    public static void registerModSounds() {
        BsmpC.LOGGER.info("Registering Mod Sounds for " + BsmpC.MOD_ID);
    }
}
