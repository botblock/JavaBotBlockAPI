The GetBotAction class allows to perform GET requests on the `/api/bots/:id` path of BotBlock.  
The methods require a valid Bot ID (Either as Long or String) to be provided.

> ### Note
> All requests are getting cached for 2 minutes to prevent possible rate limits.  
> If you want to disable this, use [`GetBotAction(true)`](#disable-caching).  
> **We do not recommend using this without any rate limit or caching in place!**

## Index
- [Disable caching](#disable-caching)
- [Methods](#methods)
  - [`getBotInfo(Long|String)`](#getbotinfolongstring)
  - [`getBotListInfo(Long|String)`](#getbotlistinfolongstring)
  - [`getBotListInfo(Long|String, Site|String)`](#getbotlistinfolongstring-sitestring)
  - [`getDiscriminator(Long|String)`](#getdiscriminatorlongstring)
  - [`getGitHub(Long|String)`](#getgithublongstring)
  - [`getInvite(Long|String)`](#getinvitelongstring)
  - [`getLibrary(Long|String)`](#getlibrarylongstring)
  - [`getName(Long|String)`](#getnamelongstring)
  - [`getOwners(Long|String)`](#getownerslongstring)
  - [`getPrefix(Long|String)`](#getprefixlongstring)
  - [`getServerCount(Long|String)`](#getservercountlongstring)
  - [`getSupportLink(Long|String)`](#getsupportlinklongstring)
  - [`getWebsite(Long|String)`](#getwebsitelongstring)


## Disable caching
If you want to disable caching, will you need to add a boolean to the `GetBotAction` constructor like this:  
```java
// "true" disables the internal caching.
GetBotAction getBotAction = new GetBotAction(true);
```

## Methods

### `getBotInfo(Long|String)`
**Returns**: JSONObject

Returns the complete information of the bot as JSONObject.  
If you only want to retrieve the information from the different bot lists, use [`getBotListInfo(Long|String)`](#getbotlistinfolongstring) instead.

<details>
  <summary>Example JSON (Click to open/close)</summary>

```json
{
    "id": "123456789012345678",
    "username": "Unknown",
    "discriminator": "0000",
    "owners": [],
    "server_count": null,
    "invite":"",
    "prefix": "",
    "website": "",
    "github": "",
    "support": "",
    "library": "",
    "list_data": {
        "botlist": [
            {"json": "This JSON is unique for each bot list"},
            404
        ]
    }
}
```
</details>

### `getBotListInfo(Long|String)`
**Returns**: JSONObject

Retrieves the information of a bot from all bot lists.  
The returned JSONObject contains JSONArrays of each bot list, which contains the response (JSON) of the site and the returned HTTP status.

### `getBotListInfo(Long|String, Site|String)`
**Returns**: JSONArray

Retrieves the information of a bot on a specific bot list.  
The returned JSONArray contains the response (JSON) of the bot list and the returned HTTP status of that site.

### `getDiscriminator(Long|String)`
**Returns**: String

Gets the discriminator of the bot. Will be `0000` when the user is invalid/unknown.

### `getGitHub(Long|String)`
**Returns**: Possibly-empty String

Gets the link to a bot's GitHub. Will be empty when the user is invalid/unknown or this value isn't set on any bot list.

### `getInvite(Long|String)`
**Returns**: Possibly-empty String

Gets the OAuth invite used to make the bot join your Discord. Will be empty when the user is invalid/unknown or this value isn't set on any bot list.

### `getLibrary(Long|String)`
**Returns**: Possibly-empty String

Gets the library the bot uses (f.e. JDA or discord.js). Will be empty when the user is invalid/unknown or this value isn't set on any bot list.

### `getName(Long|String)`
**Return**: String

Gets the name of the bot. Will be `Unknown` when the user is invalid/unknown.

### `getOwners(Long|String)`
**Returns**: Possibly-empty ArrayList\<String>

Gets a list of owners from the bot. Will be an empty ArrayList\<String> when the user is invalid/unknown.

### `getPrefix(Long|String)`
**Returns**: Possibly-empty String

Gets the prefix of the bot. Will be empty when the user is invalid/unknown or this value isn't set on any bot list.

### `getServerCount(Long|String)`
**Returns**: Possibly-null Integer

Gets the server count of the bot. Will be `null` when the user is invalid/unknown or this value isn't set on any bot list.

### `getSupportLink(Long|String)`
**Returns**: Possibly-empty String

Retrieves the Support link (i.e. Discord invite) of the bot. Will be empty when the user is invalid/unknown or this value isn't set on any bot list.

### `getWebsite(Long|String)`
**Returns**: Possibly-empty String

Retrieves the website link from the bot. Will be empty when the user is invalid/unknown or this value isn't set on any bot list.