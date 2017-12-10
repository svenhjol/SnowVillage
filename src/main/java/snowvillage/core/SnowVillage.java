package snowvillage.core;

import java.util.ArrayList;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SnowVillage.MOD_ID, version = SnowVillage.MOD_VERSION , name = SnowVillage.MOD_NAME)
public class SnowVillage
{
    public static final String MOD_NAME = "Snow Village";
    public static final String MOD_ID = "snowvillage";
    public static final String MOD_VERSION = "@MOD_VERSION@";

    public static final ArrayList<Biome> coldBiomes = new ArrayList<Biome>();
    
    @Instance(MOD_ID)
    public static SnowVillage instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        coldBiomes.add(Biomes.COLD_TAIGA);
        coldBiomes.add(Biomes.ICE_PLAINS);
        coldBiomes.add(Biomes.MUTATED_ICE_FLATS);

        ArrayList<Biome> vanillaBiomes = new ArrayList<Biome>(MapGenVillage.VILLAGE_SPAWN_BIOMES);
        vanillaBiomes.addAll(coldBiomes);
        MapGenVillage.VILLAGE_SPAWN_BIOMES = vanillaBiomes;
        MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
    }
}
