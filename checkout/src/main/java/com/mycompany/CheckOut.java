package com.mycompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.rule.IPriceRule;
import com.mycompany.rule.ProductBag;

/*
 * Class for calculating price for stock units.
 * 
 */
public class CheckOut {
	private int total = 0;
	private List<IPriceRule> rules = new ArrayList<>();
	private Map<Sku, Integer> registry = new HashMap<>();

	/**
	 * @param p_rules - the price rules to apply on the stock units. Rules are applied in the given order.
	 * This means the first rule is applied first and so on. Empty or null rules are not accepted
	 */
	public CheckOut(IPriceRule... p_rules) {
		this.rules = Arrays.asList(p_rules);		
		if(rules.isEmpty()) {
			throw new IllegalArgumentException("At least on rule must be given");
		}
		
		if( rules.stream().anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("Null rules are not valid");
		}
	}

	private void register(String externalSku) {
		Sku sku = Sku.valueOf(externalSku);
		Integer existingAmount = registry.get(sku);
		if (existingAmount == null) {
			existingAmount = 0;
		}
		existingAmount++;
		registry.put(sku, existingAmount);	
	}
	
	public void scan(String externalSku) {
		total = 0;
		register(externalSku);

		ProductBag productBag = new ProductBag(registry);
		for (IPriceRule priceRule : rules) {
			priceRule.applyTo(productBag);
		}

		if (productBag.hasAnyItemsLeft()) {
			throw new RuntimeException("There are unprocessed products in the bag, check your rules.");
		}

		total = productBag.getTotal();
	}

	public int total() {
		return total;
	}
}
