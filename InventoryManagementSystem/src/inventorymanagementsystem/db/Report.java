package inventorymanagementsystem.db;
import java.util.Date;

public class Report {
    private int reportId;
    private Date reportDate;
    private String reportType;
    private String description;
    private double totalAmount;

    // Private constructor to ensure objects are created through the builder only
    private Report(ReportBuilder builder) {
        this.reportId = builder.reportId;
        this.reportDate = builder.reportDate;
        this.reportType = builder.reportType;
        this.description = builder.description;
        this.totalAmount = builder.totalAmount;
    }

    // Getters for the Report class
    public int getReportId() {
        return reportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public String getReportType() {
        return reportType;
    }

    public String getDescription() {
        return description;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Static nested Builder class
    public static class ReportBuilder {
        private int reportId;
        private Date reportDate;
        private String reportType;
        private String description;
        private double totalAmount;

        // Setters for each field (returning the builder itself for chaining)
        public ReportBuilder setReportId(int reportId) {
            this.reportId = reportId;
            return this;
        }

        public ReportBuilder setReportDate(Date reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public ReportBuilder setReportType(String reportType) {
            this.reportType = reportType;
            return this;
        }

        public ReportBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ReportBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        // Build the Report object
        public Report build() {
            return new Report(this);
        }
    }

    // Method to generate a report summary
    public String generateReportSummary() {
        return "Report ID: " + reportId + "\n" +
               "Date: " + reportDate + "\n" +
               "Type: " + reportType + "\n" +
               "Description: " + description + "\n" +
               "Total Amount: " + totalAmount;
    }
}



