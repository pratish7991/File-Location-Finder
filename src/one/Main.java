package one;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main
{
    public static int number_of_detected_file(Logfile logfile,String name){
        return logfile.selectCount(name);
    }
    public static int number_of_detected_file_in_drive(Logfile logfile,String name,String drive_name){
        return logfile.selectCount(name,drive_name);
    }
    public static void display_result(String name, HashMap<String,ArrayList> hashMap, ArrayList<String> arrayList1){
        hashMap.put(name,arrayList1);
        for (Map.Entry<String, ArrayList> set : hashMap.entrySet()) {
            System.out.println( "File Name: "+set.getKey() +","+ " Directory: " + set.getValue());
        }
    }
    public static boolean search_operation(Logfile logfile,String name,String g){
        return logfile.selectTable_searchoperation(name,g);
    }
    public static boolean search_operation_addition(Logfile logfile,String name,String g){
        return logfile.selectTable_searchoperation_addition(name,g);
    }
    public static boolean search_operation_deletion(Logfile logfile,String name,String g){
        return logfile.selectTable_searchoperation_deletion(name,g);
    }
    public static boolean search_operation_directorychange(Logfile logfile,String name,String g){
        return logfile.selectTable_searchoperation_directorychange(name,g);
    }

    public static void main(String[] args) throws InterruptedException {
        File[] fs = File.listRoots();
        System.out.println();
        System.out.println("Divers in the System are : ");

        for(int i=0;i<fs.length;i++){
            System.out.println((i+1)+ " " +fs[i]);
        }
        System.out.println();
        Main  ff = new Main();
        Scanner scan = new Scanner(System.in);

        System.out.printf("Enter the file to be search.. : " );
        String name = scan.nextLine();

        Logfile logfile = new Logfile();

        if (logfile.checkTable("history4")==0){
            logfile.createTable("history4");
        }
        if (logfile.checkTable("history10")==0){
            logfile.createTable("history10");
        }
        if (logfile.checkTable("history11")==0){
            logfile.createTable("history11");
        }
        if (logfile.checkTable("history12")==0){
            logfile.createTable("history12");
        }
        if (logfile.checkTable("history13")==0){
            logfile.createTable("history13");
        }
        if (logfile.checkTable("history14")==0){
            logfile.createTable("history14");
        }

        System.out.printf("Enter the drive to serach file in : ");
        String[] DriveInput = scan.nextLine().split(" ");

        ArrayList<String> number_drive_array = new ArrayList<>();

        for (int i=0;i< DriveInput.length;i++) {
            int h=Integer.parseInt(DriveInput[i]);
            String hh = String.valueOf(fs[h - 1]);
            number_drive_array.add(hh);
        }

        System.out.println();

        logfile.deletetable3(name);
        ArrayList<Thread> findfiles2 = new ArrayList<>();
        for (String s : number_drive_array) {
            findfiles2.add(new Detectfile(name, new File(s),s)) ;

        }
        for (Thread findfile:findfiles2){
            findfile.start();
        }
        for (Thread findfile:findfiles2){
            findfile.join();
        }
        ArrayList<String> number_drive_array2 = (ArrayList<String>)number_drive_array.clone();
        for (String g:number_drive_array){

            if (logfile.selectCountTable3(name,g)==logfile.selectCount(name,g)){
                    int z=0;
                    for (int j = 1; j<= logfile.selectCount(name, g); j++){
                        if(logfile.selectTable(name,j, g)!=null){
                            String x=logfile.selectTable(name,j, g);
                            x = x.trim();
                            File f = new File(x);
                            boolean b=f.exists();
                            if (b){
                                System.out.println("File directory: "+logfile.selectTable(name,j,g)+" from log history");
                                z++;
                                if (logfile.selectCountTable_searchoperation(name,g)==0){
                                    System.out.println("hdskjbo rgd");
                                    logfile.insertTable_searchoperation(name, g,false);
                                }else{
                                    logfile.updateTable_searchoperation(name,g, false);
                                }
                            }
                        }
                    }
                    if (z==logfile.selectCount(name,g)){
                        number_drive_array2.remove(g);
                    }
            }
        }


        HashMap<String, ArrayList> hashMap =new HashMap<String,ArrayList>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<Findfile> findfiles = new ArrayList<>();

        for (String g:number_drive_array2){
//            System.out.println(logfile.selectCountTable3(name,g)+" "+logfile.selectCount(name,g));
            if (logfile.selectCountTable_searchoperation(name,g)==0){
                System.out.println("thi sis a nkj");
                logfile.insertTable_searchoperation(name, g,true);
            }else{
                logfile.updateTable_searchoperation(name,g, true);
            }
            if (logfile.selectCountTable3(name,g)<logfile.selectCount(name,g) && (logfile.selectCountTable3(name,g)!=0) ){
                System.out.printf("%d file has deleted from the search drive %S ",-logfile.selectCountTable3(name,g)+logfile.selectCount(name,g),g);
                System.out.println();
                findfiles.add(new Findfile(name, g,new File(g),hashMap,arrayList1));
                logfile.deletetable(name,g);
                if (logfile.selectCountTable_searchoperation_deletion(name,g)==0){
                    logfile.insertTable_searchoperation_deletion(name,g,true);
                }else{
                    logfile.updateTable_searchoperation_deletion(name,g, true);
                }

                if (logfile.selectCountTable_searchoperation_addition(name,g)==0){
                    logfile.insertTable_searchoperation_addition(name,g, false);
                }else{
                    logfile.updateTable_searchoperation_addition(name,g, false);
                }

                if (logfile.selectCountTable_searchoperation_directorychange(name,g)==0){
                    logfile.insertTable_searchoperation_directorychange(name,g, false);
                }else{
                    logfile.updateTable_searchoperation_directorychange(name,g, false);
                }

            }else if ((logfile.selectCountTable3(name,g)>logfile.selectCount(name,g)) && (logfile.selectCount(name,g)!=0)){
                System.out.printf("%d extra file has added in search drive %S ", (logfile.selectCountTable3(name,g)-logfile.selectCount(name,g)),g);
                System.out.println();
                logfile.deletetable(name,g);
                findfiles.add(new Findfile(name, g,new File(g),hashMap,arrayList1)) ;
                if (logfile.selectCountTable_searchoperation_deletion(name,g)==0){
                    logfile.insertTable_searchoperation_deletion(name,g,false);
                }else{
                    logfile.updateTable_searchoperation_deletion(name,g, false);
                }

                if (logfile.selectCountTable_searchoperation_addition(name,g)==0){
                    logfile.insertTable_searchoperation_addition(name,g, true);
                }else{
                    logfile.updateTable_searchoperation_addition(name,g, true);
                }

                if (logfile.selectCountTable_searchoperation_directorychange(name,g)==0){
                    logfile.insertTable_searchoperation_directorychange(name,g, false);
                }else{
                    logfile.updateTable_searchoperation_directorychange(name,g, false);
                }
            }else if ((logfile.selectCountTable3(name,g)>logfile.selectCount(name,g))){

                logfile.deletetable(name,g);
                findfiles.add(new Findfile(name, g,new File(g),hashMap,arrayList1)) ;
                if (logfile.selectCountTable_searchoperation_deletion(name,g)==0){
                    logfile.insertTable_searchoperation_deletion(name,g,false);
                }else{
                    logfile.updateTable_searchoperation_deletion(name,g, false);
                }
                if (logfile.selectCountTable_searchoperation_addition(name,g)==0){
                    logfile.insertTable_searchoperation_addition(name,g, false);
                }else{
                    logfile.updateTable_searchoperation_addition(name,g, false);
                }

                if (logfile.selectCountTable_searchoperation_directorychange(name,g)==0){
                    logfile.insertTable_searchoperation_directorychange(name,g, false);
                }else{
                    logfile.updateTable_searchoperation_directorychange(name,g, false);
                }
            }else if ((logfile.selectCountTable3(name,g)==logfile.selectCount(name,g))){
//                System.out.println("this is a man");
                for (int j = 1; j<= logfile.selectCount(name, g); j++){
                    if(logfile.selectTable(name,j, g)!=null){
                        String x=logfile.selectTable(name,j, g);
                        x = x.trim();
                        File f = new File(x);
                        boolean b=f.exists();
                        if (!b){
                            findfiles.add(new Findfile(name, g,new File(g),hashMap,arrayList1)) ;
                            System.out.printf("File location of directory {%S} has altered in drive %S",logfile.selectTable(name,j, g),g);
                            System.out.println();
                            if (logfile.selectCountTable_searchoperation_deletion(name,g)==0){
                                logfile.insertTable_searchoperation_deletion(name,g,false);
                            }else{
                                logfile.updateTable_searchoperation_deletion(name,g, false);
                            }
                            if (logfile.selectCountTable_searchoperation_addition(name,g)==0){
                                logfile.insertTable_searchoperation_addition(name,g, false);
                            }else{
                                logfile.updateTable_searchoperation_addition(name,g, false);
                            }
                            if (logfile.selectCountTable_searchoperation_directorychange(name,g)==0){
                                logfile.insertTable_searchoperation_directorychange(name,g, true);
                            }else{
                                logfile.updateTable_searchoperation_directorychange(name,g, true);
                            }
                            logfile.deletetable(name,g);
                        }
                    }
                }
            }

        }
        if (findfiles.size()!=0){
            for (Findfile findfile:findfiles){
                findfile.start();
            }
            for (Findfile findfile:findfiles){
                findfile.join();
            }
            Main.display_result(name,hashMap,arrayList1);
        }
        }
    }


