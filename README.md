# Warcaby


Aplikacja konsolowa napisana w języku Java wykorzyystująca najprostsze mechanizmy języka. Jest to symulator gry w waracaby oparty o operacje bitowe. Tworząc grę dysponuję tylko dwoma zmiennymi typu long, których wewnętrzna struktura dla każdego z elementów gry jest opisana przez własny zestaw bitów (bx) wyspecyfikowany jak poniżej: 
• b2,b1,b0 - współrzędna X na planszy, 
• b5,b4,b3 - współrzędna Y na planszy, 
• b6 - kolor (0 - czarny, 1 - biały), 
• b7 -figura(0-pion,1-damka), 
• b8 -stan(0-zbity,1-wgrze).

Rysunek organizacji pojedyńczej zmiennej typu long:
<img width="857" alt="Zrzut ekranu 2022-09-4 o 13 24 59" src="https://user-images.githubusercontent.com/110846802/188310863-3654da4b-c0be-48e5-990b-f28a40c50a1d.png">

Wygląd planszy oraz pionów przedstawiony jest za pomocą znaków unicode: 
• 2B1B - białe pole, 
• 2B1C - czarne pole, 
• 2659 - biały pion, 
• 265F - czarny pion, 
• 2655 - biała dama, 
• 265B - czarna dama,

<img width="427" alt="Zrzut ekranu 2022-09-4 o 12 45 43" src="https://user-images.githubusercontent.com/110846802/188310866-d78c5380-8130-4a09-a4cf-a7f81f0cf68a.png">
