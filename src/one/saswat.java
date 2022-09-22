//package one;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class saswat {
//    public static void logSearch(String name)throws FileNotFoundException {
//
////File nw = new File();
//
//        Scanner scan = new Scanner(new File("logFile.txt"));
//
//        while(scan.hasNextLine()){
//
//            String line = scan.nextLine().toString();
//
//            if(line.contains(name)){
//
//                System.out.println(line);
//
//            }
//
//        }
//
//    }
//
//    public static void fileStore(String filename,File file)throws IOException {
//
//        File nw = new File("logFile.txt");
//
//        if(nw.exists()) {
//
//            FileWriter fw = new FileWriter(nw,true);
//
//            fw.write(filename+" presents in "+file.getAbsolutePath()+"\n");
//
//            fw.close();
//
//        }
//
//        else {
//
//            nw.createNewFile();
//
//            FileWriter fw = new FileWriter(nw);
//
//            fw.write(filename+" presents in "+file.getAbsolutePath()+"\n");
//
//            fw.close();
//
//        }
//}
