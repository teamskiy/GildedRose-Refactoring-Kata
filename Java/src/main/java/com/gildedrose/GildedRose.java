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
            } else if (getItemGroup(items[i].name) == LEGENDARY) {
                updateLegendaryItem(i);
            } else {
                updateRegularItem(i);
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

    private void updateLegendaryItem(int i) {
        /**
         * Do nothing, since it is legendary item.
         * The innkeeper thinks that in the future he could start
         * increasing the quality 10-fold each day, and he would
         * need to update this method, but as for now
         * them legendary items just keep their props the same
         */
    }

    private void updateRegularItem(int i) {
        if (items[i].quality > 0) {
            items[i].quality = items[i].quality - 1;
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            if (items[i].quality > 0) {
                items[i].quality = items[i].quality - 1;
            }
        }
    }
}
