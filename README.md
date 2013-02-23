Battleship Game
===============
<p align="center"> <img src="imagens/battleship_window.png"/> </p>

##About
Implement the Battleships Game using sockets as communication. One of the users is the server connection and the other the client.
Users can also exchange messages through a chat.

##Start the game
Start the game the user must choose to start as server or client. The user can start the server or client accessing the menu Options.
<p align="center"> <img src="imagens/options.png"/> </p>

If the user start as server socket connection will open for 30 seconds, waiting for a connection. If the timeout for connection exceeded 
is necessary to start the server again. When the server is opened and the timeout for connection exceeded a message is displayed 
informing the user what is happening.
<p align="center"> <img src="imagens/open_server.png"/> </p>

If the user start as client socket connection is necessary enter the server host.
<p align="center"> <img src="imagens/start_client.png"/> </p>

When the connection is successfully a message is displayed to both players saying that the connection was successful.
<p align="center"> <img src="imagens/connected_successfully.png"/> </p>

Each player can play three times in a game. When the player hits an opponent's piece this piece change color for red, if you miss the 
opponent's piece this piece change color for blue. Each movement was a message informing that the piece was hit, is also informed if 
the arm was sunk.
<p align="center"> <img src="imagens/game.png"/> </p>

When a player wins the game a popup informs that he won the match, a popup also informs the loser that he lost the match.
<p align="center"> <img src="imagens/messagens_final_game.png"/> </p>

##Build status
Last build in Travis continuous integration  
[![Build Status](https://travis-ci.org/marcuspimenta/Battleship-Game.png?branch=master)](https://travis-ci.org/marcuspimenta/Battleship-Game)

##Download
You can download it in the .jar format:  
[last version](https://raw.github.com/marcuspimenta/Battleship-Game/master/build/battleship.jar)

##Author
Marcus Vinícius Pimenta  
email: [mvinicius.pimenta@gmail.com](mailto:mvinicius.pimenta@gmail.com)
