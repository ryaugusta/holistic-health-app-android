// Project:     Natural Remedies
// Author:      Augusta
// Date:        1/25/18
// File:        DatabaseTest.java
// Description: This class tests creating and population of listings in the Vitamin and Supplement

package com.example.ryanaugusta.hoistichealthapp;


import android.content.Context;
import android.database.Cursor;

// class is abstract because there is no need to create an object
// It is only used to hold methods to test the database
public abstract class DatabaseTest {

    // make the argument associated with the table
    public static void populateVitaminTable(Context context) {

        // create database handler object
        DatabaseHandler dbh = new DatabaseHandler(context);

        dbh.deleteVitaminObjects();


        // create data to add to test vitamin table
        try {
            // 2 vitamins to improve memory
            VitaminInfo vitamin1 = new VitaminInfo("Vitamin B-12 ",
                    "Having enough B-12 in your diet can improve memory. Research shows that B-12 can" +
                    "slow cognitive decline in people with early Alzheimer's when taken together with Omega-3 fatty acids");
            VitaminInfo vitamin2 = new VitaminInfo("Vitamin E",
                    "An anti-oxidant that has been studied in the treatment for Alzheimer's and memory loss" +
                    "It protects the neural linings surrounding the nerves. Helps retain information longer and better");

            // 2 vitamins to help anxiety
            VitaminInfo vitamin3 = new VitaminInfo("Vitamin B3 ",
                    "Involved in many enzymatic processes and plays a key role in serotonin synthesis. Doses of 1000 - 3000mg per day" +
                    "may be helpful for anxiety");
            VitaminInfo vitamin4 = new VitaminInfo("Vitamin D ",
                    "fat-soluble vitamin found in eggs, fatty fish and naturally produced through your body after exposure to the suns UV rays" + "" +
                    "May improve seasonal anxiety and depression that worsen during winter months. Also important for: immunity, bone health and heart health.");

            // add the vitamin info to the table
            dbh.addVitaminInfo(vitamin1);
            dbh.addVitaminInfo(vitamin2);
            dbh.addVitaminInfo(vitamin3);
            dbh.addVitaminInfo(vitamin4);

            // test method to getAllVitaminObjects
            System.out.println("Get Vitamins");
            // get all objects
            Cursor cursor = dbh.getAllVitaminObjects();

            // move cursor to the first
            cursor.moveToFirst();

            // loop through the cursor
            while (cursor.isAfterLast() == false) {

                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "
                        + cursor.getString(2));
                cursor.moveToNext();
            }

            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // the supplement test has actual data that will be used in the final application
    public static void populateSupplementTable(Context context) {

        DatabaseHandler dbh = new DatabaseHandler(context);
        dbh.deleteSupplementObjects();

        // create data to add to test vitamin table
        try {
            // 3 supplements for 'memory, brain function / nootropics'
            SupplementInfo supp1 = new SupplementInfo("Lions Mane ",
                    "Mushroom Supplement, This species of mushroom has been the subject of recent studies, " +
                    "and is renowned for providing support to the brain, nervous system, and memory");
            SupplementInfo supp2 = new SupplementInfo("Ginkgo Biloba ",
                    "Used in traditional Chinese medicine for thousands of years, " +
                    "and today it is one of the most popular herbal supplements," +
                    " widely advertised as an antioxidant that helps prevent memory loss and dementia");
            SupplementInfo supp3 = new SupplementInfo("Fish Oil",
                    "A rich source of docosahexaenoic acid (DHA) and eicosapentaenoic acid (EPA), " +
                    "two types of omega-3 fatty acids that have been inked with many health benefits, including improved brain function.");

            // 2 supplements for arthritis pain
            SupplementInfo supp4 = new SupplementInfo("Ginger ",
                    "Ginger has been shown to have anti-inflammatory properties similar to ibuprofen and COX-2 inhibitors" +
                    "in 2012 a specialized ginger extract reduced inflamation in Rheumatoid Arthritis as effectively as steroids.");
            SupplementInfo supp5 = new SupplementInfo("Tumeric ",
                    "'Curcumin' is the chemical in tumeric that can reduce joint pain and swelling by blocking inflammatory" +
                    "cytokines and enzymes. A 2010 clinical trial showed long-term improvement in pain and function. arthritis");



            // add the vitamin info to the table
            dbh.addSupplementInfo(supp1);
            dbh.addSupplementInfo(supp2);
            dbh.addSupplementInfo(supp3);
            dbh.addSupplementInfo(supp4);
            dbh.addSupplementInfo(supp5);

            // test method to getAllVitaminObjects
            System.out.println("Get Supplements");

            // get all objects
            Cursor cursor = dbh.getAllSupplementObjects();

            // move cursor to the first
            cursor.moveToFirst();


            // loop through the cursor
            while (cursor.isAfterLast() == false) {

                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "
                        + cursor.getString(2));
                cursor.moveToNext();
            }

            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void populateMyStackFrag (Context context) {

        DatabaseHandler dbh = new DatabaseHandler(context);
        //dbh.deleteMyStackObjects();

        // create data to add to test vitamin table
        try {
            // 3 supplements for 'memory, brain function / nootropics'
            MyStackInfo stack1 = new MyStackInfo("testing");


            // add the vitamin info to the table
            dbh.addMyStackInfo(stack1);


            // test method to getAllVitaminObjects
            System.out.println("Get Supplements");

            // get all objects
            Cursor cursor = dbh.getAllMyStackObjects();

            // move cursor to the first
            cursor.moveToFirst();


            // loop through the cursor
            while (cursor.isAfterLast() == false) {

                System.out.println(cursor.getInt(0) + ", " + cursor.getString(1) + ", "
                        + cursor.getString(2));
                cursor.moveToNext();
            }

            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

