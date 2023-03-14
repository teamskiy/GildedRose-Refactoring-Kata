package com.gildedrose;

import static com.gildedrose.ItemGroup.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (getItemGroup(items[i].name) == INCREASES_IN_QUALITY_WITH_AGE) {
                updateItemThatIncreasesQualityWithAge(i);
            } else if (getItemGroup(items[i].name) == BACKSTAGE_PASSES) {
              updateBackstagePassItem(i);
            } else {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }

                if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                    items[i].sellIn = items[i].sellIn - 1;
                }

                if (items[i].sellIn < 0) {
                    if (items[i].quality > 0) {
                        if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                }
            }
        }
    }

    private void updateItemThatIncreasesQualityWithAge(int i) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
            }
        }
    }

    private void updateBackstagePassItem(int i) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;

            if (items[i].sellIn < 11) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }

            if (items[i].sellIn < 6) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            items[i].quality = 0;
        }
    }
}
