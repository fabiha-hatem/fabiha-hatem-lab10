public class WordCounter {
    public static int processText(StringBuffer text, String stopword) throws TooSmallText, InvalidStopwordException {
        // returns number of words until the stopword, not inclufing the stopword 
        // if it's less then five words it will throw toosmall even if the stopword is fousn 
        // text is args[0]
        // stopwors is arrgs[0]
        Pattern regex = Pattern.compile();
        Matcher regexMatcher = regex.matcher(text); 
        while(regexMatcher.find()){
            System.out.println(regexMatcher.group());
        }

    }
        
       
    public static void main(String [] args){

    }

    public static StringBuffer ProcessFile(String path) throws EmptyFileException{
        // return StringBuffer holding all contents of the file 
        // when you try to open the file and it doesnt exsist yo have to catch a filenotfundexception 

    }

    // path is args[0] in main 
    // you can name path to text if you want 


}
