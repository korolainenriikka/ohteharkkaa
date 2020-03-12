/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bob.domain;

import java.util.Date;

/**
 *
 * @author riikoro
 */
public class Muistutus {
    private Date date;
    private String description;

    public Muistutus(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
