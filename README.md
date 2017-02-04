# Agadar's Java Wrapper for the NationStates API.

This Java library provides programmers with an easy way to communicate with the NationStates API. 

It supports the following basic functionalities:
* Retrieving information about Nations, Regions, the World Assembly, and the World;
* Downloading and reading daily data dumps;
* Sending telegrams;
* Verifying users.

On top of the basic functionalities, this wrapper also ensures the mandated rate limits are not violated, and gives warnings when
the wrapper itself is outdated, so that you don't have to worry about any of those things.

## Usage

### Basics

Setting a User Agent is the first thing you'll have to do, ensuring NationStates admins can identify your script:

```
NationStates.setUserAgent("Example Nation's Script (example@example.com)");
```

That's all you need to do to be able to use the wrapper! Now we can, say, retrieve the national animal name of a nation:
```
Nation nation = NationStates.nation("agadar").shards(NationShard.ANIMAL).execute();
```

This gives us a Nation object holding the animal name. That's all there is to it! All queries are build and executed in a similar 
fashion.

### Custom return types

The 'Nation' class and other such domain classes have little to no methods: they exist solely to hold the information you request 
from the NationStates API. If you want to add methods, then you can inherit the domain classes or create entirely seperate ones and tell the wrapper to use your
custom domain classes instead.

For example, imagine we create a class 'CustomNation' that inherits 'Nation' but which holds some handy methods not natively present in the 'Nation' class. We then first 
register our custom class so that it is recognized:

```
XmlConverter.registerTypes(CustomNation.class);
```

Now whenever we want a Nation-query to return our custom type, we can use the overloaded execute()-function, like so:

```
CustomNation nation = NationStates.nation("agadar").shards(NationShard.ANIMAL).execute(CustomNation.class);
```

And voilà: we now have an instance of our custom class, holding the retrieved animal name.

## Links

[NationStates forum thread](http://forum.nationstates.net/viewtopic.php?f=15&t=383518)

[Official NationStates website](http://www.nationstates.net/)

[NationStates API documentation](https://www.nationstates.net/pages/api.html)
