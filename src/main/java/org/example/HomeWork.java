package org.example;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Scanner;

public class HomeWork {
    private final static int MAX_STARTING_SEQUENCE_LENGTH = 100_000;
    private final static int MAX_NUMBER_OF_QUERIES = 100_000;

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу UPIT из файла contest7_tasks.pdf
     */
    @SneakyThrows
    public void upit(InputStream in, OutputStream out) {
        Objects.requireNonNull(in, "Parameter 'in' can't be null");
        Objects.requireNonNull(out, "Parameter 'out' can't be null");

        try (Scanner scanner = new Scanner(in)) {
            Scanner firstLineScanner = new Scanner(scanner.nextLine());

            int sequenceLength = firstLineScanner.nextInt();
            checkStartingSequenceLength(sequenceLength);

            int numberOfQueries = firstLineScanner.nextInt();
            checkNumberOfQueries(numberOfQueries);

            firstLineScanner.close();

            UpItQueryHandler queryHandler = new UpItQueryHandler();
            queryHandler.initStartingSequence(scanner.nextLine());

            for (int i = 0; i < numberOfQueries; i++) {
                try (Scanner queryLine = new Scanner(scanner.nextLine())) {
                    int queryType = queryLine.nextInt();

                    switch (queryType) {
                        case 1:
                            queryHandler.set(queryLine.nextInt(), queryLine.nextInt(), queryLine.nextInt());
                            break;
                        case 2:
                            queryHandler.add(queryLine.nextInt(), queryLine.nextInt(), queryLine.nextInt());
                            break;
                        case 3:
                            queryHandler.insert(queryLine.nextInt(), queryLine.nextInt());
                            break;
                        case 4:
                            long sum = queryHandler.find(queryLine.nextInt(), queryLine.nextInt());
                            out.write(String.valueOf(sum).getBytes());
                            out.write(System.lineSeparator().getBytes());
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown query type - '" + queryType + "'");
                    }
                }
            }
            out.flush();
        }
    }

    private void checkNumberOfQueries(int numberOfQueries) {
        if (numberOfQueries < 1) {
            throw new IllegalArgumentException("Number of queries have to be greater than 0");
        }
        if (numberOfQueries > MAX_NUMBER_OF_QUERIES) {
            throw new IllegalArgumentException("Number of queries have to be less or equal than " + MAX_NUMBER_OF_QUERIES);
        }
    }

    private void checkStartingSequenceLength(int sequenceLength) {
        if (sequenceLength < 1) {
            throw new IllegalArgumentException("Starting sequence have to be greater than 0");
        }
        if (sequenceLength > MAX_STARTING_SEQUENCE_LENGTH) {
            throw new IllegalArgumentException("Starting sequence length have to be less or equal than " + MAX_STARTING_SEQUENCE_LENGTH);
        }
    }
}