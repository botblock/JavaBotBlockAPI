<!-- BotBlock links -->
[BotBlock]: https://botblock.org
[API]: https://botblock.org/api/docs

<!-- BotBlock4J -->
[BotBlock4J]: https://github.com/Nathan-webb/BotBlock4J

<!-- Javadoc link -->
[Javadocs]: https://jbba.dev/docs

<!-- Bintray links -->
[BadgeDownload]: https://img.shields.io/bintray/v/andre601/maven/JavaBotBlockAPI?label=Bintray&style=plastic
[Download]: https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion

<!-- JavaBotBlockAPI image -->
[image]: https://raw.githubusercontent.com/botblock/JavaBotBlockAPI/master/src/main/resources/JavaBotBlockAPI.png

<!-- Dependency links -->
[JDA]: https://github.com/DV8FromTheWorld/JDA
[OkHttp]: https://github.com/square/okhttp/
[JSON]: https://json.org
[JetBrains annotations]: https://github.com/JetBrains/java-annotations
[Caffeine]: https://github.com/ben-manes/caffeine

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[contributorsBadge]: https://img.shields.io/badge/Contributors_✨-2-green.svg?style=plastic
<!-- ALL-CONTRIBUTORS-BADGE:END -->

![image]

[![contributorsBadge]](#contributors-)

JavaBotBlockAPI is a continued and updated Java Wrapper for [BotBlock], a website that makes it possible to update guild counts on multiple lists with one API.  
This wrapper is a fork of [BotBlock4J] and was updated and improved to make it as userfriendly as possible.

# Installation
[![BadgeDownload]][Download]

You can install JavaBotBlockAPI through the following methods.  
Make sure to replace `{version}` with the above shown version.

## Gradle
Put this code into your `build.gradle`:  
```gradle
repositories{
    jcenter()
}

dependencies{
    compile group: 'com.andre601', name: 'JavaBotBlockAPI', version: '{version}'
}
```

## Maven
For maven use this code snippet:
```xml
<dependencies>
  <dependency>
    <groupId>com.andre601</groupId>
    <artifactId>JavaBotBlockAPI</artifactId>
    <version>{version}</version>
  </dependency>
</dependencies>
```

# Usage
To use the Wrapper you have to follow these steps.

## Notes
In the below examples do I use a JDA instance called `jda`.  
This will also work with ShardManager.

## POST methods
You can post you guild counts to the different Botlists using the BotBlock API.

### Creating an instance of BotBlockAPI
For posting your guild counts towards the BotBlock API you first need to create an instance of the BotBlockAPI class.  
To do this it's recommended to use `BotBlockAPI.Builder()`.

Here is an example of how it could look like.
```java
BotBlockAPI api = new BotBlockAPI.Builder()
    .addAuthToken("lbots.org", "MySecretToken123")
    .addAuthToken("botlist.space", "MySecretToken456")
    .build();
```
Remember to use `.build();` at the end to create the class.

### Auto Posting
JavaBotBlockAPI allows you to post the guild counts automatically every X minutes.  
To do this, you first need to get an instance of the PostAction and then call `.enableAutoPost(...)`.

Here is an example:
```java
PostAction postAction = new PostAction();

// api is the instance of the BotBlockAPI
postAction.enableAutoPost(jda, api);
```
The delay in which you post the guild counts is set through the `.setUpdateInterval(int)` method in the BotBlockAPI.Builder().

### Cancel auto posting
To cancel the auto posting just call `.disableAutoPost();` in the RequestHandler and it should cancel the scheduler.

### Manually posting
There are methods that allow you to post the guild counts manually.  
To Post your guild counts, just call the `.postGuilds(...)` method in the PostAction.

```java
PostAction postAction = new PostAction();

// api is the instance of the BotBlockAPI
postAction.postGuilds(jda, api);
```

## GET methods
Since version 2.0.0 of JavaBotBlockAPI can you get certain information of a bot or the available Bot lists on the BotBlock API.

### All available Bot lists
You can call `.getBotLists()` to receive a JSONObject with all available Botlists in the BotBlockAPI.

The returned JSONObject could look like this:
```json
{
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

### Single Bot list
Calling `.getBotList(String, Site|String)` returns a specific Botlist as JSONObject.  
For example does `.getBotList("123456789012345678", "lbots.org")` return the following JSONObject:
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

The first String parameter is used for the internal caching.

### Complete Bot info
Calling `.getBotInfo(Long|String)` returns a JSONObject from all the botlists and with some general information.

The JSONObject can look like this:
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

`{"data"}` is the JSON that is returned by the provided Botlist meaning it's different for each site.  
`name`, `discriminator`, `owners`, `server_count` and `invite` are based on the most common appearances of the data.

### Bot info from all Bot lists
You can call `.getBotListInfo(Long|String)` to only receive the bot info from all the Botlists.

The returned JSONObject can look like this:
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
`{"data"}` is the JSON that is returned by the provided Botlist meaning it's different for each site.

### Bot info of a single site
With `.getBotListInfo(Long|String, Site|String)` can you receive the info of a specific site.  
The returned data depends on the selected site and can be different for each one.

### Invite
The method `getInvite(Long|String)` allows you to get the OAuth invite of the bot.  
The invite is based on the most common appearance on the different bot lists.

### Server count
`.getServerCount(Long|String)` gives you the server count of the bot.  
The server count is based on the most common appearance on the different bot lists.

### Owners
You can call `.getOwners(Long|String)` to get the owners of a Bot from all the Botlists.  
The info is returned as ArrayList and is based on how often the info is provided by the botlists.

## Exceptions
When you post the guild counts you could encounter certain Exceptions.  
You can receive the following exceptions:
- `IOException`  
The Request couldn't be performed properly. This can be f.e. the case when BotBlock.org denies access (403).
- `RatelimitedException`  
When we exceed the ratelimit of BotBlock.org  
This is usually not the case when using the auto-post method because of the minimal delay being 2 minutes.  
The GET method would also not call this as the result is cached for 2 minutes (unless disabled).
- `NullPointerException`  
Thrown when BotBlock.org sends an empty response, meaning something got messed up on their side.

# Libraries
JavaBotBlockAPI utilizes different APIs to provide the functionality it offers right now.  
We have a list of those libraries listed here.
- [JDA] - Java library used for creating bots.
- [OkHttp] - Library for creating and managing http requests.
- [JSON] - Used for JSON management.
- [JetBrains annotations] - Used for annotations like `@NotNull` or `@Nullable`.
- [Caffeine] - Library used for caching.


# Links
Here are some useful links:
- [BotBlock.org][BotBlock] Site for which this wrapper was made.
  - [API] API documentation.
- [Javadocs] Java documentation of the Wrapper.
- [BotBlock4J] Original Wrapper from which this one originates.

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore -->
<table>
  <tr>
    <td align="center"><a href="https://tkachuk.tech"><img src="https://avatars1.githubusercontent.com/u/1907079?v=4" width="100px;" alt="Dave"/><br /><sub><b>Dave</b></sub></a><br /><a href="#ideas-DavidRockin" title="Ideas, Planning, & Feedback">🤔</a></td>
    <td align="center"><a href="http://linkedin.dv8tion.net"><img src="https://avatars1.githubusercontent.com/u/1479909?v=4" width="100px;" alt="Austin Keener"/><br /><sub><b>Austin Keener</b></sub></a><br /><a href="#plugin-DV8FromTheWorld" title="Plugin/utility libraries">🔌</a></td>
  </tr>
</table>

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
