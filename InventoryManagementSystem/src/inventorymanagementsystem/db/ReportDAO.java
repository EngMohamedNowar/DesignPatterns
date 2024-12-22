package inventorymanagementsystem.db;

import inventorymanagementsystem.DatabaseConnection;
import inventorymanagementsystem.db.Report;
import java.sql.*;

public class ReportDAO {
    private static ReportDAO instance;
    private Connection connection;

    // Private constructor to ensure singleton pattern
    private ReportDAO() {
        connection = DatabaseConnection.getConnection();  // Singleton connection
    }

    // Public method to get the singleton instance of ReportDAO
    public static ReportDAO getInstance() {
        if (instance == null) {
            synchronized (ReportDAO.class) {
                if (instance == null) {
                    instance = new ReportDAO();
                }
            }
        }
        return instance;
    }

    // Add a report
    public boolean addReport(Report report) {
        String query = "INSERT INTO Reports (report_date, report_type, description, total_amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(report.getReportDate().getTime()));
            stmt.setString(2, report.getReportType());
            stmt.setString(3, report.getDescription());
            stmt.setDouble(4, report.getTotalAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all reports
    public ResultSet getAllReports() {
        String query = "SELECT * FROM Reports";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get a report by its ID
    public ResultSet getReportById(int reportId) {
        String query = "SELECT * FROM Reports WHERE report_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update a report
    public boolean updateReport(Report report) {
        String query = "UPDATE Reports SET report_date = ?, report_type = ?, description = ?, total_amount = ? WHERE report_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(report.getReportDate().getTime()));
            stmt.setString(2, report.getReportType());
            stmt.setString(3, report.getDescription());
            stmt.setDouble(4, report.getTotalAmount());
            stmt.setInt(5, report.getReportId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a report
    public boolean deleteReport(int reportId) {
        String query = "DELETE FROM Reports WHERE report_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

