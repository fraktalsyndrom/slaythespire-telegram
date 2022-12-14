import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main
{
	public static final String DATABASE_FILE_PATH = "cards.tsv";
	public static final String RELICS_FILE_PATH = "relics.tsv";

	public static void main(String[] args)
	{
		try
		{
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new SlayTheSpireBot(new GameObjectDatabase(DATABASE_FILE_PATH, RELICS_FILE_PATH)));
		}
		catch (TelegramApiException e)
		{
			e.printStackTrace();
		}

	}
}
