package com.java.zonov.cft.sorting;

import com.java.zonov.cft.sorting.read.ReadFromSource;
import com.java.zonov.cft.sorting.sort.SortingT;
import com.java.zonov.cft.sorting.verify.Verification;
import com.java.zonov.cft.sorting.write.WriteToSource;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Command line parameter. Data type - Integer
     */
    @Option(name = "-i", usage = "Integer data type")
    public boolean intType;

    /**
     * Command line parameter. Data type - String
     */
    @Option(name = "-s", usage = "String data type", forbids = {"-i"})
    public boolean strType;

    /**
     * Command line parameter. Sorting type - ASC
     */
    @Option(name = "-a", usage = "ASC Sorting", forbids = {"-d"})
    public boolean asc;

    /**
     * Command line parameter. Sorting type - DESC
     */
    @Option(name = "-d", usage = "DESC Sorting")
    public boolean desc;

    /**
     * Command line arguments. Output and input file names
     */
    @Argument(required = true, metaVar = "IO files")
    private List<String> arguments = new ArrayList<>();


    public static void main(String[] args) {
        new Main().start(args);
    }

    private void start(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if (!(strType ^ intType)) {
                throw new CmdLineException(parser, "Data type option is not set", new Throwable());
            }
            if (arguments.size() < 2) {
                throw new CmdLineException(parser, "Not enough arguments [IO files] is given", new Throwable());
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Program [-options...] arguments...");

            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample
            System.err.println("Example: sort.exe -i -d in.txt out.txt");
            System.exit(2);
        }

        String inputFileName = arguments.get(0); //  имя файла для считывания данных
        String outputFileName = arguments.get(1); // имя файла для сохранение

        List<String> listStr = ReadFromSource.getData(inputFileName);

        if (intType) {
            List<Integer> listInt = Verification.check(listStr);

            if (!listInt.isEmpty()) {
                WriteToSource.write(outputFileName, SortingT.sort(listInt), desc);
            }
            else {
                System.err.println("Nothing to sort");
                System.exit(2);
            }
        } else if (strType && !listStr.isEmpty()) {
            WriteToSource.write(outputFileName, SortingT.sort(listStr), desc);
        } else {
            System.err.println("Nothing to sort");
            System.exit(2);
        }


    }
}
