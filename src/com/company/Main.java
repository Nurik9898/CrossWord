package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        /*int dictLength = 2;
        int wordLength = 2;
        Game game = new Game(Game.inputDictionary(),Game.inputWordsLengths());
        game.CalculateWords();
        game.printAllWordsTable();
        System.out.println(game.maxValue + " количество слов");
        System.out.println(game.maxCount() + " строк");
        System.out.println((Game.afterTime-Game.beforeTime)+ " миллисекунд работа функции");*/
        //GameReader g = new GameReader("C:\\Users\\Ильнур\\IdeaProjects\\CrossWord\\src\\com\\company\\Dictionary.txt",GameReader.inputDictionary(), GameReader.inputWordsLengths());
        //Game g = new Game("C:\\Users\\Ильнур\\IdeaProjects\\CrossWord\\src\\com\\company\\Dictionary.txt",Game.inputDictionary(), Game.inputWordLength());
        //g.print(true);
        //g.print(false);

            Window app = new Window("Кроссворд");
            app.setVisible(true);
            app.pack();



    }
}
