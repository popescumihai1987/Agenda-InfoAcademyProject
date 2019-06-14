/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageAgenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mihai2.Popescu
 */
public class Contact {

    private final String nume;
    private String prenume;
    private Date dataNasterii;
    private String tel;
    private PhoneType type;

    String pattern = "dd/MM/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);

    public Contact(String nume, String prenume, String dataNasterii, String tel) throws Exception {
        if (nume.length() > 2 && prenume.length() > 2) {
            this.nume = nume;
            this.prenume = prenume;
            if (tel.length() == 10 && !tel.contains("[a-zA-Z]+")) {
                if (tel.startsWith("07")) {
                    this.tel = tel;
                    this.type = PhoneType.MOBIL;
                } else if (tel.startsWith("03") || tel.startsWith("02")) {
                    this.tel = tel;
                    this.type = PhoneType.FIX;
                } else {
                    throw new UnsupportedOperationException("Numarul de telefon trebuie sa inceapa cu 07 pentru mobil sau 02/03 pentru fix...");
                }
                if ("".equals(dataNasterii)) {
                    this.dataNasterii = format.parse("01/01/1970");
                } else {
                    try {
                        this.dataNasterii = format.parse(dataNasterii);
                    } catch (ParseException e) {
                        throw new UnsupportedOperationException("Formatul datei de nastere trebuie sa fie dd/MM/YYYY (31/12/2000)");
                    }
                }
            } else {
                throw new UnsupportedOperationException("Numarul de telefon trebuie sa aiba 10 caractere numerice...");
            }
        } else {
            throw new UnsupportedOperationException("Numele si prenumele trebuie sa fie de minim doua caractere...");
        }
    }

    public Contact(String line) {
        String[] element = line.split(",");
        this.nume = element[0];
        this.prenume = element[1];
        this.tel = element[2];
        try {
            this.dataNasterii = format.parse(element[3]);
        } catch (ParseException e) {
            throw new UnsupportedOperationException("Formatul datei de nastere trebuie sa fie dd/MM/YYYY (31/12/2000)");
        }
        this.type = PhoneType.valueOf(element[4]);
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getTel() {
        return tel;
    }

    public String getDataNasterii() {
        return format.format(this.dataNasterii);
    }

    public Date getDataNasteriiDate() {
        return this.dataNasterii;
    }

    public PhoneType getPhoneType() {
        return this.type;
    }

    @Override
    public String toString() {
        if (format.format(this.dataNasterii).isEmpty()) {
            return this.nume + "," + this.prenume + "," + this.tel + ",01/01/1970," + this.type;

        } else {
            return this.nume + "," + this.prenume + "," + this.tel + "," + format.format(this.dataNasterii) + "," + this.type;
        }
    }

}
