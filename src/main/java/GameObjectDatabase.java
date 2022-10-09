import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameObjectDatabase
{
	private Map<String,GameObject> gameObjectsMap;

	public GameObjectDatabase(String cardsFileName, String relicsFileName)
	{
		gameObjectsMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

		List<List<String>> cardRecords = populateListFromFile(cardsFileName);
		List<List<String>> relicRecords = populateListFromFile(relicsFileName);;

		createCardDatabase(cardRecords);
		createRelicDatabase(relicRecords);
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

		} catch (Exception e) { e.printStackTrace(); }

		return records;
	}


	private void createCardDatabase(List<List<String>> records)
	{
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

			gameObjectsMap.put(name, card);
		}
	}

	private void createRelicDatabase(List<List<String>> records)
	{
		for (List<String> record : records)
		{
			System.out.println(record);

			String name = record.get(0);
			String rarity = record.get(1);
			String description = record.get(3);

			Relic relic = new Relic(name, rarity, description);

			gameObjectsMap.put(name, relic);
		}
	}

	public GameObject getGameObject(String gameObjectName)
	{
		GameObject target = gameObjectsMap.get(gameObjectName);
		if (target != null)
			return target;

		throw new IllegalArgumentException("Could not find game object named " + gameObjectName + " in database");
	}
}