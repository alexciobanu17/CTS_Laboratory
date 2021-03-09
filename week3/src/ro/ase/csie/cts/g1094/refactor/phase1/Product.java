package ro.ase.csie.cts.g1094.refactor.phase1;

public class Product {
	
	public static final int MAX_AGE_ACCOUNT=10;
	public static final float MAX_FIDELITY_DISCOUNT = 0.15f;
	
	public float computePriceWithDiscount(int productType, float price, int accountAge)
	  {
	    float finalPrice = 0;
	    float fidelityDiscount = (accountAge > MAX_AGE_ACCOUNT) ? MAX_FIDELITY_DISCOUNT : (float)accountAge/100; 
	    if (productType == 1)
	    {
	      finalPrice = price;
	    }
	    else if (productType == 2)
	    {
	      finalPrice = (price - (0.1f * price)) - fidelityDiscount * (price - (0.1f * price));
	    }
	    else if (productType == 3)
	    {
	      finalPrice = (price - (0.25f * price)) - fidelityDiscount * (price - (0.25f * price));
	    }
	    else if (productType == 4)
	    {
	      finalPrice = (price - (0.35f * price)) - fidelityDiscount * (price - (0.35f * price));
	    }
	    return finalPrice;
	  }
}