## v1.0.5
- Updated minimal delay to 2 since ratelimit is 1/120s.

## v1.0.4
- RequestHandler now no longer allows values in itself. Instead you provide them through the postGuilds  or startAutoPosting methods.

## v1.0.3
- Attempt to fix JSONException for when BotBlock.org has no `failure` object.

## v1.0.2
- Removed JDA and ShardManager from BotBlockAPI and let RequestHandler handle those.

## v1.0.1
- Make Builder of BotBlockAPI static to fix error with it not being an enclosing class.

## v1.0.0
- First release.
