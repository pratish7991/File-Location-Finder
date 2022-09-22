package one;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void main() {
        String name ="salabim.pdf";

        assertEquals(4,Main.number_of_detected_file(new Logfile(),name));
        assertEquals(2,Main.number_of_detected_file_in_drive(new Logfile(),name,"D:\\"));
        assertEquals(2,Main.number_of_detected_file_in_drive(new Logfile(),name,"E:\\"));
        assertEquals(false,Main.search_operation(new Logfile(), name,"D:\\"));
        assertEquals(true,Main.search_operation(new Logfile(), name,"E:\\"));

        assertEquals(false,Main.search_operation_addition(new Logfile(), name,"D:\\"));
        assertEquals(false,Main.search_operation_deletion(new Logfile(), name,"D:\\"));
        assertEquals(false,Main.search_operation_directorychange(new Logfile(), name,"D:\\"));

        assertEquals(false,Main.search_operation_addition(new Logfile(), name,"E:\\"));
        assertEquals(false,Main.search_operation_deletion(new Logfile(), name,"E:\\"));
        assertEquals(true,Main.search_operation_directorychange(new Logfile(), name,"E:\\"));




//        table hsitory11 search operation deletion
//        table hsitory13 search operation directory
//        table hsitory12 search operation adiition
//        table hsitory14 search operation search operation
//        table hsitory4 main log file
//        table hsitory10 initial check

    }
}