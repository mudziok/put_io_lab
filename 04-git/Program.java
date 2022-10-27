//wow ale fajna klasa

public class ModBlocks {
    public static final RegistryObject<Block> GOLD_ORE = register("gold_ore", () ->
        new Block(new AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DIRT)));

    static void rthrthrth() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.regihrthrthster(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T>  ret = registerNoItem(name, block);
        Registration.ITEMS.regihrtrthrthster(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    }
}