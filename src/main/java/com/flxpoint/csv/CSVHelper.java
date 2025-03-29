package com.flxpoint.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CSVHelper {
	private static final Logger logger = Logger.getLogger(CSVHelper.class.getName());

	public static void main(String[] args) {
		String inputFile = "src/main/resources/input.csv";
		String outputFile = "src/main/resources/output.csv";

		List<String> lines = readCsvFile(inputFile);
		logger.info("CSV Data: " + lines);
		List<String> uniqueLines = removeDuplicates(lines);

		writeCsvFile(outputFile, uniqueLines);
		logger.info("Unique data is saved to new CSV at path: " + outputFile);
	}

	/**
	 * Reads a CSV file and returns its content as a list of strings. Each line is
	 * trimmed to remove leading and trailing spaces. Empty lines are filtered out.
	 * 
	 * @param filePath Path to the CSV file.
	 * @return List of non-empty, trimmed lines from the file.
	 */
	private static List<String> readCsvFile(String filePath) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath)).stream().map(String::trim)
					.filter(line -> !line.isEmpty() && !line.matches("^[,\\s]*$")) // Ignore empty & comma-only lines
					.collect(Collectors.toList());

			return lines;
		} catch (IOException e) {
			logger.warning("Error while reading file: " + e.getMessage());
			return Collections.emptyList(); // Return an empty list in case of an error
		}
	}

	/**
	 * Removes duplicate lines from the CSV while preserving the header row.
	 * 
	 * @param lines List of CSV lines.
	 * @return A new list with duplicates removed, preserving the first occurrence.
	 */
	private static List<String> removeDuplicates(List<String> lines) {
		if (lines.isEmpty())
			return Collections.emptyList();

		// Store the header separately
		String header = lines.get(0);

		// Use a LinkedHashSet to remove duplicates while maintaining insertion order
		Set<String> uniqueRows = new LinkedHashSet<>(lines.subList(1, lines.size()));

		// Construct the final list with the header followed by unique rows
		List<String> result = new ArrayList<>();
		result.add(header);
		result.addAll(uniqueRows);

		return result;
	}

	/**
	 * Writes a list of strings to a CSV file.
	 * 
	 * @param filePath Path where the file should be written.
	 * @param lines    List of lines to write to the file.
	 */
	private static void writeCsvFile(String filePath, List<String> lines) {
		try {
			Files.write(Paths.get(filePath), lines);
		} catch (IOException e) {
			logger.warning("Error while writing file: " + e.getMessage());
		}
	}
}