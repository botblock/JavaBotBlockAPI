!!! warning "Deprecated"
    This class is marked as deprecated since version 5.0.0!  
    Use [GetBotAction](/wiki/methods/get/getbotaction) or [GetListAction](/wiki/methods/get/getlistaction) respectively.

The GetAction class is used to retrieve values from a bot on the different Bot lists.

!!! info "Note"
    - The requests are cached for __2 minutes__ to avoid rate limits. You may disable it (see below) but it's not recommended.

## Index
- [Disable caching](#disable-caching)
- [GET methods (Bot)](#get-methods-bot)
    - [`getBotInfo(Long|String)`](#getbotinfo)
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
- [GET methods (Bot list)](#get-methods-bot-list)
    - [`getBotList(String, Site|String)`](#getbotliststring-sitestring)
    - [`getBotLists(String)`](#getbotlistsstring)
    - [`getBotListFeatures(String, Site|String)`](#getbotlistfeaturesstring-sitestring)



## Disable caching
All requests performed through this class are cached by default.  
If you don't want this for some reason (i.e. you use your own caching system), you may disable the caching like this:  
```java
// "true" DISABLES the caching.
GetAction get = new GetAction(true);
```

We highly recommend to NOT disable caching as you may get rate limited without any proper delaying of the requests.

## GET methods (Bot)

### `getBotInfo(Long|String)`
**Returns**: JSONObject

Retrieves the complete bot info.

<details>
  <summary>Example JSON (Click to open/close)</summary>

```json
{
    "id": "123456789012345678",
    "username": "MyBot",
    "discriminator": "1234",
    "owners": [
        "234567890123456789"
    ],
    "server_count": 100,
    "invite":"https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot",
    "prefix": "?",
    "website": "https://mybot.com",
    "github": "https://github.com/mybot/MyBot",
    "support": "https://discord.gg/AbCdE9F",
    "library": "JDA",
    "list_data": {
        "lbots.org": [
            {"data": "Unique bot data"},
            200
        ],
        "botlist.space": [
            {"data": "Unique bot data"},
            404
        ]
    }
}
```
</details>

### `getBotListInfo(Long|String)`
**Returns**: JSONObject

Retrieves the information of a bot from all bot lists.

<details>
  <summary>Example JSON (Click to open/close)</summary>

```json
{
    "lbots.org": [
        {"data": "Unique bot data"},
        200
    ],
    "botlist.space": [
        {"data": "Unique bot data"},
        404
    ]
}
```
</details>

### `getBotListInfo(Long|String, Site|String)`
**Returns**: JSONArray

Retrieves the information of a bot on a specific bot list.

<details>
  <summary>Example JSON (Click to open/close)</summary>

```
[
    {"data": "Unique bot data"},
    200
]
```
</details>

### `getDiscriminator(Long|String)`
**Returns**: String

Retrieves the Discriminator of the bot.

### `getGitHub(Long|String)`
**Returns**: Possibly-null String

Retrieves the GitHub link of the bot.

### `getInvite(Long|String)`
**Returns**: Possibly-null String

Retrieves the OAuth Invite (Not Discord invite) of the bot.

### `getLibrary(Long|String)`
**Returns**: Possibly-null String

Retrieves the used library of the bot.

### `getName(Long|String)`
**Return**: Possibly-null String

Retrieves the name of the bot.

### `getOwners(Long|String)`
**Returns**: Possibly-empty ArrayList\<String>

Retrieves the list of Bot Owners.

### `getPrefix(Long|String)`
**Returns**: Possibly-null String

Retrieves the prefix of the bot.

### `getServerCount(Long|String)`
**Returns**: Possibly-null Integer

Retrieves the server count of the bot.

### `getSupportLong(Long|String)`
**Returns**: Possibly-null String

Retrieves the Support link (i.e. Discord invite) of the bot.

### `getWebsite(Long|String)`
**Returns**: Possibly-null String

Retrieves the website link from the bot.

## GET methods (Bot list)

### `getBotList(String, Site|String)`
**Returns**: JSONObject

Retrieves the API information of a specific bot list.

<details>
  <summary>Example JSON (Click to open/close)</summary>

```json
{
    "id": "lbots.org",
    "added": 1549227235,
    "name": "LBots",
    "url": "https://lbots.org/",
    "icon": "https://lbots.org/static/img/logo.png",
    "language": "English",
    "display": 1,
    "defunct": 0,
    "discord_only": 1,
    "description": "A bot listing website that loves NSFW",
    "api_docs": "https://lbots.org/api/docs",
    "api_post": "https://lbots.org/api/v1/bots/:id/stats",
    "api_field": "guild_count",
    "api_shard_id": "shard_id",
    "api_shard_count": "shard_count",
    "api_shards": null,
    "api_get": null,
    "api_all": null,
    "view_bot": "https://lbots.org/bots/:id",
    "bot_widget": null,
    "content": null,
    "owners": "Neko#0013 (367330084337745920)",
    "discord": "https://discord.gg/EKv9k6p",
    "features": [
        {
            "name": "Offers Paid Promotion",
            "id": 21,
            "display": 5,
            "type": 1,
            "description": null,
            "value": 1
        },
        {
            "name": "HTML Long Description",
            "id": 6,
            "display": 4,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Custom Bot GitHub Link",
            "id": 15,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Custom Bot Support Link",
            "id": 8,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Custom Bot Website Link",
            "id": 13,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Discord Bot Support Link",
            "id": 12,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Has Voting",
            "id": 2,
            "display": 2,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Votes sent to Webhooks",
            "id": 24,
            "display": 2,
            "type": 1,
            "description": null,
            "value": 1
        },
        {
            "name": "Has Categories or Tags",
            "id": 9,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Has Mobile Support",
            "id": 26,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Has Search",
            "id": 23,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Requires Owner in Server",
            "id": 25,
            "display": 0,
            "type": 1,
            "description": null,
            "value": 1
        },
        {
            "name": "Server Count API",
            "id": 3,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 1
        },
        {
            "name": "Has Ads on Site",
            "id": 11,
            "display": 5,
            "type": 1,
            "description": null,
            "value": 0
        },
        {
            "name": "Paid Access",
            "id": 19,
            "display": 5,
            "type": 1,
            "description": null,
            "value": 0
        },
        {
            "name": "Has Internationalisation Support",
            "id": 27,
            "display": 4,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Iframe Long Description",
            "id": 5,
            "display": 4,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Markdown Long Description",
            "id": 4,
            "display": 4,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Certified Bot Vanity URLs",
            "id": 18,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Custom Bot Donate Link",
            "id": 14,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Custom Bot Invite Link",
            "id": 7,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Vanity URLs for all",
            "id": 20,
            "display": 3,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Voting Data Exposed",
            "id": 16,
            "display": 2,
            "type": 1,
            "description": null,
            "value": 0
        },
        {
            "name": "Additional Bot Owners/Editors",
            "id": 17,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Has Certification Program",
            "id": 10,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 0
        },
        {
            "name": "Has Widget",
            "id": 22,
            "display": 0,
            "type": 0,
            "description": null,
            "value": 0
        }
    ]
}
```
</details>

### `getBotLists(String)`
**Returns**: JSONObject

Retrieves the API information of all bot lists (including defunct ones).

### `getBotListFeatures(String, Site|String)`
**Returns**: JSONArray

Retrieves the features of a specific bot list.  
The listed features can be positive but also negative.

<details>
  <summary>Example JSON (Click to open/close)</summary>

```json
[
    {
        "name": "Offers Paid Promotion",
        "id": 21,
        "display": 5,
        "type": 1,
        "description": null,
        "value": 1
    },
    {
        "name": "HTML Long Description",
        "id": 6,
        "display": 4,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Custom Bot GitHub Link",
        "id": 15,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Custom Bot Support Link",
        "id": 8,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Custom Bot Website Link",
        "id": 13,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Discord Bot Support Link",
        "id": 12,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Has Voting",
        "id": 2,
        "display": 2,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Votes sent to Webhooks",
        "id": 24,
        "display": 2,
        "type": 1,
        "description": null,
        "value": 1
    },
    {
        "name": "Has Categories or Tags",
        "id": 9,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Has Mobile Support",
        "id": 26,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Has Search",
        "id": 23,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Requires Owner in Server",
        "id": 25,
        "display": 0,
        "type": 1,
        "description": null,
        "value": 1
    },
    {
        "name": "Server Count API",
        "id": 3,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 1
    },
    {
        "name": "Has Ads on Site",
        "id": 11,
        "display": 5,
        "type": 1,
        "description": null,
        "value": 0
    },
    {
        "name": "Paid Access",
        "id": 19,
        "display": 5,
        "type": 1,
        "description": null,
        "value": 0
    },
    {
        "name": "Has Internationalisation Support",
        "id": 27,
        "display": 4,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Iframe Long Description",
        "id": 5,
        "display": 4,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Markdown Long Description",
        "id": 4,
        "display": 4,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Certified Bot Vanity URLs",
        "id": 18,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Custom Bot Donate Link",
        "id": 14,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Custom Bot Invite Link",
        "id": 7,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Vanity URLs for all",
        "id": 20,
        "display": 3,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Voting Data Exposed",
        "id": 16,
        "display": 2,
        "type": 1,
        "description": null,
        "value": 0
    },
    {
        "name": "Additional Bot Owners/Editors",
        "id": 17,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Has Certification Program",
        "id": 10,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 0
    },
    {
        "name": "Has Widget",
        "id": 22,
        "display": 0,
        "type": 0,
        "description": null,
        "value": 0
    }
]
```
</details>