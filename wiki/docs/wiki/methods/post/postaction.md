[Javadoc]: /../javadoc/com/andre601/javabotblockapi/requests/PostAction.html

The PostAction class contains all methods used to post to the BotBlock API.

!!! info "Notes"
    - All methods of this class require a [[BotBlockAPI]] instance to be set!
    - All methods can be used with either a JDA instance, a ShardManager instance or the ID of the bot (As String or Long) and guild count. Refer to the [Javadoc] for more information.

## Post automatically
You can use the `enableAutoPost` method to post your bots guild count automatically.  
You set the delay in the BotBlockAPI through the `setUpdateInterval` method. Note that the delay can't be below 2.

To stop the automatic posting, use the `disableAutoPost` method.

Example:  
```java
JDA jda = /* Get your JDA instance */

// 1. Create an instance of the BotBlockAPI
BotBlockAPI api = new BotBlockAPI.Builder()
        .addAuthToken("lbots.org", "My.5ecr37.T0k3n")
        .addAuthToken(Site.BOTLIST_SPACE, "My.s3crEt.7okEn") // The API has a enum with all sites.
        .setUpdateInterval(5) // Post all 5 minutes.
        .build();

// 2. Create an instance of the PostAction
PostAction post = new PostAction();

// 3. Call the enableAutoPost method
post.enableAutoPost(jda, api);

// 4. Disable the auto-posting
post.disableAutoPost();
```

## Post manually
To post manually will you need to use the `postGuilds` method.

Example:  
```java
JDA jda = /* Get your JDA instance */

// 1. Create an instance of the BotBlockAPI
BotBlockAPI api = new BotBlockAPI.Builder()
        .addAuthToken("lbots.org", "My.5ecr37.T0k3n")
        .addAuthToken(Site.BOTLIST_SPACE, "My.s3crEt.7okEn") // The API has a enum with all sites.
        .build();

// 2. Create an instance of the PostAction
PostAction post = new PostAction();

// 3. Call the postGuilds method you want to use
post.postGuilds(jda, api);
```

## Exceptions
The methods may throw one of those errors:

- `RatelimitedException`  
When you get rate limited by the BotBlock site. You can only post every 120 seconds (2 minutes).
- `IOException`  
When the request couldn't be performed. Causes can be different.