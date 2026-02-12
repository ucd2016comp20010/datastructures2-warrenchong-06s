(g)
What happens if you change
positions() to call preorder()?

positions() on inorder():

[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]

This version seems to go to the deepest point, before moving to its parent node, before going to the other child of that branch, before moving out the parent branch of its parent and then moving to the sibling of that branch and so on and so forth.

positions() on preorder():

[1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 7]

To Me, the program seems to go into the first branch, then the first option of the second branch and there on and so forth until it reaches a point of no return, then it moves to the previous branch that it did not explore before exploring that to its deepest point, before repeating the process until there are no more branches left to explore

(h) The height of the tree is 5
I would expect that given the complexit of the algorithm is o(n), that it would take the amount of calls as there are elements in the tree. 