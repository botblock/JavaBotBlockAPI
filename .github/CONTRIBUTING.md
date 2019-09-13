[issues]: https://github.com/botblock/JavaBotBlockAPI/issues
[Code of Conduct]: ./CODE_OF_CONDUCT.md
[BotBlock API]: https://botblock.org/api/docs

## Contributing Guidelines
We welcome everyone to contribute to the JavaBotBlockAPI project.  
But to keep a certain order and style over the project do you have to follow those guidelines here. But don't worry! They're fairly simple.

## Issues
If you encounter problems with the API feel free to open an issue on the [issues] tab.  
But before doing so make sure to check for the following things.

### Using latest version
We do not support old versions of this API. We release new versions to fix issues, update dependencies or add new methods.  
So if you encounter an issue or want a feature to be added, make sure to use the latest version as it might contain new functions and/or bugfixes.

### Make sure it is not an issue of external (3rd party) libraries
JavaBotBlockAPI uses different 3rd party libraries such as JDA and also depends on the [BotBlock API].  
This means that some errors you encounter could be the cause of said 3rd party libraries or sites not working as intended.  
We can't fix issues related to those. If you encounter errors with those, go to the respective repository/site and report the issue.

### Issue doesn't already exist
Instead of creating a new issue explaining the same thing as the other one does, better go to the issue and comment on it to proof that it happens on multiple platforms.  
The best way for us to fix errors and issues is by gathering informations to find out why those issues happen. And having multiple issues complaining about the same issue won't help anyone here and only slows the process of fixing it down.

### Follow the templates
We have templates in place to make sure we receive the required informations.  
Please follow those templates or else your issue might get ignored and closed.

----
## Pull Requests
We accept pull requests for fixing issues or adding new features, but you have to follow those rules.

### Javadocs
Please add javadocs comments to **all public methods the developer has access to.**  
Javadocs help developers to see what methods they can/should use and what those do.

When adding Javadoc comments, follow this Styling choises.

#### Line breaks and paragraphs
When making a new line, start the new line with `<br>` and __don't__ close it.  
For paragraphs, keep an empty line in between and then start the new line with a `<p>` (Also don't close it)

**Example**:
```java
/**
 * This is a small summary of some method.
 * <br>It uses line breaks like those.
 *
 * <p>But also new paragraphs are used.
 */
```

#### Links
When linking to other classes or methods, use the full path to it in the `{@link}` option.

Bad example: `{@link BotBlockAPI}`  
Good example: `{@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI}`

Note the alternative argument used in the good example.  
A special case is used when linking to a method within the same class. In this case just link the method using #.  
Example: `{@link #myMethod}`

**Note**:  
Use the `<a href="">` option to link external pages. When doing so also remember to include `target="_blank"`.  
A link could look like this: `<a href="https://google.com" target="_blank">Google it!</a>` (You have to close it.)

#### param styling
When the method has parameters, you have to set a description with the `@param` option.  
Note that the description has to be on a new line.

**Example**:
```java
/**
 * @param foo
 *        The param description.
 */
```

#### Since
When adding new methods to the API, will you need to add a `@since` annotation at the end containing the new version.  
Usually do only big, breaking changes increase the minor number (`v{major}.{minor}.{build}`) so usually you only increase the build.

Please do NOT edit any already existing `@since` annotation.

#### Other Styling
Please make sure that all text is on the same vertical line (block).  
This means that when f.e. a `@return` is used, that you have to use two spaces after `@param` instead of one.

#### Example
Here is an example of a method with all parts:  
```java
/**
 * Adds "Bar" to the provided text.
 * 
 * @param  foo
 *         The text that should get "Bar" added to it.
 *
 * @return The provided String with "Bar" added to it.
 *
 * @since v1.0.0
 */
public String getFooBar(String foo){
    return foo + "Bar";
}
```

## [Code of Conduct]
We want to give everyone a chance to contribute to the project.  
So please keep your comments and messages nice. Every message that is considered rasist, insulting or similar will be removed.  
If you continue to send those messages you'll be permanently removed from this repository.