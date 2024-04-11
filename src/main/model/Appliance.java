package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

///represents an Appliance having energyUsage, a name, and if its on or off
public class Appliance implements Writable {

    private int energyUsage;   //amount of energy used in specific appliance
    private String name; // name of appliance
    private boolean onOff;     // if appliance is on or off;

     /*
     * REQUIRES: applianceName has a non-zero length
     * EFFECTS: the appliance is automatically turned on.
      */

    public Appliance(int energyUsage, String name) {
        this.energyUsage = energyUsage;
        this.name = name.toLowerCase();
        onOff = true;

    }


    //EFFECTS: returns the name of appliance
    public String getApplianceName() {
        return this.name;
    }


    //EFFECTS: returns the amount of energy used by appliance
    public int getEnergyUsage() {
        if (onOff) {
            return this.energyUsage;
        } else {
            return 0;
        }

    }

    // MODIFIES: this
    //EFFECTS: switches appliance from on to off
    public void turnOnOff() {
        onOff = !onOff;
        EventLog.getInstance().logEvent(new Event("Turned" + getApplianceName() + "On/Off"));

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("energyUsage", energyUsage);
        return json;
    }
}
