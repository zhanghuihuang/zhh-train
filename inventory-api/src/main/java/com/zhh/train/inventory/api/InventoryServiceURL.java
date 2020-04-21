package com.zhh.train.inventory.api;

public class InventoryServiceURL {
    private static final String INVENTORY_PREFIX = "http://inventory-server/inventory";
    private static final String INVENTORY_PATH = "/{product}/{quantity}";
    private static final String INVENTORY_TIMEOUT_PATH = "/timeout/{product}/{quantity}";
    private static final String INVENTORY_ERROR_PATH = "/error/{product}/{quantity}";
    private static final String PRODUCT_KEY = "{product}";
    private static final String QUANTITY_KEY = "{quantity}";

    public static String getInventoryPrefix() {
        return INVENTORY_PREFIX;
    }

    public static String getInventoryPath(String product, int quantity) {
        return INVENTORY_PATH.replace(PRODUCT_KEY, product).replace(QUANTITY_KEY, String.valueOf(quantity));
    }

    public static String getInventoryTimeoutPath(String product, int quantity) {
        return INVENTORY_TIMEOUT_PATH.replace(PRODUCT_KEY, product).replace(QUANTITY_KEY, String.valueOf(quantity));
    }

    public static String getInventoryErrorPath(String product, int quantity) {
        return INVENTORY_ERROR_PATH.replace(PRODUCT_KEY, product).replace(QUANTITY_KEY, String.valueOf(quantity));
    }
}
