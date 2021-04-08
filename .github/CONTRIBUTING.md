<!-- Other URLs -->
[discord]: https://discord.gg/6dazXp6
[codemc]: https://ci.codemc.io

<!-- GitHub URLs -->
[issues]: https://github.com/botblock/JavaBotBlockAPI/issues
[feature]: https://github.com/botblock/JavaBotBlockAPI/issues/new?labels=Type%3A+Enhancement&template=feature_request.yml
[bug]: https://github.com/botblock/JavaBotBlockAPI/issues/new?labels=Type%3A+Bug+%28Unconfirmed%29&template=bug_report.yml
[discussion]: https://github.com/botblock/JavaBotBlockAPI/discussions
[checkutil]: https://github.com/botblock/JavaBotBlockAPI/blob/master/core/src/main/java/org/botblock/javabotblockapi/core/CheckUtil.java

# Contributing Guidelines
We welcome any kind of contributions to JavaBotBlockAPI but also expect some level of quality to be followed.

Please read this guide carefully before either submitting an issue or Pull request to not risk getting them closed without warning.

## Issues
Issues should be reported either on our [Discord Server][discord] or through our [Issue tracker][issues] depending on the type of issue.

### Feature requests
Feature requests should be made through the [Feature request template][feature] on our Issue tracker.  
Alternatively can you also create a [discussion] to suggest a change in the "Suggestions" category.

### Security issues
Issues regarding security should always be reported through our [Discord server][discord] since the Issue tracker isn't a save place to do this.  
On the Server, head over to the `#javabotblockapi` channel and inform `Andre_601#0601` about ths issue.

### Bug reports
Any other bug report should be made through the [Bug report template][bug] on our Issue tracker.

## Pull requests
Pull requests to improve JavaBotBlockAPI are always welcome as long as you follow these basic rules.

### Target Branch
We have different branches on this repository that all serve a different purpose.

