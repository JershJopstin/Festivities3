package com.zapcloudstudios.festivities3;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = FestivitiesMod.MODID, version = FestivitiesMod.VERSION, name = "Festivities3")
public class FestivitiesMod
{
	@Mod.Instance("festivities3")
	public static FestivitiesMod instance;
	@SidedProxy(clientSide = "com.zapcloudstudios.festivities3.ClientProxy", serverSide = "com.zapcloudstudios.festivities3.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "festivities3";
	public static final String VERSION = "1.0";

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		
		FestiveBlocks.loadBlocks();
		
		FestiveBlocks.registerBlocks();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderInformation();

		proxy.setupGuiIngameForge();
	}
}
