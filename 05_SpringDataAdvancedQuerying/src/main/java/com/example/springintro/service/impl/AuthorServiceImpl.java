package com.example.springintro.service.impl;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNameWithTotalCopies;
import com.example.springintro.model.entity.Book;
import com.example.springintro.repository.AuthorRepository;
import com.example.springintro.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);

                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1,
                        authorRepository.count() + 1);

        return authorRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<String> findAllFirstNameEndWith(String endWith) {
        return authorRepository.findByFirstNameEndsWith(endWith)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllLastNameStartWithAllBookTitle(String startWith) {
        return authorRepository.findByLastNameStartsWith(startWith)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(a -> String.format("%s %s%n%s%n",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBooks().stream()
                                .map(b -> " - " + b.getTitle()).collect(Collectors.joining(System.lineSeparator()))))
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorNameWithTotalCopies> findAllAuthorsWithTotalCopies() {

        return authorRepository.authorsByBookCopies().orElseThrow(NoSuchElementException::new);
    }
}