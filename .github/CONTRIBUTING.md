[issues]: https://github.com/botblock/JavaBotBlockAPI/issues
[Code of Conduct]: https://github.com/botblock/JavaBotBlockAPI/blob/master/CODE_OF_CONDUCT.md
[BotBlock API]: https://botblock.org/api/docs

# Contributing Guidelines
We welcome everyone to contribute to the JavaBotBlockAPI project.  
But to keep a certain order and style over the project do you have to follow those guidelines here. But don't worry! They're fairly simple.

# Issues
If you encounter problems with the API feel free to open an issue on the [issues] tab.  
But before doing so make sure to check for the following things.

## Using latest version
We do not support old versions of this API. We release new versions to fix issues, update dependencies or add new methods.  
So if you encounter an issue or want a feature to be added, make sure to use the latest version as it might contain new functions and/or bugfixes.

## Make sure it is not an issue of external (3rd party) libraries
JavaBotBlockAPI uses different 3rd party libraries such as JDA and also depends on the [BotBlock API].  
This means that some errors you encounter could be the cause of said 3rd party libraries or sites not working as intended.  
We can't fix issues related to those. If you encounter errors with those, go to the respective repository/site and report the issue.

## Issue doesn't already exist
Before creating an issue, make sure that you checked the issues page for any existing issue of the same type.  
If one exists, comment on it and provide additional informations.  
The best way for us to fix errors and issues is by gathering informations to find out why those issues happen. And having multiple issues complaining about the same issue won't help anyone here and only slows the process of fixing it down.

## Follow the templates
We have templates in place to make sure we receive the required informations.  
Please follow those templates or else your issue might get ignored and closed.

----
# Pull Requests
We accept pull requests for fixing issues or adding new features, but you have to follow those rules.

## Javadocs
Please add javadocs comments to **all public methods the developer has access to and should use.**  
Javadocs help developers to see what methods they can/should use and what those do.

When adding Javadoc comments, follow this Styling choises.

