package com.gildedrose;

import static com.gildedrose.ItemGroup.*;

class GildedRose {
    Item[] items;

    static final int MAX_QUALITY = 50;
    static final int MIN_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateSellInValueForItem(i);

            switch (getItemGroup(items[i].name)) {
                case BACKSTAGE_PASSES:
                    updateBackstagePassItem(i);
                    break;
                case INCREASES_IN_QUALITY_WITH_AGE:
                    updateItemThatIncreasesQualityWithAge(i);
                    break;
                case LEGENDARY:
                    updateLegendaryItem(i);
                    break;
                case CONJURED:
                    updateConjuredItem(i);
                    break;
                case REGULAR:
                    updateRegularItem(i);
                    break;
            }
        }
    }

    private void updateItemThatIncreasesQualityWithAge(int i) {
        int change = 1;
        if (items[i].sellIn >= 0) {
            changeQualityOfItemBy(i, change);
        } else {
            changeQualityOfItemBy(i, 2 * change);
        }
    }

    private void updateBackstagePassItem(int i) {
        if (items[i].sellIn < 0) {
            items[i].quality = 0;
        } else if (items[i].sellIn < 5) {
            changeQualityOfItemBy(i, 3);
        } else if (items[i].sellIn < 10) {
            changeQualityOfItemBy(i, 2);
        } else {
            changeQualityOfItemBy(i, 1);
        }
    }

    private void updateLegendaryItem(int i) {
        /**
         * Do nothing, since it is legendary item.
         * The innkeeper thinks that in the future he could start
         * increasing the quality 10-fold each day, and he would
         * need to update this method, but as for now,
         * them legendary items just keep their props the same
         */
    }

    private void updateRegularItem(int i) {
        int change = -1;
        if (items[i].sellIn >= 0) {
            changeQualityOfItemBy(i, change);
        } else {
            changeQualityOfItemBy(i, 2 * change);
        }
    }

    // "Conjured" items degrade in Quality twice as fast as normal items
    private void updateConjuredItem(int i) {
        updateRegularItem(i);
        updateRegularItem(i);
    }

    private void changeQualityOfItemBy(int i, int change) {
        items[i].quality += change;

        if (items[i].quality > MAX_QUALITY) {
            items[i].quality = MAX_QUALITY;
        }
        if (items[i].quality < MIN_QUALITY) {
            items[i].quality = MIN_QUALITY;
        }
    }

    private void updateSellInValueForItem(int i) {
        if (getItemGroup(items[i].name) != LEGENDARY) {
            --items[i].sellIn;
        }
    }
}
