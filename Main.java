/****************************************************************
 *
 * File: Main.java
 * By: Edgar Catalan
 * Date: June 11, 2020
 *
 * Description: An interactive Dictionary with Data Structures such as Enum, ArrayList, and HashMaps.
 * Receives three inputs from the user. A keyword from the dictionary, part of speech, or the string "distinct"
 *
 ****************************************************************/
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

//enum cc
public class Main {
    public static final String ENTRYNOTFOUD = "<Not Found>";
    public static final String ENTRYNOTPARTOFSPEECH = "<2nd argument Not Part Of Speech or Distinct>";

    public enum Dictionary{
        Arrow("Arrow","noun","Here is your arrow <IMG> -=>> </IMG>"),
        Distinct_Adj1("Distinct", "adjective","Familiar. Worked in Java"),
        Distinct_Adj2("Distinct", "adjective" ,"Unique. No duplicates. Clearly different or of a different kind"),
        Distinct_Adj3("Distinct", "adverb","Uniquely. Writtern distinctly"),
        Distinct_Noun1("Distinct", "noun", "A keyword in this assignment"),
        Distinct_Noun2("Distinct", "noun", "A keyword in this assignment"),
        Distinct_Noun3("Distinct", "noun", "A keyword in this assignment"),
        Distinct_Noun4("Distinct", "noun","An advance search option"),
        Distinct_Noun5("Distinct", "noun","Distinct is a parameter in this assignment"),
        Placeholder_Adj1("PlaceHolder", "adjective", "To be updated..."),
        PlaceHolder_Adj2("PlaceHolder", "adjective", "To be updated..."),
        PlaceHolder_Adv1("PlaceHolder", "adverb","To be updated..."),
        PlaceHolder_Conj("PlaceHolder","Conjuction","To be updated..."),
        PlaceHolder_Inter1("PlaceHolder", "interjection","To be updated..."),
        PlaceHolder_Noun1("PlaceHolder","noun", "To be updated..."),
        PlaceHolder_Noun2("PlaceHolder", "noun","To be Updated"),
        PlaceHolder_Pre("PlaceHolder","preposition","To be updated..."),
        PlaceHolder_ProN("PlaceHolder","pronoun","To be updated..."),
        PlaceHolder_Verb("PlaceHolder","verb","To be updated..."),
        CSC340_Adj("CSC340","adjective","C++ version of CSC 210 + CSC220 + more"),
        CSC340_Noun1("CSC340","noun","A CS upper division course"),
        CSC340_Noun2("CSC340", "noun", "Many hours outside of class"),
        CSC340_Noun3("CSC340","noun","Programming Methodology"),
        CSC220_Adj("CSC220","adjective","Ready to create complex data structures"),
        CSC220_Verb("CSC220","verb","To Create data structures"),
        Book_Noun1("Book", "noun","A set of pages"),
        Book_Noun2("Book", "noun","A written work published in printed or electronic form"),
        Book_Verb1("Book","verb","To arrange for someone to have a seat on a plane."),
        Book_Verb2("Book","verb","To arrange something on a particular date."),
        Adverb("Adverb","noun","Adverb is a word that adds more information about place, time, manner, cause or\n" +
                "degree to a verb, an adjective, a phrase or another adverb."),
        Adjective("Adjective","noun"," Adjective is a word that describes a person or thing, for example big, red and\n" +
                "clever in a big house, red wine and a clever idea."),
        Interjection("Interjection","noun","Interjection is a short sound, word or phrase spoken suddenly to express an\n" +
                "emotion. Oh!, Look out! and Ow! are interjections."),
        Noun("Noun","noun"," Noun is a word that refers to a person, (such as Ann or doctor), a place (such as Paris\n" +
                "or city) or a thing, a quality or an activity (such as plant, sorrow or tennis.");

        private String key_Word;
        private String part_Of_Speech;
        private String definition;
        /* Constructor for enum Dictionary */
        private Dictionary(String key_Word, String part_Of_Speech, String definition){ this.key_Word = key_Word; this.part_Of_Speech = part_Of_Speech; this.definition = definition;}


        //Accessor to get Keywords from the user
        public String getKey_Word() {return key_Word.toUpperCase();}

        //To return the Part of Speech as a String
        public String part_ofSpeech(){ return this.part_Of_Speech;}
        // Getter for definition
        public String definition(){
            return this.definition;
        }


        // toString is part of every Java program but just overriding it here to get the output from the solution
        public String toString(){
            return this.key_Word+"["+ this.part_Of_Speech + "] " + this.definition;
        }

    } // End of Dictionary


