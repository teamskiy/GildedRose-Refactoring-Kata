package com.gildedrose;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public enum ItemGroup {
    BACKSTAGE_PASSES,
    CONJURED,
    INCREASES_IN_QUALITY_WITH_AGE,
    LEGENDARY,
    REGULAR;

    private static final Set<String> LEGENDARY_ITEMS =
        ImmutableSet.of("Sulfuras, Hand of Ragnaros");

    private static final Set<String> ITEMS_THAT_INCREASE_IN_QUALITY_WITH_AGE =
        ImmutableSet.of("Aged Brie");

    public static ItemGroup getItemGroup(String itemName) {
        if (isBackstagePassItem(itemName)) {
            return BACKSTAGE_PASSES;
        } else if (isConjuredItem(itemName)) {
            return CONJURED;
        } else if (isIncreasesInQualityWithAgeItem(itemName)) {
            return INCREASES_IN_QUALITY_WITH_AGE;
        } else if (isLegendaryItem(itemName)) {
            return LEGENDARY;
        }
        return REGULAR;
    }

    private static boolean isBackstagePassItem(String itemName) {
        return itemName.startsWith("Backstage passes");
    }

    private static boolean isConjuredItem(String itemName) {
        return itemName.startsWith("Conjured");
    }

    private static boolean isIncreasesInQualityWithAgeItem(String itemName) {
        return ITEMS_THAT_INCREASE_IN_QUALITY_WITH_AGE.contains(itemName);
    }

    private static boolean isLegendaryItem(String itemName) {
        return LEGENDARY_ITEMS.contains(itemName);
    }
}
