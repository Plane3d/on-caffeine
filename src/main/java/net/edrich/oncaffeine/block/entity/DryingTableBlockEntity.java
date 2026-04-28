package net.edrich.oncaffeine.block.entity;

import net.edrich.oncaffeine.item.ModItems;
import net.edrich.oncaffeine.screen.DryingTableScreenHandler;
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

public class DryingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 576;

    public DryingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRYING_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index){
                return switch (index){
                    case 0 -> DryingTableBlockEntity.this.progress;
                    case 1 -> DryingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> DryingTableBlockEntity.this.progress = value;
                    case 1 -> DryingTableBlockEntity.this.maxProgress = value;
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
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("drying_table.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("drying_table.progress");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockentity.oncaffeine.drying_table_entity");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DryingTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient())
        {
            return;
        }
        if (isOutputSlotEmptyOrReceivable())
        {
            if (this.hasRecipe(this.checkRecipe()))
            {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished())
                {
                    this.craftItem();
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
        this.removeStack(INPUT_SLOT, 1);
        ItemStack result = switch (this.checkRecipe()) {
            case "green_tea_recipe" -> new ItemStack(ModItems.GREEN_TEA_LEAVES);
            case "black_tea_recipe" -> new ItemStack(ModItems.BLACK_TEA_LEAVES);
            case "raw_coffee_recipe" -> new ItemStack(ModItems.RAW_COFFEE_BEANS);
            default -> new ItemStack(Items.GRASS);
        };

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


    private boolean hasRecipe(String recipe)
    {
        return (!this.checkRecipe().equals("no_recipe")) && canInsertIntoOutputSlot(recipe) && canInsertAmountIntoOutputSlot(recipe);

    }

    private String checkRecipe()
    {
        boolean teaLeaves = getStack(INPUT_SLOT).getItem() == ModItems.TEA_LEAVES;
        boolean greenTeaLeaves = getStack(INPUT_SLOT).getItem() == ModItems.GREEN_TEA_LEAVES;
        boolean coffeeFruit = getStack(INPUT_SLOT).getItem() == ModItems.COFFEE_FRUIT;

        if(teaLeaves)
        {
            return "green_tea_recipe";
        }
        else if (greenTeaLeaves)
        {
            return "black_tea_recipe";
        }
        else if (coffeeFruit)
        {
            return "raw_coffee_recipe";
        }

        return "no_recipe";
    }

    private boolean canInsertIntoOutputSlot(String recipe)
    {
        Item item = switch (recipe) {
            case "green_tea_recipe" -> ModItems.GREEN_TEA_LEAVES;
            case "black_tea_recipe" -> ModItems.BLACK_TEA_LEAVES;
            case "raw_coffee_recipe" -> ModItems.RAW_COFFEE_BEANS;
            default -> throw new IllegalStateException("Unexpected value: " + recipe);
        };
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(String recipe)
    {
        ItemStack result = switch (recipe) {
            case "green_tea_recipe" -> new ItemStack(ModItems.GREEN_TEA_LEAVES);
            case "black_tea_recipe" -> new ItemStack(ModItems.BLACK_TEA_LEAVES);
            case "raw_coffee_recipe" -> new ItemStack(ModItems.RAW_COFFEE_BEANS);
            default -> throw new IllegalStateException("Unexpected value: " + recipe);
        };
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable()
    {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
