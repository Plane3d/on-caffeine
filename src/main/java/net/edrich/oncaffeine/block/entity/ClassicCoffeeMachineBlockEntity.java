package net.edrich.oncaffeine.block.entity;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.item.ModItems;
import net.edrich.oncaffeine.screen.ClassicCoffeeScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ClassicCoffeeMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int BEAN_SLOT = 0;
    private static final int WATER_SLOT = 1;
    private static final int MUG_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public ClassicCoffeeMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CLASSIC_COFFEE_MACHINE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ClassicCoffeeMachineBlockEntity.this.progress;
                    case 1 -> ClassicCoffeeMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ClassicCoffeeMachineBlockEntity.this.progress = value;
                    case 1 -> ClassicCoffeeMachineBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt (NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
        nbt.putInt("class_coffee_machine.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("class_coffee_machine.progress");
    }


    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockentity.oncaffeine.classic_coffee_entity");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ClassicCoffeeScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if (world.isClient())
        {
            return;
        }

        if(isOutputSlotEmptyOrReceivable())
        {
            if(this.hasRecipe()){
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()){
                    this.craftItem();
                    this.resetProgress();
                }
                else
                {
                    this.resetProgress();
                }
            }
        }
        else
        {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress()
    {
        this.progress = 0;
    }

    private void craftItem()
    {
        this.removeStack(BEAN_SLOT, 1);
        this.removeStack(WATER_SLOT, 1);
        this.removeStack(MUG_SLOT, 1);
        ItemStack result = new ItemStack(ModBlocks.MUG_COFFEE);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished()
    {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress()
    {
        progress++;
    }

    private boolean hasRecipe()
    {
        ItemStack result = new ItemStack(ModBlocks.MUG_COFFEE);
        boolean hasBeans = getStack(BEAN_SLOT).getItem() == ModItems.COFFEE_BEANS;
        boolean hasWater = getStack(WATER_SLOT).getItem() == Items.POTION.getDefaultStack().getItem();
        boolean hasMug = getStack(MUG_SLOT).getItem() == ModBlocks.MUG_EMPTY.asItem();

        return hasMug && hasBeans && hasWater && canInsertAmountIntoOutputSlot(result) && canInsertAmountIntoOutputSlot(result.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(Item item)
    {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result)
    {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable()
    {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

}
