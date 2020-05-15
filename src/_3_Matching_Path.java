import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class _3_Matching_Path {

    /*
    Using a glop or regex pattern string
    An instance of the PathMatcher interface is used to perform match .
    PathMatch is functional interface . It contain
    a  matches(Path path)

     */
//    Three step to process to match pattern to a path
    /*
    + prepare a glop or regex string
    + get a PathMatcher() method of a FileSystem object
    + call the matches() method with a Path
     */

    /*
    glop
     + an *  matches zero or more character without crossing directory
     + an ** matches zero or more character crossing directory boundaries
     */




    // test 1
    public static void main(String[] args) {

        // Learn about regex
        String regexPattern = "[txt]";
        PathMatcher  matcher = FileSystems.getDefault().getPathMatcher(regexPattern);
        Path path = Paths.get("C:\\Users\\nguye\\Desktop\\Bank.txt") ;
        boolean matched = matcher.matches(path);
        System.out.println(regexPattern + " matches   " + path.toAbsolutePath() +  "  " +   matched);

    }
}