#### master
The `master` branch is the main branch for new releases.  
Whenever enough changes have been accumulated on the [development branch](#development) will it be merged into master through a Pull request which then creates a new release on the Nexus Repository of [CodeMC].

Only Pull requests from the development branch are allowed to target the master branch and any other PR will be rejected.

#### development
The `development` branch is the go-to branch for all changes towards JavaBotBlockAPI.  
All changes, no matter if directly to project itself or to other parts such as the GitHub Action Workflow files are made on this branch any and Pull request should target it, no matter what.

#### gh-pages
The `gh-pages` branch is used to display the Javadoc at https://docs.botblock.org/JavaBotBlockAPI.  
This branch is only updated through a GitHub Action and any Pull request targeting this branch will be rejected.

### Javadoc formatting
We have specific styling Guides when it comes to Javadocs.  
A general rule is, that you have to document ANY method that is public and should be used by the end user in some way.

#### Line breaks and Paragraphs
New lines and paragraphs are made using the `<br>` and `<p>` tags.  
Both tags don't need to be closed and are always placed at the start of a line.

While the simple line break only needs to be on a new line will the paragraph have an empty line in-between.

*Example*:  
```java
/**
 * This is a description.
 * <br>This is a new line.
 * 
 * <p>This is a new paragraph.
 */
```

#### Linking
When linking to methods, Objects, Classes or similar should `{@link}` be used.

Always use the full path to a class, method, etc.  
Only exception is for methods within the same Class in which case you use `{@link #methodName() methodName()}`.

In all cases should you always add an alternative text to the link, even if it would be the exact same output as without it.

*Example*:  
```java
/**
 * Bad
 * {@link BotBlockAPI}
 * 
 * Also Bad (Missing Alternative Text)
 * {@link org.botblock.javabotblockapi.core.BotBlockAPI}
 * 
 * Good
 * {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI}
 */
```

`{@link}` is also used for linking to external dependencies such as Java, JDA or JSON-java.

When you want to link to external pages that aren't Javadocs should you use the `a` HTML-tag.  
When doing so, always close the tag (`</a>`) and include `target="_blank"`.

#### Parameters
Methods with parameters need to have their parameters documented using `@param`.

The description of the parameter is always on a new line right below the parameter name.

*Example*:  
```java
/**
 * @param foo
 *        Description goes here.
 */
```

#### Since
We use `@since` to mention since when a specific method, field or class is available.  
The mentioned version is always in the format `major.minor.patch`

Always add `@since` to newly added methods, classes and/or fields but never alter existing ones or add them to already existing methods, classes or fields.

#### Deprecation
Any deprecation follows a specific deprecation-policy we have.

Whenever you deprecate a method, object, field or similar will you need to add the `@Deprecated` annotation from Java as-well as the `@DeprecatedSince` and `@PlannedRemoval` annotations from us.  
The `@DeprecatedSince` annotation should have the major, minor and patch version mentioned since when the marked object is deprecated and, if available, also mention a replacement that should be used.  
`@PlannedRemoval` is used to mark when the object will be removed. The mentioned major, minor and patch version should always be at least 2 patches after the version of the deprecation. This means that a method deprecated in 6.6.0 would have a planned removal of 6.6.2 or higher.

Whenever an object is marked as deprecated should the Javadoc comment contain the `@deprecated` tag alongside a description explaining why it was deprecated and any mention of alternatives to use.

At no point should a object be removed without the aforementioned grace-period and setup.

#### CheckUtil
JavaBotBlockAPI utilizes a [CheckUtil] class to perform certain checks whenever a method is used.  
If you use methods of the CheckUtil are you required to add the following part into the Javadoc comment of the method:  
```java
/**
 * <p>Following Exceptions can be thrown from the CheckUtil:
 * <ul>
 *     <li>{@link java.lang.NullPointerException NullPointerException} - Mention the cause here</li>
 *     <li>{@link java.lang.IllegalStateException IllegalStateException} - Mention the cause here</li>
 * </ul>
 */
```

When using `notEmpty(String, String)` or `notEmpty(Map<?, ?>, String)` will you need to mention the NullPointerException and when using `condition(boolean, String)` will you need to mention the IllegalStateException.

Unless the [RequestHandler notice](#requesthandler) is used should this paragraph be the last section of the description (right before the param section).

#### RequestHandler
JavaBotBlockAPI performs HTTP requests through a [RequestHandler] in the request module.  
If you implement a method that uses methods from the RequestHandler will you need to add the following lines to the Javadoc of the method:  
```java
/**
 * <p>Following Exceptions can be thrown from the HTTP request:
 * <ul>
 *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
 *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RateLimitedException} - When the request got rate limited.</li>
 * </ul>
 */
```

This paragraph should always be the last section of the description.

#### Padding
`@param` names and descriptions, `@since` text and `@return` text should always be on the same vertical line.

If `@return` is used should `@param` and `@since` have 2 spaces instead of one.

#### Order
Here is a full example showing the order of all mentioned parts.

```java
/**
 * Returns "foobar".
 * 
 * @return "foobar"
 * 
 * @deprecated Use {@link #getFooBar(String) getFooBar(String)} instead.
 */
@Deprecated
@DeprecatedSince(major = 6, minor = 0, patch = 0, replacements = {"#getFooBar(String)"})
@PlannedRemoval(major = 6, minor = 0, patch = 2)
public String getFooBar(){
    return "foobar";
}

/**
 * Adds the provided String to "foo".
 * 
 * <p>Following Exceptions can be thrown from the CheckUtil:
 * <ul>
 *     <li>{@link java.lang.NullPointerException} - When the provided String is empty.</li>
 * </ul>
 * 
 * @param  bar
 *         The String to add to "foo".
 * 
 * @return "foo" with the provided String appended to it.
 * 
 * @since  6.0.0
 */
public String getFooBar(String bar){
    return "foo" + bar;
}
```

### Code Styling
The Code follows a basic styling Guide that you need to follow when making a Pull request.

#### spaces
There are no spaces before parantheses and braces.

*Example*:  
```java
// Wrong
public void doSomething (boolean yes) {
    if (yes) {
        System.out.println("Yes");
        return;
    }
    
    System.out.println("No");
}

// Right
public void doSomething(boolean yes){
    if(yes){
        System.out.println("Yes");
        return;
    }
    
    System.out.println("No");
}
```

#### Single-line If-Statement
Whenever a true if statement would result in a single line being executed should the braces be left away.

*Example*:
```java
// Wrong
public void doSomething(boolean yes){
    if(yes){
        System.out.println("Yes");
    }
    
    System.out.println("No");
}

// Right
public void doSomething(boolean yes){
    if(yes)
        System.out.println("Yes");
    
    System.out.println("No");
}
```

#### Parameter annotation
Non-primitive Parameters should always be annotated with `@Nonnull` or `@Nullable` depending on whether they can be null or not.

*Example*:
```java
// Wrong
public void doSomething(String text){
    if(text == null){
        System.out.println("No text provided!");
        return;
    }
    
    System.out.println("Provided Text: " + text);
}

// Right
public void doSomething(@Nullable String text){
    if(text == null){
        System.out.println("No text provided!");
        return;
    }
    
    System.out.println("Provided Text: " + text);
}
```

#### Empty Check for Nonnull annotated Objects
Whenever an Object is annotated as Nonnull (See [previous part](#parameter-annotation)) should you use the `notEmpty` methods from the [CheckUtil] to make sure the parameter is actually not null nor empty.

*Example*:
```java
// Wrong
public void doSomething(@Nonnull String text){
    System.out.println("Provided Text: " + text);
}

// Right
public void doSomething(@Nullable String text){
    CheckUtil.notEmpty(text, "Text");
    
    System.out.println("Provided Text: " + text);
}
```