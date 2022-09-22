package one;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Detectfile extends Thread{
    private String name;
    private File file;
    private String drive;

    public Detectfile(String name, File file,String drive) {
        this.name = name;
        this.file = file;
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
                    this.file=fil;
                    run();
                }
                else if (this.name.equalsIgnoreCase(fil.getName()))
                {
                    Logfile logfiles = new Logfile();
                    logfiles.insertTable3(this.name,this.drive,fil.getAbsolutePath());
                }

            }
    }
}
