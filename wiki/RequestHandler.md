> ----
> # DEPRECATED
> This class and its methods are deprecated since version 3.0.0 and will be removed in a later release.  
> Please use either the [[GetAction]] or the [[PostAction]] class for GET or POST requests respectively!
> 
> ----

The RequestHandler is used to post the guild counts or since Version 2.0.0 also get either the botlists or bot info from those Lists.

Posting guild counts requires you to previously set an instance of [[BotBlockAPI]].

## Getting an instance
You first need to get an instance of the RequestHandler.  
To get one, just call `new RequestHandler()`.  
```java
RequestHandler handler = new RequestHandler();
```

**Note**: Since [version 2.3.0](https://github.com/botblock/JavaBotBlockAPI/releases/tag/v2.3.0) does the RequestHandler use caching for different GET methods.  
If you for some reason don't want to have caching enabled (which you should since it also acts as a rate-limit prevention) you can do the following when getting an instance of the RequestHandler:  
```java
// true means we disable the caching.
RequestHandler handler = new RequestHandler(true);
```

## Post Guild counts
The RequestHandler offers different ways to post your guild counts.

### Automatic
To post your guild counts automatically you have to call the `startAutoPosting` method.  
The method requires either one of the following options:
- An instance of `ShardManager`.
- An instance of `JDA`.
- The bots ID as long and the guild counts as an integer.
- The bots ID as String and the guild counts as an integer.

All methods require an instance of BotBlockAPI with the set sites and tokens.  
The delay is defined through the [[setUpdateInterval|BotBlockAPI#setupdateintervalinteger]] method.

#### Examples
Here are some examples of different types.  
```java
private JDA jda = /* Get instance of JDA */
private ShardManager shardManager = /* Get instance of ShardManager */

private int guilds = /* Get the guild count */

private RequestHandler handler = new RequestHandler();
private BotBlockAPI api = /* Get instance of BotBlockAPI */

handler.startAutoPosting(jda, api);
handler.startAutoPosting(shardManager, api);
handler.startAutoPosting(123456789012345678L, guilds, api);
handler.startAutoPosting("123456789012345678", guilds, api);
```

### Manual
If you want to manually post the guild counts could you do this through the `postGuilds` method.

Similar to the automatic method does this one have different types that require different things.
- An instance of `ShardManager`.
- An instance of `JDA`.
- The bots ID as long and the guild counts as an integer.
- The bots ID as String and the guild counts as an integer.

All methods require an instance of BotBlockAPI with the set sites and tokens.  
The delay is defined through the [[setUpdateInterval|BotBlockAPI#setupdateintervalinteger]] method.

#### Examples
Here are some examples of different types.  
```java
private JDA jda = /* Get instance of JDA */
private ShardManager shardManager = /* Get instance of ShardManager */

private int guilds = /* Get the guild count */

private RequestHandler handler = new RequestHandler();
private BotBlockAPI api = /* Get instance of BotBlockAPI */

handler.postGuilds(jda, api);
handler.postGuilds(shardManager, api);
handler.postGuilds(123456789012345678L, guilds, api);
handler.postGuilds("123456789012345678", guilds, api);
```

## Get Bot lists or Bot info
Since Version 2.0.0 can you also receive either all bot lists or bot info from the bot lists.  
Those methods don't require an instance of BotBlockAPI to be set.

### All botlists
You can call `getBotlists(String)` to receive a JSONObject of all supported Botlists by BotBlock.org or `getBotlist(String, Site|String)` to get one specific Botlist.

#### Responses
Here are the possible JSONObjects of both methods. The second method uses `lbots.org` as an example.

**All botlists**:  
```json{
    "botlist.space": {
        "api_docs": "https://docs.botlist.space",
        "api_post": "https://api.botlist.space/v1/bots/:id",
        "api_field": "server_count",
        "api_shard_id": null,
        "api_shard_count": null,
        "api_shards": "shards",
        "api_get": "https://api.botlist.space/v1/bots/:id"
    },
    "lbots.org": {
        "api_docs": "https://lbots.org/api/docs",
        "api_post": "https://lbots.org/api/v1/bots/:id/stats",
        "api_field": "guild_count",
        "api_shard_id": "shard_id",
        "api_shard_count": "shard_count",
        "api_shards": null,
        "api_get": null
    }
}
```

**Single botlist**:  
```json
{
    "api_docs": "https://lbots.org/api/docs",
    "api_post": "https://lbots.org/api/v1/bots/:id/stats",
    "api_field": "guild_count",
    "api_shard_id": "shard_id",
    "api_shard_count": "shard_count",
    "api_shards": null,
    "api_get": null
}
```

### Complete Botinfo
Calling `getAll(...)` gives you the info of the bot from all botlists available on BotBlock as a JSONObject.  
You can provide the ShardManager, JDA instance or the Bot ID as String or long.
A response could look like this:  
```json
{
    "id": "123456789012345678",
    "name": "MyBot",
    "discriminator": "1234",
    "owners": [
        "234567890123456789"
    ],
    "server_count": 100,
    "invite": "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot",
    "list_data": {
        "botlist.space": [
            {"data"},
            200
        ],
        "lbots.org": [
            {"data"},
            404
        ]
    }
}
```  
The owners, server count and invite are based on how often those values appear on the different sites.  
`{"data"}` is different for each site and therefore depends on what the site returns.

### Bot info from all botlists
You can call `getBotInfos(...)` to receive only the information of the different botlists.  
The method supports ShardManager, JDA and the Bot ID as both Long or String.

The response can look like this:  
```json
{
    "botlist.space": [
        {"data"},
        200
    ],
    "lbots.org": [
        {"data"},
        404
    ]
}
```

### Bot info from a single site
You can use `getBotInfo(..., Site|String)` to receive the info of a single botlist as JSONArray.  
Similar to [`getBotlists(...)`](#bot-info-from-all-botlists) does it support ShardManager, JDA and the Bot ID.

**Example response**:  
```json
[
    {"data"},
    200
]
```  
`{"data"}` is the JSON returned by the botlist and therefore depends on the site itself.

### Get Owners
`getOwners(...)` allows you to get the owners as an ArrayList (Content-type String).