import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.concurrent.TimeUnit;


/*
Note : the different between
    - UnallocatedSpace : available space as seen by the underlying file system
    - AvailableSpace :  Usable space is space available to the JVM
    - Usable space Sometimes  is smaller than Unallocated Space
 */

public class _4_Managing_File_Attributes {


    /*
    - @BasicFileAttributeView  :  manage basic attributes
      + creation time
      + last time access
      + last modified time
      + size
      + file type
      + file key : a unique number of file
      => It let you modify this attribute
     - @DosFileAttributeView : (allows you to access the file attributes that are specific to DOS )
     +  Check if a file is hidden file
     + a system file
     + an archive file
     + a read only file
     - @PosixFileAttributeView : POSIx stands for Portable Operating System Interface : UNIX
     + manage : owner , group and permissions
     - @FileOwnerAttributeView : This attribute view lets you manage the owner of a file
     - @AclFileAttributeView : ACL stand for Access Control List . It is a list of permissions attached to a file
     - @UserDefinedFileAttributeView : lets you manage a set of user-defined attributes for a File

     */


    /*
    Checking for a File Attribute View Support
    - supportsFileAttributeView() method of the FileStore class to check whether a specific file attribute supported
    Example : Checking File Attribute View bu File store Object
    Reading and Updating File Attributes
    - Object getAttribute(Path path , String attribute , LinkOption ....Options)
    - Object setAttribute(Path path , String attribute , Object value , LinkOption ... options )
    XxxAttributes : let you read attributes
    XxxAttributeView : interface if you want to read and update

The following two methods of the File class let you read the  file attribute in a bulk

 <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type,
    LinkOption... options)
 Map<String,Object> readAttributes(Path path, String attributes, LinkOption...
    options)

The last argument of both methods let you specify how a symbolic link is handled .
By default if a file is a symbolic link , the attributes of target of symbolic are read
If you specify NOFOLLOW_LINKS are the option , the attribute of the symbolic link are read
        - readAttributes() in a XxxAttributes object




     */

    public static void main(String[] args) throws IOException {

        // Reading File Attribute
            // Create the Path object representing the path of the file
            Path path = Paths.get("c:\\Users\\nguye\\Desktop\\Bank.txt");
            // Read the basic file attributes
            BasicFileAttributes bfa = Files.readAttributes(path , BasicFileAttributes.class);
            // Get the last modified time
            FileTime lastModifiedTime = bfa.lastModifiedTime();
            // Get the size of the file
            long size = bfa.size();

            System.out.println("Old last time modified " + lastModifiedTime);


            // Create a new File Time

            FileTime  newFileTime = FileTime.fromMillis((bfa.lastModifiedTime().to(TimeUnit.HOURS)  + 10));

            // Using BasicFileAttributeView to update new file attribute
            BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path , BasicFileAttributeView.class);
            BasicFileAttributes bfav = basicFileAttributeView.readAttributes();


            basicFileAttributeView.setTimes(newFileTime , null  , null);

            System.out.println("Using BasicFileAttributes " + bfa.lastModifiedTime());
            System.out.println();
            System.out.println("Using BasicFileAttributeView to set new last modify time" + bfav.lastModifiedTime());
            System.out.println(size);

        /*
        Path path = Paths.get("C:\\Users\\nguye\\Desktop\\Bank.txt");
        // Get File Store Reference
        try {


            FileStore fs = Files.getFileStore(path);
            // Check if POSIX file attribute is supported by the file store
            boolean supported = fs.supportsFileAttributeView(PosixFileAttributeView.class);
//            boolean supported1 = fs.supportsFileAttributeView(BasicFileAttributes.class);
            boolean supported2 = fs.supportsFileAttributeView(DosFileAttributeView.class);
            boolean supported3 = fs.supportsFileAttributeView(AclFileAttributeView.class);
            boolean supported4 = fs.supportsFileAttributeView(FileOwnerAttributeView.class);

            if (supported)
            {
                System.out.println("POSIX file attribute view is supported");

            }else
            {
                System.out.println("POSIX file attribute view is not supported ");
            }
            if (supported2)
            {
                System.out.println("DOS file attribute view is supported");

            }else
            {
                System.out.println("DOS file attribute view is not supported ");
            }
            if (supported4)
            {
                System.out.println("File Owner Attribute ");

            }else
            {
                System.out.println("File Owner Attribute no ");
            }
        } catch (IOException E)
        {
            E.printStackTrace();
        }

         */
    }


}
