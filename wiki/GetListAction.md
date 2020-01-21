The GetListAction allows to perform GET requests towards the `/api/lists` and `/api/lists/:id` endpoint of the BotBlock API.

> ### Note
> All requests are getting cached for 2 minutes to prevent possible rate limits.  
> If you want to disable this, use [`GetListAction(true)`](#disable-caching).  
> **We do not recommend doing this without any caching or rate limit-system in place!**

## Index
- [Disable caching](#disable-caching)
- [Methods](#methods)
  - [`getApiField(String, Site|String, ApiField)`](#getapifieldstring-sitestring-apifield)
  - [`getBotWidget(String, Site|String)`](#getbotwidgetstring-sitestring)
  - [`getDescription(String, Site|String)`](#getdescriptionstring-sitestring)
  - [`getDiscordInvite(String, Site|String)`](#getdiscordinvitestring-sitestring)
  - [`getFeatures(String, Site|String)`](#getfeaturesstring-sitestring)
  - [`getFilteredLists(String)`](#getfilteredlistsstring)
  - [`getIcon(String, Site|String)`](#geticonstring-sitestring)
  - [`getId(String, Site|String)`](#getidstring-sitestring)
  - [`getLanguage(String, Site|String)`](#getlanguagestring-sitestring)
  - [`getList(String, Site|String)`](#getliststring-sitestring)
  - [`getLists(String)`](#getlistsstring)
  - [`getName(String, Site|String)`](#getnamestring-sitestring)
  - [`getOwners(String, Site|String)`](#getownersstring-sitestring)
  - [`getTimeAdded(String, Site|String)`](#gettimeaddedstring-sitestring)
  - [`getUrl(String, Site|String)`](#geturlstring-sitestring)
  - [`isDefunct(String, Site|String)`](#isdefunctstring-sitestring)
  - [`isDiscordOnly(String, Site|String)`](#isdiscordonlystring-sitestring)

## Disable caching
If you want to disable caching, will you need to add a boolean to the `GetListAction` constructor like this:  
```java
// "true" disables the internal caching.
GetListAction getListAction = new GetListAction(true);
```

## Methods

### `getApiField(String, Site|String, ApiField)`
**Returns**: Possibly-null String

Gets one of the JSON fields that are prefixed with `api_`.  
The returned String can either be text (i.e. name of JSON field for POST requests), a URL (i.e. to documentation) or null.

### `getBotWidget(String, Site|String)`
**Returns**: Possibly-null String

Gets the link for displaying a bot widget (image with various information of the bot from the bot list). This may return null if no widget link was set.

### `getDescription(String, Site|String)`
**Returns**: Possibly-null String

Gets the description (tag line) of the bot list. This may return null if no description is set.

### `getDiscordInvite(String, Site|String)`
**Returns**: Possibly-null String

Gets the Discord invite of the bot list. This may return null if no invite was set.

### `getFeatures(String, Site|String)`
**Returns**: Possibly-empty JSONArray

Gets a JSONArray containing all the positive and negative features. This may return an empty JSONArray if no features are set.  
An entry of the JSONArray may look like this:  
```json
{
    "name": "Displayed name of feature",
    "id": -1,
    "display": -1,
    "type": 0,
    "description": null,
    "value": 1
}
```

### `getFilteredList(String)`
**Returns**: JSONObject

Gets the lists, but with only the [API fields](#getapifieldstring-sitestring-apifield) present.

### `getIcon(String, Site|String)`
**Returns**: Possibly-null String

Gets the URL to the Bot list-icon. This may return null if no icon was set.

### `getId(String, Site|String)`
**Returns**: String

Gets the id of a bot list. This is **not** a numerical id (id from Discord) but what is used by BotBlock for things like the POST API.

### `getLanguage(String, Site|String)`
**Returns**: String

Gets the primary language of the bot list.

### `getList(String, Site|String)`
**Returns**: JSONObject

Gets the full info of a bot list.

### `getLists(String)`
**Returns**: JSONObject

Gets all available bot lists, including those that are [defunct](#isdefunctstring-sitestring)

### `getName(String, Site|String)`
**Returns**: String

Gets the displayed name of the bot list.

### `getOwners(String, Site|String)`
**Returns**: Possibly-null String

Gets the owners of a bot list. The returned String is in the format `<name>#<discrim> (<id>), <name>#<discrim> (<id>), ...`  
This may return null if no owners are set.

### `getTimeAdded(String, Site|String)`
**Returns**: Integer

Gets the date and time (UNIX timestamp) of when the bot list was added to BotBlock.

### `getUrl(String, Site|String)`
**Returns**: String

Gets the URL of the bot list.

### `isDefunct(String, Site|String)`
**Returns**: Boolean

Returns `true` when the bot list is defunct and `false` otherwise.  
A defunct site is not usable by the API of BotBlock, but can still be found on the website and accessed through the `/api/lists/:id` endpoint.

### `isDiscordOnly(String, Site|String)`
**Returns**: Boolean

Returns `true` when the bot list is only for Discord bots and `false` otherwise.