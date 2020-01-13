<!-- BotBlock links -->
[BotBlock]: https://botblock.org
[API]: https://botblock.org/api/docs

<!-- BotBlock4J -->
[BotBlock4J]: https://github.com/Nathan-webb/BotBlock4J

<!-- Javadoc link -->
[Javadocs]: https://jbba.dev/docs

<!-- CI link (Thanks to CodeMC.io) -->
[CodeMC]: https://ci.codemc.io/view/Author/job/botblock/job/JavaBotBlockAPI/

<!-- Bintray links -->
[BadgeDownload]: https://img.shields.io/bintray/v/andre601/maven/JavaBotBlockAPI?label=Bintray&style=plastic
[Download]: https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion

<!-- JavaBotBlockAPI image -->
[image]: src/main/resources/JavaBotBlockAPI.png

<!-- Dependency links -->
[JDA]: https://github.com/DV8FromTheWorld/JDA
[OkHttp]: https://github.com/square/okhttp/
[JSON]: https://json.org
[JetBrains annotations]: https://github.com/JetBrains/java-annotations
[Caffeine]: https://github.com/ben-manes/caffeine

<!-- GitHub links -->
[Wiki]: https://github.com/botblock/JavaBotBlockAPI/wiki
[contributors.md]: https://github.com/botblock/JavaBotBlockAPI/blob/master/contributors.md

![image]

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
- [JetBrains annotations] - Used for annotations like `@NotNull` or `@Nullable`.
- [Caffeine] - Library used for caching.


# Links
Here are some useful links:
- [BotBlock.org][BotBlock] Site for which this wrapper was made.
  - [API] API documentation.
- [Javadocs] Java documentation of the Wrapper.
- [CodeMC] CI server for dev builds. Those jar files may differ from the ones on bintray.
- [BotBlock4J] Original Wrapper from which this one originates.

## Contributors ✨
We appreciate any contribution from others towards this project.  
All contributors are listed on the [contributors.md] file.