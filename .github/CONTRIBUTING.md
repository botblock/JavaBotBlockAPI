[issues]: https://github.com/botblock/JavaBotBlockAPI/issues
[Code of Conduct]: ./CODE_OF_CONDUCT.md

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

When adding Javadoc comments, follow this template:  
```java
/**
 * Short summary of what the method does. 
 * <br>Start new lines with a {@code <br>}
 *
 * @param  bar
 *         Set the description in the new line.
 *
 * @return What it returns. (Note to keep the param name, description and return value on same vertical line)
 *
 * @since vNew.version.here
 */
public String myMethod(String bar){
    return "foo" + bar;
}
```

## [Code of Conduct]
We want to give everyone a chance to contribute to the project.  
So please keep your comments and messages nice. Every message that is considered rasist, insulting or similar will be removed.  
If you continue to send those messages you'll be permanently removed from this repository.
