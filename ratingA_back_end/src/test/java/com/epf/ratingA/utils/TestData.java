package com.epf.ratingA.utils;

import com.epf.ratingA.enumer.Category;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.models.User;
import com.epf.ratingA.enumer.Role;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class TestData {
    public static User createSampleUser(Long id, String firstName, String lastName, Role role, String email, Instant birthdate, String sexe, String password) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setSexe(sexe);
        user.setPassword(password);
        // Ajoute d'autres propriétés de l'utilisateur si nécessaire
        return user;
    }

    public static User createSampleUser1() {
        return createSampleUser(1L, "John", "Doe", Role.USER, "john.doe@example.com", Instant.parse("1990-01-01T00:00:00Z"), "Male", "password123");
    }

    public static User createSampleUser2() {
        return createSampleUser(2L, "Jane", "Smith", Role.USER, "jane.smith@example.com", Instant.parse("1985-05-15T12:30:00Z"), "Female", "password456");
    }

    public static List<Category> createSampleCategories() {
        // Crée et retourne une liste de catégories fictives
        return Arrays.asList(Category.ACTION, Category.DRAMA, Category.COMEDY, Category.ADVENTURE,Category.CRIME,Category.MYSTERY,Category.SCIENCE_FICTION);
    }



    public static Rate createSampleRate(Long id, String name, float note, String summary, String detailSummary) {
        // Crée et retourne un rate fictif
        Rate rate = new Rate();
        rate.setIdRate(id);
        rate.setName(name);
        rate.setNote(note);
        rate.setSummary(summary);
        rate.setDetailSummary(detailSummary);
        // Ajoute d'autres propriétés de rate si nécessaire
        return rate;
    }


    public static List<Rate> createSampleRates() {
        // Crée et retourne une liste de rates fictifs
        return Arrays.asList(
                createSampleRate(1L, "Rate 1", 4.5f, "Summary 1", "Detail Summary 1"),
                createSampleRate(2L, "Rate 2", 3.8f, "Summary 2", "Detail Summary 2"),
                createSampleRate(3L, "Rate 3", 5.0f, "Summary 3", "Detail Summary 3")
        );
    }


    // Ajoute d'autres méthodes de création d'utilisateurs si nécessaire
}
