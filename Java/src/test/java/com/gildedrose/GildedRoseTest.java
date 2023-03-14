package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void givenBackstagePassItemWithMoreThan10DaysSellIn_whenUpdateQualityOnce_thenQualityIncreased() {
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        int quality = 0;
        int sellIn = 11;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality + 1, app.items[0].quality);
    }

    @Test
    void givenBackstagePassItemWithMoreThan5DaysButLessThan10DaysSellIn_whenUpdateQualityOnce_thenQualityIncreasedByTwo() {
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        int quality = 0;
        int sellIn = 7;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality + 2, app.items[0].quality);
    }

    @Test
    void givenBackstagePassItemWithMoreThan0DaysButLessThan5DaysSellIn_whenUpdateQualityOnce_thenQualityIncreasedByThree() {
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        int quality = 0;
        int sellIn = 1;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality + 3, app.items[0].quality);
    }

    @Test
    void givenBackstagePassItemThatIsPassingItsSellDate_whenUpdateQualityFor100Days_thenQualityIsAlways0() {
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        int quality = 1000;
        int sellIn = 0;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 100; ++day) {
            app.updateQuality();
            assertEquals(0, app.items[0].quality);
        }
        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 100, app.items[0].sellIn);
    }

    @Test
    void givenItemThatIncreasesTheQualityWithAge_whenUpdateQualityOnce_thenQualityIncreased() {
        String itemName = "Aged Brie";
        int quality = 0;
        int sellIn = 5;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality + 1, app.items[0].quality);
    }

    @Test
    void givenItemThatIncreasesTheQualityWithAgeThatIsPassingItsSellDate_whenUpdateQualityOnce_thenQualityIncreasedByTwo() {
        String itemName = "Aged Brie";
        int quality = 0;
        int sellIn = 0;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality + 2, app.items[0].quality);
    }

    @Test
    void givenItemThatIncreasesTheQualityWithAge_whenUpdateQualityFor100Days_thenQualityIsCappedAt50() {
        String itemName = "Aged Brie";
        int quality = 0;
        int sellIn = 5;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 100; ++day) {
            app.updateQuality();
        }
        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 100, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void givenLegendaryItem_whenUpdateQualityFor100Days_thenItemInfoIsUnchanged() {
        String itemName = "Sulfuras, Hand of Ragnaros";
        int quality = 99;
        int sellIn = 5;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 100; ++day) {
            app.updateQuality();

            assertEquals(itemName, app.items[0].name);
            assertEquals(sellIn, app.items[0].sellIn);
            assertEquals(quality, app.items[0].quality);
        }
    }

    @Test
    void givenRegularItemBeforeItsSellDate_whenUpdateQualityOnce_thenQualityDecreased() {
        String itemName = "just something very regular, like scissors";
        int quality = 5;
        int sellIn = 5;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality - 1, app.items[0].quality);
    }

    @Test
    void givenRegularItemPassingItsSellDate_whenUpdateQualityOnce_thenQualityDecreasedByTwo() {
        String itemName = "just something very regular, like ball";
        int quality = 5;
        int sellIn = 0;
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(itemName, app.items[0].name);
        assertEquals(sellIn - 1, app.items[0].sellIn);
        assertEquals(quality - 2, app.items[0].quality);
    }
}
