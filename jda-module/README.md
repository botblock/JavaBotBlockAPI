---
---

This is the jda-module, which is used to provide support for the Java Discord API (JDA).

It depends on both the {{ anchor('Core Module', 'Core') }} and the {{ anchor('Request Module', 'Request') }} and won't work without it.

## Installation

## Maven

```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <name>jcenter-bintray</name>
        <url>https://jcenter.binrtray.com</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>JavaBotBlockAPI-core</artifactId>
        <version>{{site.version}}</version>
    </dependency>
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>JavaBotBlockAPI-request-module</artifactId>
        <version>{{site.version}}</version>
    </dependency>
    <dependency>
        <groupId>org.botblock</groupId>
        <artifactId>JavaBotBlockAPI-jda-module</artifactId>
        <version>{{site.version}}</version>
    </dependency>
</dependencies>
```


## Gradle (Recommendet)

```groovy
repositories{
    jcenter()
}
dependencies{
    compile 'org.botblock:JavaBotBlockAPI-core:{{site.version}}'
    compile 'org.botblock:JavaBotBlockAPI-request-module:{{site.version}}'
    compile 'org.botblock:JavaBotBlockAPI-jda-module:{{site.version}}'
}
```

## Manual
We do not recommend using jar files directly and instead use one of the above dependency management systems.

If you still want to do it manually, or can't use one of the other option, head over to the
<a target="_blank" href="https://github.com/botblock/JavaBotBlockAPI/releases/latest">GitHub releases page</a> or to
the <a target="_blank" href="https://bintray.com/beta/#/andre601/maven/JavaBotBlockAPI?tab=overview">Bintray release page</a>
and download the jar files from there.

Note that you will not receive any support when using this method.
