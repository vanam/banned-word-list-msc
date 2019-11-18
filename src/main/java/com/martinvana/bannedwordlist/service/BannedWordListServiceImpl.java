package com.martinvana.bannedwordlist.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * Banned word list business logic implementation.
 */
@Service
public class BannedWordListServiceImpl implements BannedWordListService {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BannedWordListServiceImpl.class);

    /**
     * List of all banned words.
     */
    private static final List<String> WORD_LIST = new ArrayList<>();

    static {
        String resourceFileName = "swearWords.txt";

        try {
            WORD_LIST.addAll(Objects.requireNonNull(getResourceFileAsStringLines(resourceFileName)));
        } catch (IOException e) {
            LOGGER.error("Could not load '{}'", resourceFileName);
        }
    }

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    private static List<String> getResourceFileAsStringLines(String fileName) throws IOException {
        ClassLoader classLoader = BannedWordListServiceImpl.class.getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) {
                return null;
            }
            try (
                 InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(isr)
            ) {
                return reader.lines().collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<String> getBannedWordList(final int count) {
        // Not an efficient way of generating random sublist of swear words.
        List<String> tmpList = new ArrayList<>(WORD_LIST);
        Collections.shuffle(tmpList);
        // Purposefully there is a possible IndexOutOfBounds bug.
        return tmpList.subList(0, Math.min(count, WORD_LIST.size()));
    }
}
