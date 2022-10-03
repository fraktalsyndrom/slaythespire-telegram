public class Card extends GameObject
{

	String type;
	String cost;
	String description;
	String upgradedDescription;

	public Card(String name, String type, String rarity, String cost, String description, String upgradedDescription)
	{
		super(name, rarity);
		this.type = type;
		this.cost = cost;
		this.description = description;
		this.upgradedDescription = upgradedDescription;
	}

	public Card(String name, String type, String rarity, String cost, String description)
	{
		super(name, rarity);
		this.type = type;
		this.cost = cost;
		this.description = description;
		upgradedDescription = "";
	}

	public String getName()
	{
		return name;
	}

	public String getRarity()
	{
		return rarity;
	}

	public String getType()
	{
		return type;
	}

	public String getCost()
	{
		return cost;
	}

	public String getDescription()
	{
		return description;
	}

	public String getUpgradedDescription()
	{
		return upgradedDescription;
	}

	@Override
	public String toString()
	{
		StringBuilder printString = new StringBuilder();
		printString.append(name + '\n');
		printString.append(type + '\n');
		printString.append(rarity + '\n');
		printString.append("Cost: " + cost + " energy\n");
		printString.append(description + '\n');
		if (!upgradedDescription.equals(""))
			printString.append("Upgraded: " + upgradedDescription + '\n');

		return printString.toString();
	}
}