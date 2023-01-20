# The checkers


A console application written in Java using the simplest mechanisms of the language. It is a checkers game simulator based on bit operations. When creating a game, I have only two variables of the long type, whose internal structure for each of the game elements is described by its own set of bits (bx) specified as below:
1. b2,b1,b0 - X coordinate on the board,
2. b5,b4,b3 - Y coordinate on the board,
3. b6 - color (0 - black, 1 - white),
4. b7 - piece (0-pawn, 1-king),
5. b8 -state(0-captured,1-played).

A drawing of the organization of a single long type variable:
<img width="857" alt="Zrzut ekranu 2022-09-4 o 13 24 59" src="https://user-images.githubusercontent.com/110846802/188310863-3654da4b-c0be-48e5-990b-f28a40c50a1d.png">

The appearance of the board and pieces is presented using unicode characters:
1. 2B1B - white field,
2. 2B1C - black field,
3. 2659 - white pawn,
4. 265F - black riser,
5. 2655 - white lady,
6. 265B - black lady,

<img width="427" alt="Zrzut ekranu 2022-09-4 o 12 45 43" src="https://user-images.githubusercontent.com/110846802/188310866-d78c5380-8130-4a09-a4cf-a7f81f0cf68a.png">
