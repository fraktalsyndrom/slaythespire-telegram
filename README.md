# slaythespire-telegram
A Telegram bot that fetches information on cards and relics from Slay The Spire on inline requests.

## How to use
1) Add the bot to your group chat 
2) Make sure it has admin privileges (necessary in order for the bot to be able to read inline requests)
3) Use square brackets around card/relic names in your messages 
> example: ``[Clockwork souvenir] is amazing with [biased cognition]``)
4) The bot will respond with a message describing
> Example: ``Glacier (Skill, uncommon, cost 2)``
``Gain 7 (10) Block. Channel 2 Frost.``

## Running your own version
To run your own version of the bot, you need to create the file "telegram-bot.properties" in the root folder, with the two properties "botUsername" (specify your Telegram bot username) and "botToken" (your bot token).

Running ``mvn clean compile package`` will create an executable fat jar including all necessary dependencies.

## Thanks to
* Quincunx/ForgottenArbiter for their [excellent reference sheet](https://docs.google.com/spreadsheets/d/1ZsxNXebbELpcCi8N7FVOTNGdX_K9-BRC_LMgx4TORo4) from which I pulled the card and relic data.
* @rubenLagus for the excellent and easy-to-use [TelegramBots](https://github.com/rubenlagus/TelegramBots) Java library.
* @tanelso for their [reddit bot](https://github.com/tanelso2/SlayTheSpireCardBot/SlayTheSpireCardBot) which served as inspiration for this project.
