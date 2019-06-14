/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageAgenda;

import java.util.Comparator;

/**
 * @author Mihai2.Popescu
 */
public class ContactComparator implements Comparator<Contact> {

    private String sortBy;

    public ContactComparator(String sortBy) {
        this.sortBy = sortBy;
    }


    @Override
    public int compare(Contact o1, Contact o2) {
        if (sortBy.equals("dupa Nume")) {
            return o1.getNume().compareToIgnoreCase(o2.getNume());
        } else if (sortBy.equals("dupa Prenume")) {
            return o1.getPrenume().compareToIgnoreCase(o2.getPrenume());
        } else if (sortBy.equals("dupa Telefon")) {
            return o1.getTel().compareToIgnoreCase(o2.getTel());
        }
        return o1.getNume().compareToIgnoreCase(o2.getNume());
    }
}
