	6. What is the difference between a singly linked list and a circularly linked list?
	A singly linked list has a definitive start node, and cannot traverse on any previously visited nodes
	
	A circularly linked list does not have a definitive start node, instead a definitive tail node which is used to define that an iteration of the list has been passed, allowing for traversal on previously visited nodes
	
	7. In what situations would you prefer to use a linked list to an array?
	An application which has to deal with an unknown number of
	objects would benefit from a linked list, as you would only need to pointers for the current position and the destination, for easy insertion
	
	8. Describe 2 possible use-cases for a circularly linked list (2-3 sentences for each)
	
	Music players implement playlists as circular lists so songs loop continuously. When you reach the last song, rotate() or traversal naturally returns to the first song without null checks. 
	
	Turn-based games like board games or card games use circular linked lists to manage player rotations. Each player is a node, and after a player's turn ends, the system advances the tail pointer to the next player automatically.
	
	
