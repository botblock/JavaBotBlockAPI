[BotBlock]: https://botblock.org  
[API]: https://botblock.org/api/docs
[list]: https://botblock.org/api/docs#count

[BotBlock4J]: https://github.com/spide-r/BotBlock4J

[wiki]: https://jbba.dev/docs

[CodeMC]: https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI/  
[CodeMCBadge]: https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.codemc.io%2Fjob%2Fbotblock%2Fjob%2FJavaBotBlockAPI%2F&label=Dev%20Builds&style=plastic

[BadgeDownload]: https://img.shields.io/bintray/v/andre601/maven/JavaBotBlockAPI?label=Bintray&style=plastic  
[Download]: https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion

[JDA]: https://github.com/DV8FromTheWorld/JDA  
[Javacord]: https://github.com/javacord/Javacord

[OkHttp]: https://github.com/square/okhttp/  
[JSON]: https://github.com/stleary/JSON-java  
[Caffeine]: https://github.com/ben-manes/caffeine  

[contributors.md]: https://github.com/botblock/JavaBotBlockAPI/blob/master/contributors.md

[Javadoc]: https://docs.botblock.org/JavaBotBlockAPI
[image]: https://docs.botblock.org/JavaBotBlockAPI/assets/img/JavaBotBlockAPI.png
[site]: https://docs.botblock.org/JavaBotBlockAPI/core/org/botblock/javabotblockapi/core/Site.html

![image]

JavaBotBlockAPI is a continued and updated Java Wrapper for [BotBlock], a website that makes it possible to update guild counts on multiple lists with one API.  
This wrapper is a fork of [BotBlock4J] and was updated and improved to make it as userfriendly as possible.

# Installation
[![BadgeDownload]](https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion) [![CodeMCBadge]](https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI/)

You can install JavaBotBlockAPI through the following methods.  
Make sure to replace `{version}` with the above shown version.

## Gradle
Put this code into your `build.gradle` to download all modules:  
```groovy
repositories{
    maven{ url = 'https://dl.bintray.com/andre601/maven' }
}

dependencies{
    compile group: 'org.botblock', name: 'javabotblockapi', version: '{version}'
}
```

if you want to only download specific modules can you just use `javabotblockapi-<module>`:
```groovy
repositories{
    maven{ url = 'https://dl.bintray.com/andre601/maven' }
}

dependencies{
    // Core Module. Always needed
    compile group: 'org.botblock', name: 'javabotblockapi-core', version: '{version}'

    // Request Module. Depends on Core
    compile group: 'org.botblock', name: 'javabotblockapi-request', version: '{version}'

    // JDA Module. Depends on Core and Request
    compile group: 'org.botblock', name: 'javabotblockapi-jda', version: '{version}'
    
    // Javacord Module. Depends on Core and Request
    compile group: 'org.botblock', name: 'javabotblockapi-javacord', version: '{version}'
}
```

## Maven
For maven use this code snippet to download all modules:
```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://dl.bintray.com/andre601/maven</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi</artifactId>
    <version>{version}</version>
  </dependency>
</dependencies>
```

if you want to only download specific modules can you just use `javabotblockapi-<module>`:
```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://dl.bintray.com/andre601/maven</url>
  </repository>
</repositories>

<dependencies>
  <!-- Core Module. Always needed -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-core</artifactId>
    <version>{version}</version>
  </dependency>

  <!-- Request Module. Depends on Core -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-request</artifactId>
    <version>{version}</version>
  </dependency>

  <!-- JDA Module. Depends on Core and Request -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-jda</artifactId>
    <version>{version}</version>
  </dependency>

  <!-- Javacord Module. Depends on Core on Request -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-javacord</artifactId>
    <version>{version}</version>
  </dependency>
</dependencies>
```

# Usage Examples
Below can you find a few examples on how JavaBotBlockAPI can/should be used.  
Please make sure to also take a look at the [Javadoc] for any additional information.

## BotBlockAPI
> **Required Modules**:  
> - `core`

If you want to POST guild counts to the various bot lists should you first create a BotBlockAPI instance.  
The BotBlockAPI class contains a nested Builder class which allows a more streamlined creation of a BotBlockAPI instance.

**Example**:  
```java
BotBlockAPI api = new BotBlockAPI.Builder()
    .addAuthToken("discordextremelist.xyz", "my_s3cr3t_t0k3n")
    .addAuthToken(Site.BOTLIST_SPACE, "my_53cret_tok3n")
    .build();
```

