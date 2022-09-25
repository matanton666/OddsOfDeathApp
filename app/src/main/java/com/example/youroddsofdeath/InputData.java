package com.example.youroddsofdeath;

import android.content.res.Resources;
import android.util.Log;


import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InputData {
    public static HashMap<String, Double> loadLifeTimeRiskMap(){
        HashMap<String, Double> lifeTimeRisksMap = new HashMap<>();

        lifeTimeRisksMap.put("Heart disease",6.0);
        lifeTimeRisksMap.put("Cancer",7.0);
        lifeTimeRisksMap.put("Covid-19",12.0);
        lifeTimeRisksMap.put("opioid overdose",67.0);
        lifeTimeRisksMap.put("Suicide",93.0);
        lifeTimeRisksMap.put("Motor-vehicle crash", 101.0);
        lifeTimeRisksMap.put("falling", 102.0);
        lifeTimeRisksMap.put("Gun shooting / assault", 221.0);
        lifeTimeRisksMap.put("Pedestrian on road incidnt", 541.0);
        lifeTimeRisksMap.put("Motorcycle accedent", 799.0);
        lifeTimeRisksMap.put("Drowning", 1024.0);
        lifeTimeRisksMap.put("Fire / smoke", 1450.0);
        lifeTimeRisksMap.put("Choke from food", 2745.0);
        lifeTimeRisksMap.put("Bicycle accident", 3396.0);
        lifeTimeRisksMap.put("Heat / sun stroke", 6368.0);
        lifeTimeRisksMap.put("Accidental gun discharge", 7998.0);
        lifeTimeRisksMap.put("Electrocution", 14705.0);
        lifeTimeRisksMap.put("Radiation", 14705.0);
        lifeTimeRisksMap.put("Extreme temperatures", 14705.0);
        lifeTimeRisksMap.put("Pressure (physical)", 14705.0);
        lifeTimeRisksMap.put("Sharp object", 26744.0);
        lifeTimeRisksMap.put("Wasp/bee sting", 57825.0);
        lifeTimeRisksMap.put("Dog attack", 69016.0);



        // do 100/x for each value in the map
        for (Map.Entry<String, Double> entry : lifeTimeRisksMap.entrySet()) {
            lifeTimeRisksMap.put(entry.getKey(), 100.0/entry.getValue());
        }

        lifeTimeRisksMap.put("Stroke", .0);
        lifeTimeRisksMap.put("Respiratory disease", .0);
        lifeTimeRisksMap.put("Alzheimer's disease", .0);
        lifeTimeRisksMap.put("Diabetes", .0);
        lifeTimeRisksMap.put("Flu disease", .0);
        lifeTimeRisksMap.put("Poisoning", .0);
        lifeTimeRisksMap.put("Respiratory infection", 2.38);
        lifeTimeRisksMap.put("Infant death", 1.73);
        lifeTimeRisksMap.put("Diarrheal death", 1.66);
        lifeTimeRisksMap.put("Liver disease", 1.26);
        lifeTimeRisksMap.put("Tuberculosis", 1.21);
        lifeTimeRisksMap.put("Kidney disease", 1.19);
        lifeTimeRisksMap.put("", .0); // more at /programming stuff/oddeath app/

        return lifeTimeRisksMap;
    }


    public static HashMap<String, Double> worldLifeRisk(){
        int totalDeathsDaily = 164380;
        double dailyPercentDeath =  0.00205;
        long totalPopulation = 8000000000L;

        HashMap<String, Double> lifeRiskAccurate = new HashMap<>();

        lifeRiskAccurate.put("Cardiovascular diseases", 32.83833059 );
        lifeRiskAccurate.put("Neoplasms", 17.83155684 );
        lifeRiskAccurate.put("Chronic respiratory diseases", 7.030830952 );
        lifeRiskAccurate.put("Digestive diseases", 4.524724132 );
        lifeRiskAccurate.put("Lower respiratory infections", 4.410638747 );
        lifeRiskAccurate.put("Neonatal disorders", 3.330159626 );
        lifeRiskAccurate.put("Alzheimer's disease and other dementias", 2.871684591 );
        lifeRiskAccurate.put("Diabetes mellitus", 2.74412422 );
        lifeRiskAccurate.put("Diarrheal diseases", 2.71453303 );
        lifeRiskAccurate.put("Cirrhosis and other chronic liver diseases", 2.604088385 );
        lifeRiskAccurate.put("Chronic kidney disease", 2.524869549 );
        lifeRiskAccurate.put("Road injuries", 2.11985396 );
        lifeRiskAccurate.put("Tuberculosis", 2.087085525 );
        lifeRiskAccurate.put("HIV/AIDS", 1.528185843 );
        lifeRiskAccurate.put("Self-harm", 1.342771662 );
        lifeRiskAccurate.put("Malaria", 1.138184328 );
        lifeRiskAccurate.put("Interpersonal violence", 0.734481387 );
        lifeRiskAccurate.put("Parkinson's disease", 0.642006929 );
        lifeRiskAccurate.put("Nutritional deficiencies", 0.44505666 );
        lifeRiskAccurate.put("Drowning", 0.419697079 );
        lifeRiskAccurate.put("Meningitis", 0.41789263 );
        lifeRiskAccurate.put("Protein-energy malnutrition", 0.375470395 );
        lifeRiskAccurate.put("Maternal disorders", 0.347570434 );
        lifeRiskAccurate.put("Alcohol use disorders", 0.297229853 );
        lifeRiskAccurate.put("Drug use disorders", 0.226587455 );
        lifeRiskAccurate.put("Fire and heat and hot substances", 0.196883045 );
        lifeRiskAccurate.put("Acute hepatitis", 0.140067677 );
        lifeRiskAccurate.put("Poisonings", 0.136504776 );
        lifeRiskAccurate.put("Conflict and terrorism", 0.111424708 );
        lifeRiskAccurate.put("Environmental heat and cold exposure", 0.083961706 );
        lifeRiskAccurate.put("Exposure to forces of nature", 0.010748853 );

        for (Map.Entry<String, Double> entry : lifeRiskAccurate.entrySet()){
            double calc = (entry.getValue() / 100) * totalDeathsDaily;
            calc = (calc / totalPopulation) * 100;
            calc = 1/calc;
            lifeRiskAccurate.put(entry.getKey(), calc);
        }

        return lifeRiskAccurate;
    }

    public static HashMap<String, Double> getMapFromJson(String listName, Resources res){
        InputStream in_s = res.openRawResource(R.raw.test2);
        HashMap<String , Double> map = new HashMap<>();

        try {// read content to byte array and convert to string
            byte[] content = new byte[(int) in_s.available()];
            in_s.read(content);
            String txt = new String(content);

            JSONObject obj = new JSONObject(txt);
            JSONObject list = obj.getJSONObject(listName);
            // iterate on list variable
            for (Iterator<String> it = list.keys(); it.hasNext(); ) {
                String key = it.next();
                map.put(key, list.getDouble(key));
            }


        } catch (Exception e) {e.printStackTrace();}
        return map;
    }
    /*
    // save map to file
    public static void storeMapInPropertie(HashMap<String, Double> map, String name) throws IOException {
        Properties properties = new Properties();

        for (Map.Entry<String,Double> entry : map.entrySet()) {
            properties.put(entry.getKey(), (100/entry.getValue()));
        }
        properties.store(new FileOutputStream("lifeRiskData.properties"), null);
    }

    // load the map from file
    public static HashMap<String, Double> loadMapFromProperties(String name) throws IOException {
        Properties properties = new Properties();
        HashMap<String, Double> map = new HashMap<String, Double>();

        properties.load(new FileInputStream("lifeRiskData.properties"));
        for (String key : properties.stringPropertyNames()) {
            map.put(key, Double.parseDouble(properties.get(key).toString()));
        }
        return map;
    }
    */

}
