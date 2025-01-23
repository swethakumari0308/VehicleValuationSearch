package com.vehicle.valuation.search.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * This class is util class to read car registrations based on pattern from input file and update expected output details map.
 */
public class ReadInputFile {

    private static final String CAR_REG_NUMBER = "(^[A-Z]{2}[0-9]{2}([A-Z]{3}|\\s[A-Z]{3})$)";

    public static List<String> getVehicleRegistrationNumbers(String inputName) throws IOException {
        BufferedReader bufferedReader = null;
        String[] format = null;
        List<String> registrationDetails = new ArrayList<String>();
        try {
            File inputFile = getFile(inputName);
            bufferedReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            String regName = null;
            while ((regName = bufferedReader.readLine()) != null) {
                String registraionNumber = CAR_REG_NUMBER;
                format = regName.split(" ");
                for (String str : format) {
                    if (Pattern.matches(registraionNumber, str)) {
                        registrationDetails.add(str);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Exception occured while retrieve registration numbers:" + e.getMessage());
        } finally {
            bufferedReader.close();
        }
        return registrationDetails;
    }

    public static Map<String, List<String>> getExpectedDetails(String outputName) throws IOException {
        BufferedReader bufferedReader = null;
        String outLine = " ";
        List<String> outputDetails = new ArrayList<String>();
        Map<String, List<String>> expectedDetails = new HashMap<String, List<String>>();
        try {
            File inputFile = getFile(outputName);
            bufferedReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            while ((outLine = bufferedReader.readLine()) != null) {
                outputDetails.add(outLine);
            }
            outputDetails.remove(0);

            for (String expectedValue : outputDetails) {
                String[] exp = expectedValue.split(",");
                List<String> list = Arrays.asList(exp);
                expectedDetails.put(list.get(0), list);
            }

        } catch (Exception e) {
            System.err.println("Exception occured while retrieve output details: " + e.getMessage());
        } finally {
            bufferedReader.close();
        }

        return expectedDetails;
    }

    private static File getFile(String filepath) {
        ClassLoader classLoader = ReadInputFile.class.getClassLoader();
        return new File(classLoader.getResource(filepath).getFile());
    }
}


