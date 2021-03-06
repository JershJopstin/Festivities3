package com.zapcloudstudios.festivities3.item;

import java.util.List;

import com.zapcloudstudios.festivities3.Festivities;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMoreCookies extends ItemFoodFestive
{
	public static final String[] names = new String[] { "sugar", "choc", "sprink", "candy" };

	@SideOnly(Side.CLIENT)
	private IIcon[] cookieIcons;

	public ItemMoreCookies(int par2, float par3)
	{
		super(par2, par3, false);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Gets an icon index based on an item's damage value
	 */
	public IIcon getIconFromDamage(int par1)
	{
		int j = MathHelper.clamp_int(par1, 0, 15);
		return this.cookieIcons[j];
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + names[i];
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int j = 0; j < 4; ++j)
		{
			par3List.add(new ItemStack(par1, 1, j));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.cookieIcons = new IIcon[names.length];

		for (int i = 0; i < names.length; ++i)
		{
			this.cookieIcons[i] = par1IconRegister.registerIcon(Festivities.ID + ":cookie" + "_" + names[i]);
		}
	}
}
