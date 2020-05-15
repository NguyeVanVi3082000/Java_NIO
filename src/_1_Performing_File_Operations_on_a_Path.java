import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import static java.nio.file.StandardOpenOption.*;

/*
StandardOpenOption
APPEND          : append the written data to the existing file , if the fie is opened for writing
CREATE          : Create a new f if it does not exist
CREATE_NEW      : Create a new f if it does not exist .If the f already exist , it falls operation
DELETE_ON_CLOSE : Deletes the file when the stream is closed. It is useful when used with a
temporary file.
DSYNC           : Keeps the contents of the file synchronized with the underlying storage.
READ            : Opens a file with a read access.
SPARSE          : If it is used with the CREATE_NEW option, it is a hint to the file system that the new
file should be a sparse file. If a sparse file is not supported by a file system, this
option is ignored.
SYNC            : Keeps the content and the metadata of the file synchronized with the
underlying storage.
TRUNCATE_EXISTING   Truncates the length of an existing file to zero if the file is opened for a write
access.
WRITE           : Opens a file for a write access



 */

/*
Performing File Operations on a Path
- create a new F
- Deleting F
- Checking for Existence of a F
- Copying and Moving F
- commonly used FAttributes
- Probing the content type of a F
- reading the content for a F
- Writing to a F
* Random Access to a F

 */

public class _1_Performing_File_Operations_on_a_Path {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create a new File
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter File Path : ");
//        String path = input.nextLine();
//        Path p = Paths.get(path);
//        try {
//            Files.createFile(p);
//            System.out.println("Create file successful  ");
//
//
//        }catch (IOException e)
//        {
//            System.out.println(e.getCause());
//        }


        // Write to file
           // Theory
              /*
              - static Path write(Path p , byte[] bytes , OpenOption... options)
              - static Path write(Path p , Iterable<? extend CharSequence> lines  , OpenOption... options)
              - static Path write(Path p , Iterable<? extend CharSequence> lines   , Charset cs, OpenOption... options)



               */
           //OutputStream

        List<String> texts = new ArrayList<>();
        texts.add("Nguyen Van Vi");
        texts.add("Dia chi : Lap Thac - Vinh Phuc");
        texts.add("Nghe Nghiep : Sinh Vien");



        String fileContent = "Nguyen Van Vi";
        byte[] bytes = fileContent.getBytes();
        Charset cs = Charset.forName("US-ASCII");

        Path path1 = Paths.get("C:\\Users\\nguye\\Desktop\\NewFile.txt");
//        try {
//            Files.write(path1, texts , cs,WRITE,CREATE);
//        }catch (IOException e)
//        {
//            System.out.println(e.getCause());
//        }
//

        // Write APPEND


        String universityName = "University :National Economic University" ;


        byte[] universityNameBytes = universityName.getBytes();


//        try {
//            Files.write(path1 ,universityNameBytes  ,WRITE , APPEND);
//
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        // Write CSV file
//        List<String> csvFile = new ArrayList<>() ;
//        csvFile.add("Nguyen Van Vi ,19,Lap Thach, NEU");
//        csvFile.add("Nguyen Hoang Minh ,19,Bac Ninh, NEU");
//        csvFile.add("Tran Thanh Loan,19,Hai Phong, NEU");
//        csvFile.add("Nguyen Cong Minh,19,Lang Son, NEU");
//
        System.out.println("Enter Path to Write");
        String pathString = input.nextLine();
//        // How to create a path
//        Path path2 = FileSystems.getDefault().getPath("C:\\");
        Path path = Paths.get(pathString) ;
//        try {
//            Files.write(path , csvFile , cs ,WRITE );
//
//
//        }catch (IOException e)
//        {
//            e.printStackTrace();
//        }





        readContentFile(path).forEach(System.out::println);




    }

    // Read the content of File
    /*
     static byte[] readAllBytes(Path path)
	 static List<String> readAllLines(Path path)
	 static List<String> readAllLines(Path path, Charset cs)
     */
    public  static List<String> readContentFile(Path path)
    {
        List<String> info = new ArrayList<>();
        try {
             info = Files.readAllLines(path);

        }catch (IOException e)
        {
            System.out.println(e.getCause());
        }

        return info;

    }
}
