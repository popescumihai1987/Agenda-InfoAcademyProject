Avem o aplicatie Agenda de telefon.

Fiecare contact contine un Nume, Prenume, Numar de telefon, Data nasterii si Tipul numarului de telefon.
Numele si Prenumele au minim doua caractere alfanumerice.
Numarul de telefon are fix 10 caractere numerice si va incepe cu 07, 02 sau 03.
Tipul numarului de telefon este setat automat in functie de numarul de telefon introdus.
Data nasterii are un format obligatoriu (zz/LL/AAAA) sau poate fi lasata goala, caz in care se populeaza automat cu 01/01/1970.

Contactele pot fi sortate dupa Nume, Prenume sau dupa numarul de telefon.
Pot fi filtrate, in acelasi timp, dupa un string introdus de la tastatura (Personalizata).
Filtrarea se face si dupa: numere de FIX sau MOBIL, contacte cu data de nastere astazi sau in curand.

La pornire aplicatia te notifica daca exista contacte in lista a caror zi de nastere este astazi.
Inregistrarea aplicatiei se face din meniul Help -> Register cu codul 1987.
Inregistrarea se face cu ajutorul unui fisier "flag" registered.txt creat in momentul introducerii codului corect.
Aplicatia va ramane inregistrata pana la stergerea "de mana" a acestui fisier.

Aplicatia foloseste in spate un fisier db.txt, creat, daca este cazul, la pornirea aplicatiei.
Acest fisier este sub in format csv, cu campurile separate prin virgula.
In acest fisier va scrie aplicatia in cazurile de Adaugare si Editare de contacte si din acest fisier va Citi si va Sterge.

Se pot exporta si importa contactele prezente in db.txt in orice alt fisier .txt cu acelasi format din File -> Exporta, Importa.
 