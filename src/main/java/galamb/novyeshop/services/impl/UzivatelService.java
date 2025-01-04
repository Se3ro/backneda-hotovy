package galamb.novyeshop.services.impl;

import galamb.novyeshop.dto.UzivatelDTO;
import galamb.novyeshop.entity.Uzivatel;
import galamb.novyeshop.repositoryl.UzivatelRepozitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UzivatelService {

    @Autowired
    private UzivatelRepozitar uzivatelRepozitar;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UzivatelDTO registrace(UzivatelDTO uzivatelDTO) {
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setJmeno(uzivatelDTO.getJmeno());
        uzivatel.setPrijmeni(uzivatelDTO.getPrijmeni());
        uzivatel.setEmail(uzivatelDTO.getEmail());
        uzivatel.setHeslo(passwordEncoder.encode(uzivatelDTO.getHeslo()));

        uzivatel = uzivatelRepozitar.save(uzivatel);
        uzivatelDTO.setId(uzivatel.getId());
        return uzivatelDTO;
    }

    public UzivatelDTO prihlaseni(String email, String heslo) {
        Uzivatel uzivatel = uzivatelRepozitar.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Uživatel s tímto e-mailem neexistuje"));
        if (!passwordEncoder.matches(heslo, uzivatel.getHeslo())) {
            throw new RuntimeException("Špatné heslo");
        }
        UzivatelDTO uzivatelDTO = new UzivatelDTO();
        uzivatelDTO.setId(uzivatel.getId());
        uzivatelDTO.setEmail(uzivatel.getEmail());
        uzivatelDTO.setJmeno(uzivatel.getJmeno());
        uzivatelDTO.setPrijmeni(uzivatel.getPrijmeni());
        return uzivatelDTO;
    }
    public Optional<Uzivatel> najitUzivateleDleEmailu(String email) {
        return uzivatelRepozitar.findByEmail(email);
    }
    public UzivatelDTO registraceNeboPrihlaseniPresGoogle(String email, String firstName, String lastName) {
        Optional<Uzivatel> existujiciUzivatel = najitUzivateleDleEmailu(email);

        if (existujiciUzivatel.isPresent()) {
            // Pokud uživatel existuje, vrátí jeho data
            Uzivatel uzivatel = existujiciUzivatel.get();
            UzivatelDTO uzivatelDTO = new UzivatelDTO();
            uzivatelDTO.setId(uzivatel.getId());
            uzivatelDTO.setEmail(uzivatel.getEmail());
            uzivatelDTO.setJmeno(uzivatel.getJmeno());
            uzivatelDTO.setPrijmeni(uzivatel.getPrijmeni());
            return uzivatelDTO;
        } else {
            // Jinak vytvoří nového uživatele
            UzivatelDTO novyUzivatel = new UzivatelDTO();
            novyUzivatel.setEmail(email);
            novyUzivatel.setJmeno(firstName);
            novyUzivatel.setPrijmeni(lastName);
            return registrace(novyUzivatel);
        }
    }


}
