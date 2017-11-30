package com.gildedrose;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class GildedRoseTest {

    @Test
    public void default_item_with_0_quality() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void default_item_with_strictly_positive_quality() throws Exception {
        Item[] items = new Item[]{new Item("foo", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void sulfuras_with_strictly_positive_quality() throws Exception {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void brie_with_quality_under_50() throws Exception {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void backstagepass_with_strictly_positive_quality() throws Exception {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @Parameters({
            "Backstage passes to a TAFKAL80ETC concert|1|49|0|50",
            "Backstage passes to a TAFKAL80ETC concert|11|1|10|2",
            "Backstage passes to a TAFKAL80ETC concert|11|49|10|50",
            "Backstage passes to a TAFKAL80ETC concert|6|1|5|3",
            "Backstage passes to a TAFKAL80ETC concert|6|49|5|50",
            "Aged Brie|6|50|5|50",
    })
    public void name(String name, int sellIn, int quality, int expectedSellin, int expectedQuality) throws Exception {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedSellin, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void name1() throws Exception {
        List<String> names = asList("Toto", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros");
        for (int quality = 0; quality < 51; quality++) {
            for (int sellin = -20; sellin < 20; sellin++) {
                for (String name : names) {
                    Item[] items = new Item[]{new Item(name, sellin, quality)};
                    Item[] items2 = new Item[]{new Item(name, sellin, quality)};
                    GildedRose app = new GildedRose(items);
                    GildedRose2 app2 = new GildedRose2(items2);

                    app.updateQuality();
                    app2.updateQuality();

                    assertThat(app.items[0].sellIn).as("sellin of "+app.items[0].name).isEqualTo(app2.items[0].sellIn);
                    assertThat(app.items[0].quality).as("quality of "+app.items[0].name).isEqualTo(app2.items[0].quality);
                }
            }
        }
    }
}
