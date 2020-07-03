<!-- BotBlock links -->
[BotBlock]: https://botblock.org
[API]: https://botblock.org/api/docs

<!-- BotBlock4J -->
[BotBlock4J]: https://github.com/Nathan-webb/BotBlock4J

<!-- Javadoc link -->
[wiki]: https://jbba.dev/docs

<!-- CI link (Thanks to CodeMC.io) -->
[CodeMC]: https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI/
[CodeMCBadge]: https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.codemc.io%2Fjob%2Fbotblock%2Fjob%2FJavaBotBlockAPI%2F&label=Dev%20Builds&style=plastic

<!-- Bintray links -->
[BadgeDownload]: https://img.shields.io/bintray/v/andre601/maven/JavaBotBlockAPI?label=Bintray&style=plastic
[Download]: https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion

<!-- JavaBotBlockAPI image -->
[image]: src/main/resources/JavaBotBlockAPI.png

<!-- Dependency links -->
[JDA]: https://github.com/DV8FromTheWorld/JDA
[OkHttp]: https://github.com/square/okhttp/
[JSON]: https://github.com/stleary/JSON-java
[Caffeine]: https://github.com/ben-manes/caffeine

<!-- GitHub links -->
[Wiki]: https://github.com/botblock/JavaBotBlockAPI/wiki
[contributors.md]: https://github.com/botblock/JavaBotBlockAPI/blob/master/contributors.md

![image]

JavaBotBlockAPI is a continued and updated Java Wrapper for [BotBlock], a website that makes it possible to update guild counts on multiple lists with one API.  
This wrapper is a fork of [BotBlock4J] and was updated and improved to make it as userfriendly as possible.

# Installation
[![BadgeDownload]][Download] [![CodeMCBadge]][CodeMC]

You can install JavaBotBlockAPI through the following methods.  
Make sure to replace `{version}` with the above shown version.

## Gradle
Put this code into your `build.gradle`:  
```gradle
repositories{
    jcenter()
}

dependencies{
    compile group: 'org.botblock', name: 'JavaBotBlockAPI', version: '{version}'
}
```

## Maven
For maven use this code snippet:
```xml
<dependencies>
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>JavaBotBlockAPI</artifactId>
    <version>{version}</version>
  </dependency>
</dependencies>
```

# Usage
Please visit the [wiki] for all available POST and GET methods, as the amount of GET methods alone is quite large.

# Libraries/Dependencies
JavaBotBlockAPI utilizes different APIs to provide the functionality it offers right now.  
We have a list of those libraries listed here.
- [JDA] - Java library used for creating bots.
- [OkHttp] - Library for creating and managing http requests.
- [JSON] - Used for JSON management.
- [Caffeine] - Library used for caching.


# Links
Here are some useful links:
- [BotBlock.org][BotBlock] Site for which this wrapper was made.
  - [API] API documentation.
- [Wiki and Javadocs][wiki] Java documentation of the Wrapper.
- [CodeMC] CI server for dev builds. Those jar files may differ from the ones on bintray.
- [BotBlock4J] Original Wrapper from which this one originates.

## Contributors ✨
We appreciate any contribution from others towards this project.  
All contributors are listed on the [contributors.md] file.
