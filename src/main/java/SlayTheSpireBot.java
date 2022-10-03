import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlayTheSpireBot extends TelegramLongPollingBot
{
	private GameObjectDatabase dataBase;
	private String botUsername;
	private String botToken;

	public SlayTheSpireBot(GameObjectDatabase dataBase)
	{
		super();
		initBotInfo();
		this.dataBase = dataBase;
	}

	private void initBotInfo()
	{
		try (InputStream input = new FileInputStream("telegram-bot.properties"))
		{
			Properties properties = new Properties();

			properties.load(input);
			botUsername = properties.getProperty("botUsername");
			botToken = properties.getProperty("botToken");
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public GameObjectDatabase getDataBase()
	{
		return dataBase;
	}

	@Override
	public String getBotUsername()
	{
		return botUsername;
	}

	@Override
	public String getBotToken()
	{
		return botToken;
	}

	@Override
	public void onUpdateReceived(Update update)
	{
		if (update.hasMessage() && update.getMessage().hasText())
		{
			String messageText = update.getMessage().getText();

			if (messageContainsGameObjectRequest(messageText))
			{
				List<String> gameObjectNames = extractGameObjectNamesFrom(messageText);
				String outgoingMessageText = "";

				for (String requestedGameObjectName : gameObjectNames)
				{
					GameObject requestedGameObject = dataBase.getGameObject(requestedGameObjectName);
					outgoingMessageText += requestedGameObject.toString() + '\n';
				}

				SendMessage outgoingMessage = new SendMessage();
				outgoingMessage.setChatId(update.getMessage().getChatId().toString());
				outgoingMessage.setText(outgoingMessageText);

				try
				{
					execute(outgoingMessage);
				} catch (TelegramApiException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private boolean messageContainsGameObjectRequest(String messageText)
	{
		//Pattern pattern = Pattern.compile("\\[(.*?)\\]"); // [feed][glacier] OK, [feed] och [glacier] OK, en [feed] FAIL
		Pattern pattern = Pattern.compile("\\[([^)]+)\\]"); // [feed][glacier] OK, [feed] och [glacier] OK, en [feed] FAIL
		Matcher matcher = pattern.matcher(messageText);

		return matcher.find();
	}

	private List<String> extractGameObjectNamesFrom(String messageText)
	{
		List<String> cardNames = new ArrayList<>();

		String remainingMessageText = messageText;
		int firstOpenBracketIndex = remainingMessageText.indexOf('[');
		while (firstOpenBracketIndex != -1)
		{
			int firstCloseBracketIndex = remainingMessageText.indexOf(']');

			String cardName = remainingMessageText.substring(firstOpenBracketIndex + 1, firstCloseBracketIndex);
			cardNames.add(cardName);

			remainingMessageText = remainingMessageText.substring(firstCloseBracketIndex + 1);
			firstOpenBracketIndex = remainingMessageText.indexOf('[');
		}

		return cardNames;
	}
}
