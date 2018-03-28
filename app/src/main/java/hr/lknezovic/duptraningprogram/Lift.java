package hr.lknezovic.duptraningprogram;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by knez on 03/25/18.
 */

public class Lift {

    private String id;
    private String name;
    private Double weight;


    public Lift(String id, String name, Double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }


    public Lift() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
