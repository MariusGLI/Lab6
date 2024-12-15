package Problema1;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        // Configurăm ObjectMapper pentru a lucra cu LocalDate
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Citirea fișierului JSON
        List<Angajat> angajati = citireAngajati("src/main/resources/angajati.json", objectMapper);

        // 1. Afișarea listei de angajați
        System.out.println("Lista de angajati:");
        angajati.forEach(System.out::println);

        // 2. Afișarea angajaților cu salariul mai mare de 2500 RON
        System.out.println("\nAngajați cu salariul peste 2500 RON:");
        Predicate<Angajat> salariuPeste2500 = a -> a.getSalariul() > 2500;
        angajati.stream().filter(salariuPeste2500).forEach(System.out::println);

        // 3. Crearea listei de angajați din aprilie anul trecut cu funcție de conducere
        int currentYear = LocalDate.now().getYear();
        int lastYear = currentYear - 1;
        System.out.println("\nAngajați din aprilie " + lastYear + " cu funcție de conducere:");
        angajati.stream()
                .filter(a -> a.getPostul().toLowerCase().contains("sef") || a.getPostul().toLowerCase().contains("director"))
                .filter(a -> a.getData_angajarii().getYear() == lastYear && a.getData_angajarii().getMonthValue() == 4)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // 4. Angajați care nu sunt conducători, ordonat descrescător după salariu
        System.out.println("\nAngajați care nu sunt conducători, ordonat descrescător după salariu:");
        angajati.stream()
                .filter(a -> !(a.getPostul().toLowerCase().contains("sef") || a.getPostul().toLowerCase().contains("director")))
                .sorted(Comparator.comparing(Angajat::getSalariul).reversed())
                .forEach(System.out::println);

        // 5. Extragerea unei liste de nume de angajați cu majuscule
        System.out.println("\nNumele angajaților cu majuscule:");
        List<String> numeMajuscule = angajati.stream()
                .map(a -> a.getNume().toUpperCase())
                .collect(Collectors.toList());
        numeMajuscule.forEach(System.out::println);

        // 6. Salarii mai mici de 3000 RON
        System.out.println("\nSalarii mai mici de 3000 RON:");
        angajati.stream()
                .filter(a -> a.getSalariul() < 3000)
                .map(Angajat::getSalariul)
                .forEach(System.out::println);

        // 7. Determinarea primului angajat
        System.out.println("\nPrimul angajat (cel cu data angajării cea mai veche):");
        angajati.stream()
                .min(Comparator.comparing(Angajat::getData_angajarii))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Nu există angajați"));

        // 8. Statistici despre salarii
        System.out.println("\nStatistici despre salarii:");
        DoubleSummaryStatistics stats = angajati.stream()
                .collect(Collectors.summarizingDouble(Angajat::getSalariul));
        System.out.println("Salariul mediu: " + stats.getAverage());
        System.out.println("Salariul minim: " + stats.getMin());
        System.out.println("Salariul maxim: " + stats.getMax());

        // 9. Verificarea existenței unui angajat cu numele Ion
        System.out.println("\nExistă un Ion printre angajați?");
        angajati.stream()
                .map(Angajat::getNume)
                .filter(nume -> nume.equalsIgnoreCase("Ion"))
                .findAny()
                .ifPresentOrElse(
                        ion -> System.out.println("Firma are cel puțin un Ion angajat"),
                        () -> System.out.println("Firma nu are nici un Ion angajat")
                );

        // 10. Numărul de angajați care s-au angajat vara anului trecut
        System.out.println("\nNumărul de angajați angajați vara anului trecut:");
        long countVaraAnulTrecut = angajati.stream()
                .filter(a -> a.getData_angajarii().getYear() == lastYear)
                .filter(a -> a.getData_angajarii().getMonthValue() >= 6 && a.getData_angajarii().getMonthValue() <= 8)
                .count();
        System.out.println(countVaraAnulTrecut);
    }

    public static List<Angajat> citireAngajati(String filePath, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Angajat>>() {});
    }
}
