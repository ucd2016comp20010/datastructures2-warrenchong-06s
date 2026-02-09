2. Write the pseudocode for an algorithm which implements a Queue using two stacks.
   Provide implementations for the enqueue() and dequeue() methods.

Stack inputStack;
Stack outputStack;

enqueue() {
    inputStack.push();
}

deqeue(){

if (outputStack.isEmpty()){

while (!inputStack.isEmpty())
{

outputStack.push(inputStack.pop())

}

return outputStack.pop()


3. Write the pseudocode algorithm which reverses the elements on a Stack using two addi-
   tional Stack's (no other data structures are allowed).

public void reverse(){

Stack original;

Stack transfer;

while (!original.isEmpty())
{

transfer.push(original.pop());

}