package ru.xs.springjavacheckbracketsber.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.xs.springjavacheckbracketsber.model.InputText;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Slf4j
@Service
public class BracketServiceImpl implements BracketService {

    @Override
    public boolean checkStackBracketMapCycle(InputText inputText) {
        log.info("Service => Checking bracket inputText => " + inputText.text());
        final Stack<Integer> openBracketIndex = new Stack<>();
        final Map<Integer, Integer> bracketPairs = new HashMap<>();
        final String text = inputText.text();

        for (int currentIndex = 0; currentIndex < text.length(); currentIndex++) {
            final char currentChar = text.charAt(currentIndex);

            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                openBracketIndex.push(currentIndex);

                if (currentIndex + 1 != text.length()) {
                    final char nextChar = text.charAt(currentIndex + 1);

                    if (currentChar == '(' && nextChar == ')'
                            || currentChar == '[' && nextChar == ']'
                            || currentChar == '{' && nextChar == '}') {
                        return false; // Empty bracket found
                    }
                }

            } else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {

                if (openBracketIndex.isEmpty()) {
                    return false; // Unbalanced closing bracket
                }
                bracketPairs.put(openBracketIndex.pop(), currentIndex);
            }
        }

        if (!openBracketIndex.isEmpty()) {
            return false; // Unbalanced opening bracket
        }

        if (bracketPairs.isEmpty()) {
            return true; // no bracket pairs found
        }

        for (Map.Entry<Integer, Integer> entry : bracketPairs.entrySet()) {
            final int openingIndex = entry.getKey();
            final int closingIndex = entry.getValue();

            final String withinBracket = text.substring(openingIndex + 1, closingIndex);

            if (withinBracket.isBlank()) {
                return false; // Empty bracket found
            }
        }
        return true;
    }
}