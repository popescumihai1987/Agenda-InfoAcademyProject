/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageAgenda;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mihai2.Popescu
 */
public class ContactFile {

    private File file;
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);

    public ContactFile(String filename) {
        this.file = new File(filename);
        try {
            this.file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ContactFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ContactFile(File file) {
        this.file = file;
        try {
            this.file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ContactFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adauga(Contact contact) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(contact.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void editeaza(Contact c1, Contact c2) throws IOException {
        Path dbFile = file.toPath();
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(dbFile), charset);
        content = content.replaceAll(c1.toString(), c2.toString());
        Files.write(dbFile, content.getBytes(charset));
    }

    public void sterge(Contact c1) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            List<Contact> delete = stream.map((row) -> new Contact(row)).filter(p -> !p.toString().equals(c1.toString())).collect(Collectors.toList());

            try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
                delete.stream().map(c -> c.toString()).forEach(c -> writer.write(c + System.getProperty("line.separator")));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Contact> importFromFile() {
        ContactComparator dupaNume = new ContactComparator("dupa Nume");
        System.out.println(file.getAbsolutePath());
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Contact> getAllContacts() {
        ContactComparator dupaNume = new ContactComparator("dupa Nume");
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).sorted(dupaNume).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Contact> getMobileContacts(String sortBy) {
        ContactComparator comparator = new ContactComparator(sortBy);
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).filter(p -> p.getPhoneType().equals(PhoneType.MOBIL)).sorted(comparator).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Contact> getSomeContacts(String string, String sortBy) {
        ContactComparator comparator = new ContactComparator(sortBy);
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).filter(p -> p.toString().contains(string)).sorted(comparator).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Contact> getFixContacts(String sortBy) {
        ContactComparator comparator = new ContactComparator(sortBy);
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).filter(p -> p.getPhoneType().equals(PhoneType.FIX)).sorted(comparator).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Contact> getTodayAniversaryContacts(String sortBy) {
        ContactComparator comparator = new ContactComparator(sortBy);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localDate.format(formatter);
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).filter(p -> p.getDataNasterii().substring(0, 5).equals(formattedString.substring(0, 5))).sorted(comparator).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Contact> getSoonToBeAniversaryContacts(String sortBy) {
        ContactComparator comparator = new ContactComparator(sortBy);
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map((row) -> new Contact(row)).filter(p -> dateIsWithinRange(p.getDataNasterii())).sorted(comparator).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    boolean dateIsWithinRange(String testDate) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localDate.format(formatter);
        System.out.println(testDate.substring(3, 5));
        System.out.println(formattedString.substring(3, 5));
        System.out.println(testDate.substring(0, 3));
        System.out.println(formattedString.substring(3, 5));
        return (testDate.substring(3, 5).equals(formattedString.substring(3, 5)) &&
                Integer.parseInt(testDate.substring(0, 2)) > Integer.parseInt(formattedString.substring(0, 2)));
    }

    public boolean exists() {
        return this.file.exists();
    }
}
