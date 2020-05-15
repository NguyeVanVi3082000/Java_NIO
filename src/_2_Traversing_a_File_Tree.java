import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/*

0. Exercise
- Copy from one directory to other directory
- Delete all file from directory


1.FileVisitor API to recursively process all files and directories in file tree
            => perform some actions on all or some file tree
                  Example : Deleting directory
            Following Steps  :
                 - Create a F visitor class by implementing java.nio.file.FileVisitor interface
                 - use WalkFileTree() : by specifying the start directory and a File visitor object
2. Method of FileVisitor interface
                - FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) throws IOException :
                                        This method is called once before visiting entries in a directory.


                - FileVisitResult postVisitDirectory(T dir, IOException exc) throws IOException
                                        This method is called after entries in a directory (and all of their descendants)
                                        have been visited. It is invoked even if there are errors during the visit of entries
                                        in a directory.If there was any exception thrown during the iteration ofa directory,
                                        the exception object is passed to this method as the second argument.
                                        If the second argument to this method is null, there was no exception during the
                                        directory iteration.

                - FileVisitResult visitFile(T file, BasicFileAttributes attrs) throws IOException
                                        This method is called when a file in a directory is visited.


                - FileVisitResult visitFileFailed(T file, IOException exc) throws IOException
                                        This method is called when a file or directory could not be visited for any reason



3. Enum Constants of FileVisitResult and their description
                + CONTINUE                    : Continues processing

                + SKIP_SIBLINGS               : Continues processing without visiting the siblings of the file or directory.
                                                If it is returned from the preVisitDirectory() method, the entries in the current
                                                directory is also skipped and the
                + postVisitDirectory()        : method is not called on that directory.

                + SKIP_SUBTREE                : Continues processing without visiting entries in the directory.
                                                It is meaningful only when returned from the preVisitDirectory() method.
                                                Otherwise, its effect is the same as CONTINUE.


                + TERMINATE                   : Terminates the file visiting process

 */
public class _2_Traversing_a_File_Tree {

    public static void main(String[] args) throws IOException {
//        Version 1 : Beginning Java 8 Language Features
//        // Get the Path Object for the default directory
//        Path strDir = Paths.get("");
//        // Get a File Visitor object
//        FileVisitor<Path> visitor = getFileVisitor();
//        try {
//            //Traverse the contents of the StarDir
//            Files.walkFileTree(strDir, visitor);
//        }catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        Version 2 : Beadlung

        Path startDir = Paths.get("C:\\Users\\nguye\\Desktop");
        walkFileTree test = new walkFileTree("Test.txt" ,startDir );
        Files.walkFileTree(startDir , test);


    }


//    @Version 1 : By Java Begin Language 8
    public static FileVisitor<Path> getFileVisitor()
    {
        // Declare a local class DirVisitor that
        //inherits from the SimpleFileVisitor<Path> class
        class  DirVisitor<Path> extends SimpleFileVisitor<Path>
        {
            @Override
            // return FileVisitResult
            // FileVisitResult  :
            // CONTINUE ,


            // SKIP_SIBLINGS : continue processing without visiting sibling  of the file  or directory
            // SKIP_SUBTREE  : Continue processing without visiting entries in the directory

            // TERMINATE



//            preVisitDirectory  : before directory is accessed
//            fileVisit : when file is visited
//            fileFailure : when a failure occurs
//            posVisitDirectory : after directory is visited



            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                System.out.format("%s [Directory] %n" , dir);
                return FileVisitResult.CONTINUE;

            }



            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                System.out.format("%s [File , Size : %s byte ] %n" ,file ,attributes.size());
                return FileVisitResult.CONTINUE;
            }

            // FileVisitResult 
        }

        //Create an Object of DirVisitor
        FileVisitor<Path> visitor = new DirVisitor<>();
        return visitor;



    }
    }

class walkFileTree implements FileVisitor<Path>
{

    private String fileName;
    // Why file name  ? File name is file search
    private Path startDir;
    // Why we using starting path . It is a base case to conclude that file has not been found


    //Standard constructor
    public walkFileTree(String fileName , Path startDir)
    {
        this.fileName = fileName;
        this.startDir = startDir;
    }

    public String getFileName()
    {
        return fileName;
    }
    public Path getStartDir()
    {
        return startDir;
    }





    // Implement FileVisitor
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        //  The APP is called each time the process
        //  encounters a new directory in the tree
        //  It return value determines what will happen next depending on what we decide
        //  This is this point we can skip specific directories and eliminate them from the search example

//      In this case we should return true
        return FileVisitResult.CONTINUE;
    }

        // Main action happen
        // This APT is called each time a file encountered .
        // We take advantage of this to check the file attributes and compare with our critiria

//      Path: is file search or Path of directory it in (File in a directory )
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if(file.getFileName().toString().equals(fileName))
        {
            System.out.println("Found the file ");
            System.out.println(file.toAbsolutePath());
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }


//        The API is called when a specific file is not accessible for  to JVM
//        or file has been locked by other application
//        or just is permission issue
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Failed to access file" + file.toString());
        return FileVisitResult.CONTINUE;
    }




//      This API is called each time a  directory a directory has been fully traversed
//       Files.isSameFile API to check if the directory that has just been traversed
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {


        // Dir is directory . Check if this directory is the same with start Directory
        // if yes answer yes
        if (Files.isSameFile(dir , startDir))
        {
            System.out.println("File : " + fileName + " not found");
            return FileVisitResult.TERMINATE;
        }
        else
        {

            System.out.println(dir.toAbsolutePath() + "done");
            return FileVisitResult.CONTINUE;
        }

    }

}
