package com.mycompany.rule;

import java.util.HashMap;
import java.util.Map;

import com.mycompany.Sku;

public class UnitPriceRule implements IPriceRule {
	Map<Sku, Integer> unitPrices = new HashMap<>();

	public UnitPriceRule() {
		unitPrices.put(Sku.A, 40);
		unitPrices.put(Sku.B, 50);
		unitPrices.put(Sku.C, 25);
		unitPrices.put(Sku.D, 20);
	}

	@Override
	public void applyTo(ProductBag productBag) {
		for (Sku sku : unitPrices.keySet()) {
			if (productBag.hasItems(sku)) {
				int amount = productBag.getItems(sku);
				int total = amount * unitPrices.get(sku);
				productBag.addResult(sku, amount, total);
			}
		}
	}
}
