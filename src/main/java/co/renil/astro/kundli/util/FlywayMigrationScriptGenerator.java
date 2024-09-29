package co.renil.astro.kundli.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FlywayMigrationScriptGenerator {

    private static final String MIGRATION_FOLDER = "src/main/resources/db/migration/";
    private static final String VERSION_PREFIX = "V";
    private static final String UNDO_PREFIX = "U";

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Get migration name from user
//        System.out.print("Enter migration name: ");
//        String migrationName = scanner.nextLine().replaceAll(" ", "_").toLowerCase();
//
//        // Generate timestamp
//        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
//
//        // Create versioned migration file
//        String versionFileName = VERSION_PREFIX + timestamp + "__" + migrationName + ".sql";
//        createMigrationFile(versionFileName);
//
//        // Create undo migration file
//        String undoFileName = UNDO_PREFIX + timestamp + "__" + migrationName + ".sql";
//        createMigrationFile(undoFileName);
//    }

    private static void createMigrationFile(String fileName) {
        File file = new File(MIGRATION_FOLDER + fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Created migration file: " + file.getAbsolutePath());
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("-- Migration script: " + fileName + "\n");
                    writer.write("-- Write your SQL statements here\n");
                }
            } else {
                System.out.println("Migration file already exists: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error creating migration file: " + e.getMessage());
        }
    }
}

