package com.martinvana.bannedwordlist.service;

import java.util.List;

/**
 * Banned word list business logic.
 */
public interface BannedWordListService {

    /**
     * @param count Number of banned words.
     * @return List of {@code count} random banned words.
     */
    List<String> getBannedWordList(int count);
}
