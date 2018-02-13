package com.gildedrose;

/**
 * Created by Domo-kun on 30/11/2017.
 */
public class GildedItem {

    private final String name;
    private final int sellIn;
    private final int quality;

    public GildedItem(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public final Item toItem() {
        return new Item(name, sellIn, quality);
    }

    public GildedItem updateQuality(){
        return new GildedItem(name, sellIn, quality);
    }

}
