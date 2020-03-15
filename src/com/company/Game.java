package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Game {
    private String alphabet;
    private String fileName;
    private HashSet<String> wordsDictionary;
    private int[] wordsLengths;
     private int maxLengthOfWords;
    private ArrayList< ArrayList<String>> words;
    private ArrayList< ArrayList<String>> sortedWords;
    public Game(String _fileName, String _alphabet, int[] _wordsLengths) {
        alphabet = replaceChar(_alphabet);
        fileName = _fileName;
        wordsLengths = _wordsLengths;
        wordsDictionary  = new HashSet<>();
        words = new ArrayList<>();
        sortedWords = new ArrayList<>();
        readFile();
        maxLengthOfWordsInDictionary();
        sortWords();
        calculateWords();
        //clearIncorrectWordsInDictionary();
    }

    public Game (String _fileName, String _alphabet, int _wordLength) {
        alphabet = replaceChar(_alphabet);
        fileName = _fileName;
        wordsLengths = new int[1];
        wordsLengths[0] = _wordLength;
        wordsDictionary  = new HashSet<>();
        words = new ArrayList<>();
        sortedWords = new ArrayList<>();
        readFile();
        maxLengthOfWordsInDictionary();
        sortWords();
        calculateWordsByNumber();
        //clearIncorrectWordsInDictionary();
    }

    static int inputWordLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите длинну слова ");
        return scanner.nextInt();
    }

    private int numberOfMatchesChar(String word, String ch) {
        return word.length() - word.replace(ch, "").length();
    }

    private boolean wordIsInDictionary(String word) {
        boolean yes = true;
        for (char c : word.toCharArray()) {
            if(numberOfMatchesChar(word,Character.toString(c))<=numberOfMatchesChar(alphabet,Character.toString(c)) &&
                    numberOfMatchesChar(alphabet,Character.toString(c)) > 0)
                yes = true;
            else
                return false;
        }
        return yes;
    }

    private void clearIncorrectWords() {
        for (ArrayList<String> word : words) {
            for (int j = 0; j < word.size(); j++) {
                if (!wordIsInDictionary(word.get(j))) {
                    word.remove(j);
                    j--;
                }
            }
        }
    }

    private void readFile() {
       BufferedReader reader;
       try {
           reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"windows-1251"));
           String line = reader.readLine();
           wordsDictionary.add(replaceChar(line));
           while (line != null) {
               // System.out.println(line);
               line = reader.readLine();
               wordsDictionary.add(replaceChar(line));
           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
           System.out.println("Файл не найден по имени "+ fileName);
       } catch (IOException e) {
           e.printStackTrace();
       }
        wordsDictionary.remove(null);
    }

    private String replaceChar(String word) {
        try {
            if (word.contains("ё")) {
                //System.out.println(word + " " + word.replace("ё", "е"));
                return word.replace("ё", "е");
            } else
                return word;
        }
        catch (NullPointerException e) {
            return word;
        }
    }

    // Метод ввода букв словаря
    public static String inputDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите буквы словаря в одно слово: ");
        return scanner.nextLine().toLowerCase();
    }

    public static int[] inputWordsLengths() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество угадываемых слов: ");
        int Count = scanner.nextInt();
        int [] _wordsLengths = new int[Count];
        for (int i = 0; i < Count; i++){
            System.out.println("Сколько букв в слове номер " + (i+1));
            _wordsLengths[i] = scanner.nextInt();
        }
        return _wordsLengths;
    }

    private void calculateWords() {
        for (int wordsLength : wordsLengths) {
            words.add(sortedWords.get(wordsLength-1));
        }
        clearIncorrectWords();
    }

    private void calculateWordsByNumber() {
        for (int i = 1; i <=wordsLengths[0]; i++) {
            words.add(sortedWords.get(i));
        }
        clearIncorrectWords();
    }

    private void maxLengthOfWordsInDictionary() {
        int max = 0;
        for (String word : wordsDictionary) {
            if(word.length() > max)
                max = word.length();

        }
        maxLengthOfWords = max;
    }

    public String[][] getWords() {
        String[][] arr = new String[maxCount(words)][words.size()];
        for (int i = 0; i < maxCount(words); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (i+1 <= words.get(j).size())
                    arr[i][j] = words.get(j).get(i);
            }
        }
        return arr;
    }

    private void sortWords() {
        for (int i = 1; i <= maxLengthOfWords; i++) {
            ArrayList<String> OneLengthWords = new ArrayList<>();
            for (String dictWord : wordsDictionary) {
                if(dictWord.length() == i)
                    OneLengthWords.add(dictWord);
            }
            sortedWords.add(OneLengthWords);
        }
    }

    // Создает строку из пробелов длиной %Count%
    private String TabGenerator(int Count) {
        return " ".repeat(Math.max(0, Count));
    }

    // Находит максимальный размер списка слов одинаковой длинны
    private int maxCount(ArrayList<ArrayList<String>> listOfList){
        int max = 0;
        for (ArrayList<String> arr:listOfList
        ) {
            if(arr.size() > max)
                max = arr.size();
        }
        return max;
    }

    public void print(boolean isAnswer) {
        if (isAnswer)
            printAllWordsTable(words);
        else
            printAllWordsTable(sortedWords);
    }

    // Печатает все слова в несколько столбцов
    private void printAllWordsTable(ArrayList<ArrayList<String>> listOfList) {
        for (int i = 0; i < maxCount(listOfList); i++) {
            for (int j = 0; j < listOfList.size(); j++) {


                if (i+1 <= listOfList.get(j).size())
                    System.out.print(listOfList.get(j).get(i) + " ");
                else
                    System.out.print(TabGenerator(j+3));
            }
            System.out.println();
        }
    }

}
