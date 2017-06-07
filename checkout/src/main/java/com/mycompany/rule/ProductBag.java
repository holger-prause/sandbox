package com.mycompany.rule;

import java.util.HashMap;
import java.util.Map;

import com.mycompany.Sku;

/**
 * Contains the current skus which will be processed by the {@link IPriceRule} and contains their calculation results.
 * Each rule is expected to call {@link ProductBag#addResult(Sku, int, int)} when it is finished
 * with its calculation.
 */
public class ProductBag {

	private final Map<Sku, Integer> currentItems;
	private int total;

	public ProductBag(Map<Sku, Integer> registry) {
		currentItems = new HashMap<>(registry);
		total = 0;
	}

	/**
	 * Called when the {@link IPriceRule} has finished calculation, collects the result and
	 * updates the amount of items which are left for the other rules to process.
	 * 
	 * @param sku - the sku of the product
	 * @param amountProcessed - the amount of skus processed
	 * @param p_total - the calculated amount of money
	 */
	public void addResult(Sku sku, int amountProcessed, int p_total) {
		if (!currentItems.containsKey(sku)) {
			throw new RuntimeException(String.format("Can not add result %s for items which are not in the bag.", sku));
		}

		if(amountProcessed == 0) {
			throw new RuntimeException("Can not process zero amounts of products.");
		}
		
		int newAmountInBag = currentItems.get(sku) - amountProcessed;
		if (newAmountInBag < 0) {
			throw new RuntimeException("Cannot process more item than there are in the bag.");
		}

		currentItems.put(sku, newAmountInBag);
		total += p_total;
	}
	
	public boolean hasAnyItemsLeft() {
		return currentItems.values().stream().mapToInt(e -> e).sum() > 0;
	}
	
	public boolean hasItems(Sku sku) {
		return currentItems.containsKey(sku) && currentItems.get(sku) > 0;
	}

	public int getItems(Sku sku) {
		if (currentItems.containsKey(sku)) {
			return currentItems.get(sku);
		}

		return 0;
	}

	public int getTotal() {
		return total;
	}
}
