# JavaBotBlockAPI
A Java Wrapper for the BotBlock.org API, supporting all API endpoints of it.

## Javadocs
Below can you find Javadocs for each module.

- [Core](https://docs.botblock.org/JavaBotBlockAPI/core/)
- [Javacord](https://docs.botblock.org/JavaBotBlockAPI/javacord/org/botblock/javabotblockapi/javacord/package-summary.html)
- [JDA](https://docs.botblock.org/JavaBotBlockAPI/jda/org/botblock/javabotblockapi/jda/package-summary.html)
- [Request](https://docs.botblock.org/JavaBotBlockAPI/request/org/botblock/javabotblockapi/requests/package-summary.html)

## Installation
Please replace `{version}` with the latest build available on [CodeMC](https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI/).

### Gradle
```groovy
repositories{
    maven{ url = 'https://repo.codemc.io/repository/maven-public' }
}

dependencies{
    // The Core and Request modules are always required.
    compile group: 'org.botblock', name: 'javabotblockapi-core', version: '{version}'
    compile group: 'org.botblock', name: 'javabotblockapi-request', version: '{version}'
    
    // Javacord module for direct support with Javacord.
    compile group: 'org.botblock', name: 'javabotblockapi-javacord', version: '{version}'
    
    // JDA module for direct support with JDA.
    compile group: 'org.botblock', name: 'javabotblockapi-jda', version: '{version}'
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>codemc</id>
        <url>https://repo.codemc.io/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <!-- The Core module is always required. -->
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>javabotblockapi-core</artifactId>
        <version>{version}</version>
    </dependency>
    <!-- The Request module is always required. -->
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>javabotblockapi-request</artifactId>
        <version>{version}</version>
    </dependency>
    <!-- Javacord module for direct support with Javacord. -->
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>javabotblockapi-javacord</artifactId>
        <version>{version}</version>
    </dependency>
    <!-- JDA module for direct support with JDA. -->
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>javabotblockapi-jda</artifactId>
        <version>{version}</version>
    </dependency>
</dependencies>
```

## Links

- [BotBlock Documentation](https://botblock.org/docs)
- [BotBlock Discord](https://botblock.org/discord) (**Do NOT ask BotBlock-Staff for help with this API Wrapper**)
- [Andre's Support Discord](https://discord.gg/6dazXp6) (Ask in the `#javabotblockapi` channel)