    /* Methods to help retrieve data from the Dictionary */

    public static ArrayList<Dictionary> get_Part_Of_Speech(ArrayList<Dictionary> element, String input){
        ArrayList<Dictionary> correct_Element = new ArrayList<>();
        if(element != null){
            for(Dictionary values: element){
                if(values.part_ofSpeech().equalsIgnoreCase(input)){  //Making it not Case sensitive
                    correct_Element.add(values);
                }
            }
        } // end for if(element != null)
        return correct_Element;
    }

    //Method to return a Unique Part of speech
    public static ArrayList<Dictionary> get_Unique_Part_Of_Speech(ArrayList<Dictionary> element){
        HashMap<String,Dictionary> map = new HashMap<>();  // Same Logic as get_Part_Of_Speech()
        //if the user_input exists
        if(element != null){
            for(Dictionary values: element){
                String y = values.part_ofSpeech();
                String def = values.definition();
                String key = y+"-"+def;
                if(!map.containsKey(key)){
                    map.put(key,values);
                }
            }
        }
        return new ArrayList<>(map.values());


    }

    public static void main(String[] args) {
        // write your code here
        String pof = null;
        boolean running = true;
        boolean is_Distinct = false;
        String word = null;
        Scanner input = new Scanner(System.in);
        String user_Input;
        String delimiter = " ";
        String[] string_Splitter;

        /* HashMap to hold the keywords in the Dictionary */
        HashMap<String, ArrayList<Dictionary>> map = new HashMap<>();

        //Moving the data from the enumeration into the HashMap map
        for (Dictionary values : Dictionary.values()) { // the method values is a method in the HashMap class allowing to
            // move the values to keyword variable
            String keyWord = values.getKey_Word(); // returns keywords of the dictionary to keyWord

            //The values in the enum Dictionary are moved to the Array List. Based on the keyword elements
            // else add it to the ArrayList
            // and move into the HashMap
            ArrayList<Dictionary> list = (map.containsKey(keyWord))? map.get(keyWord): new ArrayList<Dictionary>();
            list.add(values);
            map.put(keyWord,list);


        }

        System.out.println("! Load Data...");
        System.out.println("! Loading Completed...");

        String[] array= new String[]{"noun", "verb", "adjective", "Pronoun", "adverb", "conjuction", "interjection", "preposition"};
        boolean ans;


        while (running) {
            is_Distinct = false;
            pof = null;
            System.out.println("Search :");
            user_Input = input.nextLine();
            string_Splitter = user_Input.split(delimiter);
            if(user_Input.equalsIgnoreCase("!Q")){
                break;
            }
            /*Get the very first input from the client */
            word = string_Splitter[0];

            /* This conditional checks on the second location the client entered was the string "distinct" */
            if(string_Splitter.length>1 &&string_Splitter[1]!=null){
                if(string_Splitter[1].equalsIgnoreCase("distinct")){
                    is_Distinct = true;
                }
                /* This conditional will check is */
                //asList makes sure
                else if(Arrays.asList(array).contains(string_Splitter[1].toLowerCase())){
                    pof = string_Splitter[1];
                }
                else{
                    System.out.println("|");
                    System.out.println(ENTRYNOTPARTOFSPEECH);	
                    System.out.println("|");
                    continue;
                }

            }


            if(string_Splitter.length>2 && string_Splitter[2]!= null){
                if(string_Splitter[2].equalsIgnoreCase("distinct")){
                    is_Distinct = true;
                }
                else if(Arrays.asList(array).contains(string_Splitter[2].toLowerCase())){
                    pof = string_Splitter[2];
                }
                else{
                    System.out.println("|");
                    System.out.println(ENTRYNOTPARTOFSPEECH);
                    System.out.println("|");
                    continue;
                }
            }
                System.out.println("|");
                ArrayList<Dictionary> result = map.get(word.toUpperCase());
                ArrayList<Dictionary> result2 = null;
                if(is_Distinct){
                    result2 = get_Unique_Part_Of_Speech(result);
                }
                else{
                    result2 = result;
                }

                ArrayList<Dictionary> result3 = null;
                if(pof != null){// Check if part of speech is not entered by the user
                    result3 = get_Part_Of_Speech(result2,pof);

                }
                else{
                    result3 = result2;
                }

                if (result3 != null&&result3.size()>0) {
                    for (Dictionary key : result3) {
                        System.out.println("  " + key + "\n");
                        //System.out.println("|");
                    }
                }else{
                    System.out.println(ENTRYNOTFOUD);
                }
                System.out.println("|");


        }
        System.out.println("----- Thank You -----");
    }// End of Main Method

}


