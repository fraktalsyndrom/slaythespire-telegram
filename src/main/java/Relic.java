public class Relic extends GameObject
{
	private String description;

	public Relic(String name, String rarity, String description)
	{
		super(name, rarity);
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public String toString()
	{
		StringBuilder printString = new StringBuilder();
		printString.append(name + '\n');
		printString.append(rarity + '\n');
		printString.append(description + '\n');

		return printString.toString();
	}
}
