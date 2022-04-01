# Sumto
Tusom / Wordle / Motus and other games like that solver

# ⚠️ Note that the only supported language is French atm

## How can i help to add a dictionary ?
1) Fork the repo
2) Find multiple dictionaries (in raw content and one word per line) of your language
3) Put all of them in `a.dico` in the folder `test_room`
4) Run the class `DicFilter.java` in `src/test/java`
5) Copy `b.dico` in the folder `test_room` to `YOUR_LANG.dico` in the folder `dico`
6) In the class `me.uwu.sumto.dico.DicoLang` add your language to the enum like `COOL("Cool language", "YOUR_LANG", "cl");` the 2nd parametter must be the name of the .dico file
7) Make a pull request

## How to run ?
1) Install java  
2) Download the latest version [here](https://github.com/UwUDev/Sumto/releases)
3) Run `java -jar sumto.jar`  

## How to use ?
1) Enter word length
2) Some games such as tusmo will force you to start with a specific letter that you can set
3) Type the word that the bot will tell you to try
4) Tell the bot the answer  
`r` = the letter is a the good place  
`y` = the letter exist but it's not at the good place  
`-` = this letter isn't needed  
If you have a 7 letters word you should type somethig like that `rr--yy-`  
Note that if the game doesn't recognise the word you can type `skip` instead of the answer
5) Repeat step 3 and 4
