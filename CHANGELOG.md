Changelog
=========

## Legend
### `*`
Version is not available on Bintray and can only be downloaded using jitpack.

<details>
  <summary><b>Installation [Click to open/close]</b></summary>

Use one of the below options. Remember to replace `{Release tag}` with the tag of the release.

**Gradle**:  
```gradle
repositories{
    maven { url = "https://jitpack.io" }
}

dependencies{
    compile group: 'com.github.botblock', name: 'JavaBotBlockAPI', version: '{Release tag}'
}
```

**Maven**:  
```xml
<repositories>
    <repository>
        <id>jitpack</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.botblock</groupId>
        <artifactId>JavaBotBlockAPI</artifactId>
        <version>{Release tag}</version>
    </dependency>
</dependencies>
```
</details>

### `**`
Versions marked with this are available on bintray, but will require you, to add a download-url.

<details>
  <summary><b>Installation [Click to open/close]</b></summary>

Use one of the below options. Replace `{version}` with the version of your choice.

**Gradle**:  
```gradle
repositories{
    maven { url = "https://dl.bintray.com/andre601/maven" }
}

dependencies{
    compile group: 'org.botblock', name: 'JavaBotBlockAPI', version: '{version}'
}
```

**Maven**:  
```xml
<repositories>
    <repository>
        <id>jitpack</id>
        <url>https://dl.bintray.com/andre601/maven</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.botblock</groupId>
        <artifactId>JavaBotBlockAPI</artifactId>
        <version>{version}</version>
    </dependency>
</dependencies>
```
</details>

____
## v4
- ### v4.0.0_0
  - Updated packages from `com.andre601.javabotblockapi` to `org.botblock.javabotblockapi`.  
  **This is a breaking change.**

- ### v4.0.1_0
  - Updated message of RatelimitedException

- ### v4.0.2*
  - Updated version system.  
  Releases are back to `#.#.#` format while dev builds are `#.#.#_#`

- ### v4.0.3
  - Fixed broken version system.  
  Forgot to remove `BUILD_NUMBER: 0` from gradle.yml... Ooops

- ### v4.0.4
  - For unknown reason is the wrapper not available through normal downloads...

- ### v4.1.0
  - Merged the `@ReplacedWith` into the `@DeprecatedSince` annotation.

- ### v4.1.1
  - `@DeprecatedSince` was slightly changed.

- ### v4.2.0**
  - Added new get methods to GetAction:
    - `getDiscriminator(Long|String)`: Returns the discriminator of the bot or an empty String.
    - `getGitHub(Long|String)`: Returns GitHub url or an empty String.
    - `getLibrary(Long|String)`: Returns the library used by the bot or an empty String.
    - `getName(Long|String)`: Returns the name of the bot or an empty String.
    - `getPrefix(Long|String)`: Returns the command prefix of the bot or an empty String.
    - `getSupportLink(Long|String)`: Returns the support link (i.e. Discord invite) or an empty String.
    - `getWebsite(Long|String)`: Returns the website link of the bot or an empty String.
  - `getServerCount(Long|String id)` is now declared as `@Nullable` meaning result may be null.

- ### v4.2.1**
  - Removed unused imports.

- ### v4.2.2
  - Removed `GLENNBOTLIST_XYZ`

- ### v4.2.3
  - Deprecated `DISCORDBESTBOTS_XYZ`
    - Use `DISCORDEXTREMELIST_XYZ` instead as they merged their lists.

