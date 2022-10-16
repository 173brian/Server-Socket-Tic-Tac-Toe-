*Server/Client Tic-Tac-Toe
A way to play Tic-Tac-Toe with friends wirelessly.

** Program overview
This is a tic-tac-toe game that is capable of being hosted over a local wi-fi network and connected to by the first user to claim the port 5000 connection.

*** Server Module
The server module hosts the ServerSocket connection over the localhost at port 5000, and interfaces with the Tic-Tac-Toe class.

*** Client Module
The client module connects to the ServerSocket over the localhost at port 5000 and interprets the packet information that is sent to it.

*** Notes About the Program and Plans for Expansion
This program is set up in a system that listens for input from the client/server, and then sends input to the client/server. This loop is repeated over and over in a rigid format. Because of that visusal "errors" can be found in the program, even though they don't affect the functionality. A great way to expand on this program would be to create an interface with a timeout method for the reading and sending information that would enable for the client to refresh information from the server without submitting a text entry.

*** Video Tutorial
https://youtu.be/5MUfx5pemi4