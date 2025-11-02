import java.util.Scanner;
import java.util.regex.Pattern; 
import java.util.regex.Matcher; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordCounter {
    public static int processText(StringBuffer text, String stopword) throws TooSmallText, InvalidStopwordException {
        // returns number of words until the stopword, not inclufing the stopword 
        // if it's less then five words it will throw toosmall even if the stopword is fousn 
        // text is args[0]
        // stopwors is arrgs[0]
        Pattern regex = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher regexMatcher = regex.matcher(text); 
        int num = 0; 
        boolean found = false;
        
        while(regexMatcher.find()){
            String word = regexMatcher.group();
            num++; 

            if (stopword != null && word.equals(stopword)){
                found = true;
                break;
            }
        }

        if(stopword != null && !found){
            throw new InvalidStopwordException("Couldn't find stopword: " + stopword);
        }

        if (num < 5 ){
            throw new TooSmallText("Only found " + num + " words");
        }

        return num; 

    }
        

    public static StringBuffer processFile(String path) throws EmptyFileException{
        // return StringBuffer holding all contents of the file 
        // when you try to open the file and it doesnt exsist yo have to catch a filenotfundexception 
        Scanner sc = new Scanner(System.in); 
        String curr = path; 
        StringBuffer contents = new StringBuffer(); 

        while(true){
            try{ 
                BufferedReader rd = new BufferedReader(new FileReader(curr));
                String line; 

                while((line = rd.readLine()) != null){
                contents.append(line);
                
                }

                rd.close();

                if(contents.length() == 0){
                throw new EmptyFileException(curr + "was empty");
                
                }

                return contents; 

            } catch (FileNotFoundException e ){
                System.out.println("file not found"); 
                curr = sc.nextLine();
            } catch (IOException e){
                System.out.println("reading error"); 
                return contents; 

            }
            
        } 


    }

    // path is args[0] in main 
    // you can name path to text if you want 
    // prompt the user to input one and 2 
    // create scanner 
    // call system .in 
    // save that variable

    public static void main(String [] args){
        if (args.length == 0 ){
            System.out.println("file or text?");
            return;
        }
        Scanner sc = new Scanner(System.in); 

        int option = 0; 
        while(option != 1 && option != 2){
            System.out.println("option 1 or 2?"); 
            if (sc.hasNextInt()){
                option = sc.nextInt();
                sc.hasNextLine(); 
                if (option != 1 && option != 2){
                    System.out.println("invalid option, option 1 or 2?");
    
                }

            } else {
                System.out.println("invalid input, enter 1 or 2?");
                sc.nextLine();

            }
        }
        String input = args[0]; 
        String stopword = null; 

        if(args.length > 1){
            stopword = args[1]; 
        }

        StringBuffer text = new StringBuffer(); 

        if(option == 1){
            try{
                text = processFile(input); 
            } catch (EmptyFileException e){
                System.out.println(e); 
                text = new StringBuffer("");
                
            }
        } else if (option == 2){
            text = new StringBuffer(input); 
        }
        try {
            int count = processText(text, stopword); 
            System.out.println("Found" + count + "words");  
        } catch (InvalidStopwordException e){
            System.out.println(e);
            System.out.println("new stopword"); 
            String newStopword = sc.nextLine();

            try{
                int count = processText (text, newStopword);
                System.out.println("Found" + count + "words");

            } catch (InvalidStopwordException e2){
                System.out.println(e2); 
                
            } catch (TooSmallText e2){
                System.out.println(e2);

            }
        } catch (TooSmallText e){
            System.out.println(e);
        }
        sc.close();
    }

}
