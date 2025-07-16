package br.com.raphaelmb.job_vacancy_manager_frontend.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatErrorMessage {
    public static String formatErrorMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(message);

            if (rootNode.isArray()) {
                return formatArrayErrorMessage(rootNode);
            }

            return rootNode.asText();
        } catch (Exception e) {
            return message;
        }
    }

    private static String formatArrayErrorMessage(JsonNode arrayNode) {
        StringBuilder sb = new StringBuilder();

        for (JsonNode node : arrayNode) {
            sb.append(" - ").append(node.get("message").asText()).append("\n");
        }

        return sb.toString();
    }
}
