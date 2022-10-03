public class GameObject
{
	String name;
	String rarity;

	public GameObject(String name, String rarity)
	{
		this.name = name;
		this.rarity = rarity;
	}

	public String getName()
	{
		return name;
	}

	public String getRarity()
	{
		return rarity;
	}
}
