package net.grosinger.nomads;

/**
 * A random pile of money with a random assigned value
 */
public class MoneyPile implements GameObject {
	private static String name = "MoneyPile";
	private int value;

	/**
	 * Class Constructor
	 */
	public MoneyPile() {
		value = randomValue();
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * How much is this money pile worth?
	 * 
	 * @return <code>int</code> - The value of this pile.
	 */
	public int getValue() {
		return value;
	}

	@Override
	public void setName(String newName) {
		// The name of a money pile will never need to be changed
	}

	private int randomValue() {
		// Min + (int)(Math.random() * ((Max - Min) + 1))
		return 100 + (int) (Math.random() * ((400 - 100) + 1));
	}

}
