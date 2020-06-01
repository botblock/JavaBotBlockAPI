[Site]: https://docs.botblock.org/JavaBotBlockAPI/org/botblock/javabotblockapi/Site.html  
[API]: https://botblock.org/api/docs#count

The BotBlockAPI is the essential class for loading botlist sites and their API tokens to then post the Guild counts.  
Posting the guild counts is handled through the [PostAction](../post/postaction) class.

## Getting started
To start, you need to add the sites and their corresponding API-tokens to the BotBlockAPI class.  
You may receive an API-token from the bot lists your bot is listed on.

In order to do this, use the `BotBlockAPI.Builder` to easily create an instance of BotBlockAPI.

## Builder
The Builder is a internal class inside the BotBlockAPI to create an instance of BotBlockAPI easy and fast.  
The class offers the following methods to use:

### `addAuthToken(Site, String)`
> **Required**: Yes (You have to use it at least once)

Adds the provided [Site] and token to the HashMap.

### `addAuthToken(String, String)`
> **Required**: Yes (You have to use it at least once)

Adds the provided Site and token to the HashMap.  
A list of all supported sites can be found on the [API doc of BotBlock][API]

### `setAuthTokens(HashMap<String, String>)`
> **Required**: Yes (When you didn't use [`addAuthToken(..., String)`](#addauthtokensite-string) at least once.)

Similar to `addAuthToken` but instead directly sets the HashMap.  
**This will overwrite every previously set entry.**

### `setUpdateInterval(Integer)`
> **Required**: No. (Default is 30)

Sets the delay (in minutes) in which the auto-post option should post the guild counts to the BotBlock API.  
The minimum delay is 2 minutes. Anything below this number will throw an `IllegalArgumentException`.

### `build()`
> **Required**: Yes

Builds the Instance of the BotBlockAPI with the previously set information.
