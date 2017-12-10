package snowvillage.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class VillageMaterialEventHandler
{
    @SubscribeEvent
    public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
    {
        if (SnowVillage.coldBiomes.contains(event.getBiome()))
        {
            final IBlockState original = event.getOriginal();
            Block block = original.getBlock();
            IBlockState replacement = null;

            if (block == Blocks.PLANKS)
            {
                replacement = original
                    .withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
            }
            if (block == Blocks.LOG)
            {
                replacement = original
                    .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE)
                    .withProperty(BlockLog.LOG_AXIS, original.getValue(BlockLog.LOG_AXIS));
            }
            if (block == Blocks.OAK_STAIRS)
            {
                replacement = Blocks.SPRUCE_STAIRS.getDefaultState()
                    .withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING))
                    .withProperty(BlockStairs.HALF, original.getValue(BlockStairs.HALF))
                    .withProperty(BlockStairs.SHAPE, original.getValue(BlockStairs.SHAPE));
            }
            if (block == Blocks.OAK_FENCE)
            {
                replacement = Blocks.SPRUCE_FENCE.getDefaultState();
            }
            
            if (replacement != null)
            {
                event.setReplacement(replacement);
                event.setResult(Result.DENY);   
            }
        }
    }
}
