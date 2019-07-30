## v2

- ### v2.0.0
  - Added new get methods for bot and botlist infos.
  - Updated JDA version to 4

- ### v2.0.1
  - Update dependency: `JDA 4.BETA.0_23 -> 4.BETA.0_30`

- ### v2.0.2
  - Update dependency: `JDA 4.BETA.0_30 -> 4.BETA.0_32`

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
