# Observer - GroupAdmin
In this project we built class GroupAdmin resposible for acting as an observable and observers that a are notified.
GroupAdmin contains a list of members to notify and alist of states that each time a state changes the members get notified.
Member is an interface with the method update, we created ConcreteMember cladd that implements said interface in order to act as a member being notified, the class contains a list of all the notifications sent from GroupAdmin.'

## Technologies
We used Java in the project.
We used Stack, ArrayList, Test, Logger and LoggerFactory libraries.

## Launch
To launch the program you need to create an object of GroupAdmin class and register Objects of ComcreteMember class into the GroupAdmin object, each time we change the state of the UdoableStringBUilder, CocreteMembers states list is updated.

## Examples of use
See Examples in the test Class.

## Tests
In GroupAdmin and ConcreteMember classes we added a few methods to have an easier time testing the classes, it is mentioned in the javadoc which methods those are.
Also, for each function in each class we made a different test.

## complexity
In this project we used ArrayList data structure to contain the members list and states list.
The reason we chose this Data Structure is because memory wise we saw that an ArrayList takes less memory than other data structures such as LinkedList and Hashmap
also, the two main methods we use most is adding a member, and adding a state to list both done in constant time complexity. also, the update function in ConcreteMember is also constant.
