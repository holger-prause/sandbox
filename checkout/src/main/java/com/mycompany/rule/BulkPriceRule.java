package com.mycompany.rule;

import java.util.HashMap;
import java.util.Map;

import com.mycompany.Sku;

public class BulkPriceRule implements IPriceRule {
	private Map<Sku, BulkPrice> bulkPrices = new HashMap<>();

	public BulkPriceRule() {
		bulkPrices.put(Sku.A, new BulkPrice(3, 100));
		bulkPrices.put(Sku.B, new BulkPrice(2, 80));
		// TODO: some validation logic to prevent bulk amount of 0 (price of 0 is ok)
	}

	@Override
	public void applyTo(ProductBag productBag) {
		for (Sku sku : bulkPrices.keySet()) {
			BulkPrice bulkPrice = bulkPrices.get(sku);
			// only pick items suitable for the given bulk size
			if (productBag.getItems(sku) >= bulkPrice.amount) {
				int amount = productBag.getItems(sku);
				int bulkCount = (int) (amount / bulkPrice.amount);
				int total = bulkCount * bulkPrice.price;
				int amountBilledProducts = bulkCount * bulkPrice.amount;
				productBag.addResult(sku, amountBilledProducts, total);
			}
		}
	}

	private class BulkPrice {
		private final int amount;
		private final int price;

		public BulkPrice(int amount, int price) {
			this.amount = amount;
			this.price = price;
		}
	}
}
