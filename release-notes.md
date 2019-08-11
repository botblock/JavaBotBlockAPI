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
    - [yabl.xyz](https://yabl.xyz) added.

- ### v2.2.0
  - Dependencies updated:
    - JDA: `4.BETA.0_32` to `4.0.0_39` (Official release of JDA v4)

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