As you can see can you provide either a String with the id you can find [here][list] or use one of the many static [Site] instances that are offered.  
The Builder has some extra methods that can be used to further customize specific behaviours. Take a look on the [Javadoc] for those.

Next would you need to choose, which type of PostAction you want to use. Depending on your selection will you need to have the right module(s) downloaded.

## Default PostAction
> **Required Modules**:  
> - `request`
>
> *In the following examples will `botId` and `guilds` be used. Those are placeholders for your bot's ID and Guild count respectively.*

```java
// Create PostAction instance
PostAction postAction = new PostAction("botId");

// Post manually
postAction.postGuilds("botId", guilds, api);

// Post automatically
postAction.enableAutoPost("botId", guilds, api);

// Disable automatic posting
postAction.disableAutoPost(); // Disable with no delay
postAction.disableAutoPost(api); // Disable with BotBlockAPI#getUpdateDelay() delay
postAction.disableAutoPost(1, TimeUnit.MINUTES); // Disable with 1 Minute delay.
```

## JDA PostAction
> **Required Modules**:  
> - `request`
> - `jda`

```java
/*
 * Get your JDA instance.
 * This can also be a ShardManager instance
 * for sharded Bots.
 */
JDA jda = getJDA();

// Create PostAction instance
PostAction postAction = new PostAction(jda);


// Post manually
postAction.postGuilds(jda, api);

// Post automatically
postAction.enableAutoPost(jda, api);

// Disable automatic posting
postAction.disableAutoPost(); // Disable with no delay
postAction.disableAutoPost(api); // Disable with BotBlockAPI#getUpdateDelay() delay
postAction.disableAutoPost(1, TimeUnit.MINUTES); // Disable with 1 Minute delay.
```

## Javacord PostAction
> **Required Modules**:  
> - `request`
> - `javacord`

```java
/*
 * Get your DiscordApi instances.
 */
DiscordApi[] discordApis = getDiscordApis();

// Create PostAction instance
PostAction postAction = new PostAction(discordApis[0]);

// Post manually
postAction.postGuilds(api, discordApis);

// Post automatically
postAction.enableAutoPost(api, discordApis);

// Disable automatic posting
postAction.disableAutoPost(); // Disable with no delay
postAction.disableAutoPost(api); // Disable with BotBlockAPI#getUpdateDelay() delay
postAction.disableAutoPost(1, TimeUnit.MINUTES); // Disable with 1 Minute delay.
```

----
## Get Bot Information
> **Required Modules**:  
> - `core`
> - `request`

You can use the GetBotAction class to retrieve information about a bot on the different Bot lists.  
The class offers options for either getting the full information as JSONObject, or to retrieve specific information such as the authors of the bot.

Due to the huge amount of methods offered by this class are we not showing any examples here.  
A look into the [Javadoc] is highly recommendet.

----
## Get List Information
> **Required Modules**:  
> - `core`
> - `request`

Similar to [Getting Bot Information](#get-bot-information) can you also retrieve information known by BotBlock about either all or specific bot lists.  
The GetListAction allows the retrieval of all Bot Lists and their information as a JSONObject, or a specific info such as the URL used for seeing a Bot's list entry for a specific bot list.

Due to the huge amount of methods offered by this class are we not showing any examples here.  
A look into the [Javadoc] is highly recommendet.

# Libraries/Dependencies
JavaBotBlockAPI utilizes different APIs to provide the functionality it offers right now.  
We have a list of those libraries listed here.

- **Javacord Module**
  - [Javacord] - Java Wrapper for making Discord Bots.
- **JDA Module**
  - [JDA] - Java library used for creating bots.
- **Request Module**
  - [OkHttp] - Library for creating and managing http requests.
  - [JSON] - Used for JSON management.
  - [Caffeine] - Library used for caching.


# Links
Here are some useful links:
- [BotBlock.org][BotBlock] Site for which this wrapper was made.
  - [API] API documentation.
- [Javadoc] Java documentation of the Wrapper.
- [CodeMC] CI server for dev builds. Those jar files may differ from the ones on bintray.
- [BotBlock4J] Original Wrapper from which this one originates.

## Contributors
We appreciate any contribution from others towards this project.  
All contributors are listed on the [contributors.md] file.