- ### v4.2.4
  - Added [`ARCANE_BOTCENTER_XYZ`](https://arcane-botcenter.xyz) and [`DBLISTA_PL`](https://dblista.pl)

- ### v4.2.5**
  - This was a test release for the attachment of zip files to the release. It failed.

- ### v4.3.0
  - Adds new GetAction
    - `getBotListFeatures(String, Site|String)`: Returns a JSONArray with all features the bot list has.

____
## v3
- ### v3.0.0
  - Moved post and get methods to their own class
    - GetAction was created for all GET methods.
    - PostAction was created for all POST methods.
  - RequestHandler (`com.andre601.javabotblockapi.RequestHandler`) is now deprecated.
  - Added [`top.gg`](https://top.gg) as alias for discordbots.org
  - New methods `getInvite(Long|String)` and `getServerCount(Long|String)` added.
  - Timeouts for POST requests where made dynamic.
  - GET methods which used either an JDA or ShardManager instance got removed.
  - Renamed multiple methods:
    - `getBotInfo` and `getBotInfos` where renamed to `getBotListInfo`
    - `getAll` was renamed to `getBotInfo`
    - `startAutoPosting` was renamed to `enableAutoPost`
    - `stopAutoPosting` was renamed to `disableAutoPost`
  - Dependencies updated:
    - JDA: `4.0.0_52` to `4.0.0_61`
    - annotations: `17.0.0` to `18.0.0`

- ### v3.0.1
  - Deprecated `Site.DISCORDBOTS_ORG`. Use `Site.TOP_GG` instead.

- ### v3.0.2*
  - Dependencies updated:
    - JDA: `4.0.0_61` to `4.0.0_70`

- ### v3.0.3
  - Same changes as [v3.0.2](#v302)

- ### v3.0.4*
  - RequestHandler was removed
  - Dependencies updated:
    - JDA: `4.0.0_70` to `4.0.0_73`

- ### v3.0.5*
  - Fixed broken build.gradle

- ### v3.0.6
  - It's `targetCompatibility` not `targetCompitability`

- ### v3.1.0
  - Updated Gradle to v5.5
  - Added links to Jenkins for dev builds (Thanks CodeMC)
  - Dependencies updated:
    - Shadow: `4.0.4` to `5.2.0`
    - JDA: `4.0.0_73` to `4.1.0_81`

- ### v3.2.0
  - Added `@DeprecatedSince` and `@ReplacedWith` annotations

- ### v3.2.1
  - Sites updated:
    - Added [`glennbotlist.xyz`](https://glennbotlist.xyz)
    - Added [`cloud-botlist.xyz`](https://cloud-botlist.xyz)

- ### v3.3.0_0
  - Version now has a build-number attached (used for Jenkin builds)
____
## v2
- ### v2.0.0
  - Added new get methods for bot and botlist infos.
  - Dependencies updated:
    - JDA: Updated to `4.BETA.0_23`

- ### v2.0.1
  - Dependencies updated:
    - JDA: `4.BETA.0_23` to `4.BETA.0_30`

- ### v2.0.2
  - Dependencies updated
    - JDA: `4.BETA.0_30` to `4.BETA.0_32`

- ### v2.1.0
  - Added new Site enum to have easier methods and reliable sitenames

- ### v2.1.1
  - Sites updated:
    - Added [`yabl.xyz`](https://yabl.xyz)

- ### v2.2.0
  - Dependencies updated:
    - JDA: `4.BETA.0_32` to `4.0.0_39` (Official release of JDA v4)

- ### v2.2.1
  - Dependencies updated:
    - JDA: `4.0.0_39` to `4.0.0_40`

- ### v2.3.0
  - Implemented caching to get methods.
    - `getBotlists()` and `getBotlist(String|Site site)` now require an additional String parameter.
  - Dependencies updated:
    - JDA: `4.0.0_40` to `4.0.0_45`

- ### v2.3.1
  - Adding GitHub Actions to workflow.
  
- ### v2.3.2
  - Attempt to fix GitHub's Actions.

- ### v2.3.3
  - Sites updated:
    - Added [`discordextremelist.xyz`](https://discordextremelist.xyz)
    - Removed `discordbotlist.us.to`
  - Removed GitHub Actions for now.

- ### v2.3.4
  - Sites updated:
    - Fixed wrong Site name from DISCORSDBESTBOTS\_XYZ to DISCORDBESTBOTS_XYZ

- ### v2.3.5
  - Dependencies updated:
    - JDA: `4.0.0_45` to `4.0.0_48`
  - Updated to Java 11. It can still work with Java 8.

- ### v2.3.6
  - 2nd attempt on integrating GitHub actions (failed)
  
- ### v2.3.8
  - Implemented GitHub actions for automated releasing to Bintray.

- ### v2.3.9
  - Dependencies updated:
    - JDA: `4.0.0_48` to `4.0.0_52`
    - OkHttp: `4.2.1` to `4.2.2`

____
## v1

- ### v1.0.0
  - First release

- ### v1.0.1
  - Make BotBlockAPI.Builder static. [Fixes "Non enclosing class" error]

- ### v1.0.2
  - Moved JDA and ShardManager from BotBlockAPI to RequestHandler methods.

- ### v1.0.3
  - Fixing JSONException when BotBlock.org has no 'failure' object present.

- ### v1.0.4
  - Made RequestHandler an empty constructor.

- ### v1.0.5
  - Updated minimal delay to 2 since rate limit is 1/120s.
