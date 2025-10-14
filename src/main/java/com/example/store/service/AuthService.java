package com.example.store.service;

import com.example.store.model.Utilisateur;
import com.example.store.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UtilisateurRepository userRepository;

    public Utilisateur login(String login, String password) {
        Utilisateur user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // ⚠️ Si tu veux vérifier un mot de passe stocké en clair :
        // if (!user.getPassword().equals(password)) {
        //     throw new RuntimeException("Mot de passe incorrect");
        // }

        return user;
    }

    public Utilisateur register(String login, String password) {
        if (userRepository.existsByLogin(login)) {
            throw new RuntimeException("Login déjà utilisé");
        }

        Utilisateur user = Utilisateur.builder()
                .login(login)
                .nom(login)
                .role("admin")
                .actif(true)
                // .password(password) // si tu veux garder un mot de passe
                .build();

        return userRepository.save(user);
    }
}

