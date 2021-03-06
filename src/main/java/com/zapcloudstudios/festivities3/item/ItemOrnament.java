package com.zapcloudstudios.festivities3.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.zapcloudstudios.festivities3.Festivities;
import com.zapcloudstudios.utils.FestiveUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOrnament extends ItemFestiveBlock
{
	private Block block;
	private boolean clear;

	@SideOnly(Side.CLIENT)
	private IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	private IIcon coloredIcon;
	@SideOnly(Side.CLIENT)
	private IIcon clearIcon;

	public ItemOrnament(Block block, boolean clear)
	{
		super(block);
		this.block = block;
		this.clear = clear;
		if (!clear)
		{
			this.setHasSubtypes(true);
			this.setMaxDamage(0);
		}
		this.setToUseItemTextureMap();
	}

	public boolean isClear()
	{
		return this.clear;
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.clear)
		{
			return super.getUnlocalizedName() + ".clear";
		}
		else
		{
			int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
			return super.getUnlocalizedName() + "." + ItemDye.field_150923_a[i];
		}
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		if (this.clear)
		{
			super.getSubItems(par1, par2CreativeTabs, par3List);
		}
		else
		{
			for (int j = 0; j < 16; ++j)
			{
				par3List.add(new ItemStack(par1, 1, j));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		if (this.clear)
		{
			this.clearIcon = par1IconRegister.registerIcon(Festivities.ID + ":ornament" + "_" + "clear");
		}
		else
		{
			this.baseIcon = par1IconRegister.registerIcon(Festivities.ID + ":ornament" + "_" + "base");
			this.coloredIcon = par1IconRegister.registerIcon(Festivities.ID + ":ornament" + "_" + "color");
		}
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		meta = stack.getItemDamage();

		if (world.setBlock(x, y, z, this.block, meta, 3))
		{
			if (world.getBlock(x, y, z) == this.block)
			{
				this.block.onBlockPlacedBy(world, x, y, z, player, stack);
				this.block.onPostBlockPlaced(world, x, y, z, meta);
			}

			--stack.stackSize;
		}

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return !this.clear;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.clearIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Gets an icon index based on an item's damage value and the given render pass
	 */
	public IIcon getIconFromDamageForRenderPass(int dmg, int pass)
	{
		if (this.clear)
		{
			return this.clearIcon;
		}
		else
		{
			if (pass == 0)
			{
				return this.baseIcon;
			}
			else
			{
				return this.coloredIcon;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass)
	{
		if (this.clear)
		{
			return 0xFFFFFF;
		}
		else
		{
			if (pass == 0)
			{
				return 0xFFFFFF;
			}
			else
			{
				int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
				return FestiveUtils.getDyeColor(i);
			}
		}
	}
}
