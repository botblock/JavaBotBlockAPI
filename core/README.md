---
---

This is the core module used across all other modules.

When using either of the other modules is this one here required to be installed too.

# Installation

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
</dependencies>
```


## Gradle (Recommendet)

```groovy
repositories{
    jcenter()
}

dependencies{
    compile 'org.botblock:JavaBotBlockAPI-core:{{site.version}}'
}
```

## Manual
We do not recommend using jar files directly and instead use one of the above dependency management systems.

If you still want to do it manually, or can't use one of the other option, head over to the
<a target="_blank" href="https://github.com/botblock/JavaBotBlockAPI/releases/latest">GitHub releases page</a> or to
the <a target="_blank" href="https://bintray.com/beta/#/andre601/maven/JavaBotBlockAPI?tab=overview">Bintray release page</a>
and download the jar files from there.

Note that you will not receive any support when using this method.
