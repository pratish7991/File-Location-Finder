package one;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Findfile extends Thread{


    //    private static HashMap<String, File> hashMap;
    private String name;
    private File file;
    private HashMap<String, ArrayList> hashMap =new HashMap<String,ArrayList>();
    private ArrayList<String> arrayList;
    private String drive;

    public Findfile(String name,String drive,File file,HashMap hashMap,ArrayList arrayList){
        this.name=name;
        this.file=file;
        this.hashMap=hashMap;
        this.arrayList=arrayList;
        this.drive=drive;
    }

    public void run()
    {
        File[] list = this.file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    System.out.println(fil.getParentFile());
                    this.file=fil;
                    run();
                }
                else if (this.name.equalsIgnoreCase(fil.getName()))
                {
                    System.out.println("file is found");
                    System.out.println(fil.getAbsolutePath());
                    arrayList.add(fil.getParent());
                    Logfile logfiles = new Logfile();
                    logfiles.insertTable(this.name,this.drive,fil.getAbsolutePath());
                }
            }
    }
}
