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
    - Added [`yabl.xyz`](https://yabl.xyz) added.

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
