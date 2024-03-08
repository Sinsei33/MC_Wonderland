package net.sinsei.wonderlandmod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;

public final class DevUtil
{
    private DevUtil() { }

    public static void outputDevMessage(String message)
    {
        Player play = Minecraft.getInstance().player;

        play.sendSystemMessage(Component.literal(message));
    }
}
