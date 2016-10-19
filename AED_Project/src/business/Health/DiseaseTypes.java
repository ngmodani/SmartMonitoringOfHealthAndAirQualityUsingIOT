/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Health;

/**
 *
 * @author ngmodani
 */
public final class DiseaseTypes {

    private static DiseaseTypes diseaseTypes;
    private final static String type1 = "Lung Cancer";
    private final static String type2 = "Asthma";
    private final static String type3 = "Chronic obstructive pulmonary disorder";
    private final static String type4 = "Heart related disease";

    
    public static DiseaseTypes getInstance() {
        if (diseaseTypes == null) {
            diseaseTypes = new DiseaseTypes();
        }
        return diseaseTypes;
    }
    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public String getType3() {
        return type3;
    }

    public String getType4() {
        return type4;
    }
    
}
