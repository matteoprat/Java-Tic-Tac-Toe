# Java-Tic-Tac-Toe

An implementation of Tic Tac Toe with Java.

# Usage
After compiling the game you can start with no arguments to play the gui version, or you can start with -console for the System Console version.

# Why
Why I wrote this game? I recently started to use Java as programming language and I needed to put together the things I studied.
For this game I used a lot of elements of Java language such as:
- HashMap
- Sets
- ArrayList
- Enum
- Switch

I also made a kind of generic Game Logic that work with both GUI version and Console version, trying to avoid methods too specialized. Maybe I could improve that part.
It tooks me a couple of days to put all together, gathering documentation here and there when needed (expecially for the GUI library).
I also put comments (lots of comments) in the code and wrote class and methods documentation, so it will be easy to follow the logic I used.
I hope that it will be useful to other for learning purpose as it was for me. 

# Game
Almost forgot to talk about the game itself.
You have a 3x3 grid, you put X the AI will put O, if a diagonal, row or column contains 3 of the same symbol the game is over and the owner of that symbol will win. If all 9 spots in the grid are full and there is no row / colomun or diagonal with 3 of the same symbol, it is a Tie.
Beware, I made the AI reacting to your moves, it does not place random symbols, there's a logic behind, so you could lose.
