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
public class MedicalHelpIssues {

    private static MedicalHelpIssues medicalIssues;
    private final String type1 = "Breathing issues";
    private final String type2 = "Chest Pain";
    private final String type3 = "Fatigue or Weakness";
    private final String type4 = "Headache";
    // private final String type4 = "Headache"

    public static MedicalHelpIssues getInstance() {
        if (medicalIssues == null) {
            medicalIssues = new MedicalHelpIssues();
        }
        return medicalIssues;
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
