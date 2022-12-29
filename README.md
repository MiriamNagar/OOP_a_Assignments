# Observer - GroupAdmin
In this project we built class GroupAdmin resposible for acting as an observable and observers that a are notified.
GroupAdmin contains a list of members to notify and alist of states that each time a state changes the members get notified.
Member is an interface with the method update, we created ConcreteMember cladd that implements said interface in order to act as a member being notified, the class contains a list of all the notifications sent from GroupAdmin.'

## Technologies
We used Java in the project.
We used Stack, ArrayList, Test, Logger and LoggerFactory libraries.

## Launch
To launch the program you nedd to create an object of GroupAdmin class and register Objects of ComcreteMember class into the GroupAdmin object, each time we change the state of the UdoableStringBUilder, CocreteMembers states list is updated.

## Examples of use
See Examples in the test Class.

## Tests
In GroupAdmin and ConcreteMember classes we added a few methods to have an easier time testing the classes, it is mentioned in the javadoc which methods those are.
Also, for each function in each class we made a different test.
