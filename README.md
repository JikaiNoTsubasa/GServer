# GServer
Game server holding status of current games and more
# Setup
You can run the jar file for the first time with only a folder called *conf* aside. It will create a basic config file inside if not present.
# REST Calls
## Create a game
In order to create a new game, execute the following rest call:
> http://hostname:port/context?action=create&id=12345&ip=192.168.0.1&desc=MyGame

Create games are only online, it means that if the program is shutdown, the game list is not saved.

* hostname: the server on which the program is run
* port: the port specified by the program (default: 50000)
* context: the application context specified in the config file conf/server.xml
* action: [create|delete|update] specify the action to do with the parameters
* id: a unique id from your application, each new game must have a unique id
* ip: the hostname or ip of the game's host
* desc: a description or name of the created game

### Example
> http://server1:50000/mygame?action=create&id=ABX-546-TRE&ip=192.168.0.1&desc=JoJpGame

## List all games
In order to list all the created games, execute the following rest call:
> http://hostname:port/context?action=get

It will list all games on each lines as json, for example:
{"id":"1234","name":"JikaiGame","hostname":"localhost","creationDate":1625229298935,"currentPlayers":1}

## Delete a game
In order to delete a game, execute the following rest call:
> http://hostname:port/context?action=delete&id=12345

It will delete a game with the id 12345