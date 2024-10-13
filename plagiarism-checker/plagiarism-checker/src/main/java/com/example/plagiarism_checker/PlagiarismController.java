package com.example.plagiarismchecker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlagiarismController {

    @PostMapping("/check-plagiarism")
    public ResponseEntity<PlagiarismResponse> checkPlagiarism(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) MultipartFile file) {
        double plagiarismPercentage = 0.0;

        try {
            // Here you can implement your plagiarism detection logic
            if (file != null && !file.isEmpty()) {
                // Example: Extract text from PDF or DOCX file
                String fileText = extractTextFromFile(file);
                plagiarismPercentage = calculatePlagiarism(fileText);
            } else if (text != null && !text.isEmpty()) {
                plagiarismPercentage = calculatePlagiarism(text);
            }

            return ResponseEntity.ok(new PlagiarismResponse(plagiarismPercentage));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String extractTextFromFile(MultipartFile file) throws IOException {
        // Implement text extraction from PDF or DOCX file
        // For simplicity, returning empty string here
        return "";
    }

    private double calculatePlagiarism(String inputText) {
        // Implement your plagiarism detection logic here
        // For simplicity, returning 0.0% here
        return 0.0;
    }

    public static class PlagiarismResponse {
        private final double percentage;

        public PlagiarismResponse(double percentage) {
            this.percentage = percentage;
        }

        public double getPercentage() {
            return percentage;
        }
    }
}
