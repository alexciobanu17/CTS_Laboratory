package ro.ase.csie.cts.g1094.refactor.phase1;

import ro.ase.csie.cts.g1094.refactor.exceptions.InvalidAgeException;
import ro.ase.csie.cts.g1094.refactor.exceptions.InvalidPriceException;

public class Product {
	
	public static final int MAX_AGE_ACCOUNT=10;
	public static final float MAX_FIDELITY_DISCOUNT = 0.15f;
	
	public static float getDiscountValue(float price, float discount) {
		return discount*price;
	}
	private static float getPriceWithDiscountAndFidelity(float price, float discountValue,float fidelityDiscount){
		
    	return (price - discountValue)* (1- fidelityDiscount);
	}
	
	private static float getFidelityDiscount(int accountAge)
	{
	    return (accountAge > MAX_AGE_ACCOUNT) ? MAX_FIDELITY_DISCOUNT : (float)accountAge/100; 

	}
	
	private static float getFinalPrice(
			float price, float fidelityDiscount, ProductType type)
	{
		float discountValue=getDiscountValue(price, type.getDiscount());
		float finalPrice = getPriceWithDiscountAndFidelity(price,discountValue,fidelityDiscount);
		return finalPrice;
	}
	
	public float computePriceWithDiscount(ProductType productType, float price, int accountAge)
			throws InvalidPriceException, InvalidAgeException
	  {
		if(price<=0) {
			throw new InvalidPriceException();
		}
		if(accountAge<0) {
			throw new InvalidAgeException();
		}
		
		float finalPrice=0;
	    float fidelityDiscount = getFidelityDiscount(accountAge);
	    
	    float discountValue=0;

	    switch(productType) {
	    case NEW:
	    	finalPrice=getFinalPrice(price,fidelityDiscount, ProductType.NEW);
	    	break;
	    case DISCOUNTED:	    
	    	finalPrice=getFinalPrice(price,fidelityDiscount, ProductType.DISCOUNTED);
	    	break;
	    case LIMITED_STOCK:
	    	finalPrice=getFinalPrice(price,fidelityDiscount, ProductType.LIMITED_STOCK);
		      break;
	    case LEGACY:
	    	finalPrice=getFinalPrice(price,fidelityDiscount, ProductType.LEGACY);
		      break;
		      default:
		    	  throw new UnsupportedOperationException("The enum type is not covered.");
	    }

	    if (productType == ProductType.NEW)
	    {
	      finalPrice = price;
	    }
	    else if (productType == ProductType.DISCOUNTED)
	    {
	      finalPrice = (price - (ProductType.DISCOUNTED.getDiscount() * price)) - fidelityDiscount * (price - (ProductType.DISCOUNTED.getDiscount() * price));
	    }
	    else if (productType == ProductType.LIMITED_STOCK)
	    {
	      finalPrice = (price - (ProductType.LIMITED_STOCK.getDiscount() * price)) - fidelityDiscount * (price - (ProductType.LIMITED_STOCK.getDiscount() * price));
	    }
	    else if (productType == ProductType.LEGACY)
	    {
	      finalPrice = (price - (ProductType.LEGACY.getDiscount() * price)) - fidelityDiscount * (price - (ProductType.LEGACY.getDiscount() * price));
	    }
	    return finalPrice;
	  }
}