package com.devops.demo.logmaintain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLogger {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLogger.class);

    // ✅ General Info Logs
    public void logInfo(String message) {
        log.info("[DB-INFO] {}", message);
    }

    // ✅ Warning Logs
    public void logWarn(String message) {
        log.warn("[DB-WARN] {}", message);
    }

    // ✅ Error Logs with Exception Details
    public void logError(String message, Exception e) {
        log.error("[DB-ERROR] {} - Exception: {}", message, e.getMessage(), e);
    }

    // ✅ Database Connection Logs
    public void logDbConnection(String dbUrl, String username) {
        log.info("[DB-CONNECTION] Connecting to DB: {} with User: {}", dbUrl, username);
    }

    public void logDbConnectionSuccess(String dbUrl) {
        log.info("[DB-CONNECTION] ✅ Successfully connected to DB: {}", dbUrl);
    }

    public void logDbConnectionFailure(String dbUrl, Exception e) {
        log.error("[DB-CONNECTION] ❌ Failed to connect to DB: {} - {}", dbUrl, e.getMessage(), e);
    }

    // ✅ Transaction Logs
    public void logTransactionStart(String transactionId) {
        log.info("[DB-TRANSACTION] ▶️ Starting transaction: {}", transactionId);
    }

    public void logTransactionCommit(String transactionId) {
        log.info("[DB-TRANSACTION] ✅ Transaction committed: {}", transactionId);
    }

    public void logTransactionRollback(String transactionId) {
        log.warn("[DB-TRANSACTION] ❌ Transaction rolled back: {}", transactionId);
    }

    // ✅ Query Execution Logs
    public void logQueryExecution(String query) {
        log.info("[DB-QUERY] Executing: {}", query);
    }

    public void logQueryExecutionError(String query, Exception e) {
        log.error("[DB-QUERY] ❌ Error executing query: {} - {}", query, e.getMessage(), e);
    }
}
