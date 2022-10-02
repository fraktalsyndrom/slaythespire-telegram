import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDatabase
{
	private List<Card> cardList;

	public CardDatabase(String databaseFileName)
	{
		List<List<String>> records = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(databaseFileName)))
		{
			String line;
			while ((line = reader.readLine()) != null)
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

		cardList = generateCards(records);
	}

	private List<Card> generateCards(List<List<String>> records)
	{
		List<Card> cards = new ArrayList<Card>();
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

	public Card getCard(String cardName)
	{
		for (Card card : cardList)
		{
			if (card.getName().equalsIgnoreCase(cardName))
				return card;
		}

		throw new IllegalArgumentException("Could not find card named " + cardName + " in database");
	}

	public List<Card> getAllCards()
	{
		return cardList;
	}
}