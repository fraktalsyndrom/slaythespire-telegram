public class Card
{
	String name;
	String rarity;
	String type;
	String cost;
	String description;
	String upgradedDescription;

	public Card(String name, String type, String rarity, String cost, String description, String upgradedDescription)
	{
		this.name = name;
		this.type = type;
		this.rarity = rarity;
		this.cost = cost;
		this.description = description;
		this.upgradedDescription = upgradedDescription;
	}

	public Card(String name, String type, String rarity, String cost, String description)
	{
		this.name = name;
		this.type = type;
		this.rarity = rarity;
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
		StringBuilder cardString = new StringBuilder();
		cardString.append(name + '\n');
		cardString.append(type + '\n');
		cardString.append(rarity + '\n');
		cardString.append(cost + '\n');
		cardString.append(description + '\n');
		if (!upgradedDescription.equals(""))
			cardString.append(upgradedDescription + '\n');

		return cardString.toString();
	}
}