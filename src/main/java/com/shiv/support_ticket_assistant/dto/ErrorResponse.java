package com.shiv.support_ticket_assistant.dto;

import java.util.Map;

public record ErrorResponse(int status, String message, Map<String, String> fieldErrors) {
}