### Line breaks and paragraphs
Start a new line with `<br>` (Don't close this tag).  
Do the same for new paragraphs, but keep an empty line in between the text (And of course use `<p>` instead).

**Example**:
```java
/**
 * This is a small summary of some method.
 * <br>It uses line breaks like those.
 *
 * <p>But also new paragraphs are used.
 */
```

### Links
Please always use the full path to a class/method when using `{@link}`

Bad example: `{@link BotBlockAPI}`  
Good example: `{@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI}`

We want to point out the alternative text used in the Good example, to display "BotBlockAPI" instead of the path.  
When linking to a method that is in a separate class, set the alternative text to `Class.method(option(s))`.

If the method you link to is within the same class, use `{@link #methodName() methodName()}`.

Linking to external Javadoc may be set with the same linking-rules.  
New external javadocs may need to be added to the `javadoc` task in the `build.gradle`.

**Note**:  
Use the `<a href="">` option to link external pages. When doing so also remember to include `target="_blank"`.  
A link could look like this: `<a href="https://google.com" target="_blank">Google it!</a>` (You have to close the a tag.)

### param styling
When the method has parameters, you have to set a description with the `@param` option.  
Note that the description has to be on a new line.

**Example**:
```java
/**
 * @param foo
 *        The param description.
 */
```

### Since
Since annotations are used to mention since what version a method/class is available.  
The syntax is `@since v{major}.{minor}.{build}` where `{major}` is only changed on huge changes (Entire recodes f.e.), `{minor}` is updated on bigger changes of classes and similar and `{build}` is changed on every other noteworthy change.

In most cases will you only update `{build}` and nothing else. If you're unsure if you can/should update the version, ask a maintainer of this repository for assistance.

Please **do not change already existing since annotations**. This also includes adding ones to already existing methods/classes.

### Deprecated methods
> Please also see the [Deprecation Policy](#deprecation-policy) about how to handle deprecations

If you're deprecating a method will you need to add the `@Deprecated` annotation and also include a `@deprecated` tag in the comment explain why it was deprecated and mention possible replacements.

You also need to add the `@DeprecatedSince` annotation to indicate since when a method is deprecated. This annotation has the fields `major`, `minor`, `patch` and `replacement` from which the last one is optional.  
You may also add the `@PlannedRemoval` annotation if the object is planned to be removed in a future release. This annotation has a `major`, `minor` and `patch` field to mark the version for when the object is removed.

### CheckUtil
JavaBotBlockAPI has a CheckUtil with some static void methods for checking various things such as if a String is empty.

When using any methods of the CheckUtil should you add the following text to the Javadoc comment as its own Paragraph:  
```java
/**
 * <p>Following Exceptions can be thrown from the {@link org.botblock.javabotblockapi.core.CheckUtil CheckUtil}:
 * <ul>
 *     <li>{@link java.lang.NullPointerException NullPointerException} - Mention the cause here.</li>
 *     <li>{@link java.lang.IllegalStateException IllegalStateException} - Mention the cause here.</li>
 * </ul>
 */
```

The CheckUtil has the following methods which can have the mentioned Excpetions:

- `ChekUtil#notEmpty(String value, String name)` - Checks the String "value" is empty and throws a NullPointerException with the message `name + " may not be empty."` in such a case.
- `CheckUtil#notEmpty(Map<?, ?> value, String name)` - Checks if the Map "value" is empty and throws a NullPointerException with the message `name + " may not be empty."` in such a case.
- `CheckUtil#condition(boolean expression, String message)` - Checks if the boolean "expression" returns true and throws a IllegalStateException with the message `message`.

### Other Styling
Please make sure that all text is on the same vertical line (block).  
This means that when f.e. a `@return` is used, that you have to use two spaces after `@param` instead of one.

### Order of the parts
Here is an example of using the different parts together:  
```java
/**
 * Adds "Bar" to the provided text.
 * <br>Will throw an error when not providing text.
 *
 * <p>Following Exceptions can be thrown from the {@link org.botblock.javabotblockapi.core.CheckUtil CheckUtil}:
 * <ul>
 *     <li>{@link java.lang.NullPointerException NullPointerException} - When foo is empty.</li>
 * </ul>
 * 
 * @param  foo
 *         The text that should get "Bar" added to it.
 *
 * @return The provided String with "Bar" added to it.
 *
 * @since  1.0.0
 *
 * @deprecated Use {@link #getFooBar() getFooBar()} instead.
 */
@Deprecated
@DeprecatedSince(major = 1, minor = 0, patch = 0, replacements = {"#getFooBar"}) // If you deprecate a method, add this one too.
@PlannedRemoval(major = 1, minor = 0, patch = 2)
public String getFooBar(@Nonnull String foo){
    CheckUtil.notEmpty(foo, "Foo");
        
    return foo + "Bar";
}

/**
 * Returns "foobar".
 *
 * @return {@code "foobar"}
 *
 * @since  1.0.1
 */
public String getFooBar(){
    return "foobar";
}
```

## Deprecation Policy
To not fully break the Wrapper on each release do we follow a general deprecation policy, to give people time to move to any replacement method, if available.

If possible should always a replacement method, field or other object be provided when deprecating an object.  
Any deprecated method is also planned for removal which is indicated by the `@PlannedRemoval` annotation. The version you define there has to be at least two patch-versions from the one the object became deprecated.  
This means that deprecating a method in `6.2.0` results in the major, minor and patch version of the annotation to display `6`, `2` and `2` respectively.

At **no point** should an object just be removed. We always mark it as deprecated first and give the aforementioned minimal delay for people to update, before removing it completely.

## [Code of Conduct]
We want to give everyone a chance to contribute to the project.  
So please keep your comments and messages nice. Every message that is considered racist, insulting or similar will be removed.  
If you continue to send those messages will we permanently remove you from this repository.
