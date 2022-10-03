import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameObjectDatabase
{
	private List<Card> cardList;
	private List<Relic> relicList;

	public GameObjectDatabase(String cardsFileName, String relicsFileName)
	{
		List<List<String>> cardRows;
		List<List<String>> relicRows;

		cardRows = populateListFromFile(cardsFileName);
		relicRows = populateListFromFile(relicsFileName);

		cardList = createCardDatabase(cardRows);
		relicList = createRelicDatabase(relicRows);
	}

	private List<List<String>> populateListFromFile(String cardDatabaseFileName)
	{
		List<List<String>> records = new ArrayList<>();

		try (BufferedReader cardsReader = new BufferedReader(new FileReader(cardDatabaseFileName)))
		{
			String line;
			while ((line = cardsReader.readLine()) != null)
			{
				System.out.println(line);
				String[] values = line.split("\\t");
				if (values.length < 4)
					continue;
				records.add(Arrays.asList(values));
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return records;
	}


	public List<Card> createCardDatabase(List<List<String>> records)
	{
		List<Card> cards = new ArrayList<>();
		for (List<String> record : records)
		{
			System.out.println(record);

			Card card;
			String name = record.get(0);
			String type = record.get(1);
			String rarity = record.get(2);
			String cost = record.get(3);
			String description = record.get(4);
			String upgradedDescription = null;
			if (record.size() >= 6)
				upgradedDescription = record.get(5);

			if (upgradedDescription != null)
			{
				card = new Card(name, type, rarity, cost, description, upgradedDescription);
			}
			else
			{
				card = new Card(name, type, rarity, cost, description);
			}
			cards.add(card);
		}

		return cards;
	}

	public List<Relic> createRelicDatabase(List<List<String>> records)
	{
		List<Relic> relics = new ArrayList<>();
		for (List<String> record : records)
		{
			System.out.println(record);

			String name = record.get(0);
			String rarity = record.get(1);
			String description = record.get(3);

			Relic relic = new Relic(name, rarity, description);

			relics.add(relic);
		}

		return relics;
	}

	public Card getCard(String cardName)
	{
		for (Card card : cardList)
		{
			if (card.getName().equalsIgnoreCase(cardName))
				return card;
		}

		throw new IllegalArgumentException("Could not find card named " + cardName + " in database");
	}

	public Relic getRelic(String relicName)
	{
		for (Relic relic : relicList)
		{
			if (relic.getName().equalsIgnoreCase(relicName))
				return relic;
		}

		throw new IllegalArgumentException("Could not find relic named " + relicName + " in database");
	}

	public GameObject getGameObject(String gameObjectName)
	{
		for (GameObject gameObject : cardList)
		{
			if (gameObject.getName().equalsIgnoreCase(gameObjectName))
				return gameObject;
		}

		for (GameObject gameObject : relicList)
		{
			if (gameObject.getName().equalsIgnoreCase(gameObjectName))
				return gameObject;
		}

		throw new IllegalArgumentException("Could not find game object named " + gameObjectName + " in database");
	}
}