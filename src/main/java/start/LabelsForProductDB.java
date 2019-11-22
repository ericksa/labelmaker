package start;


import java.sql.*;

class LabelsForProductDB {
    StringBuilder SQL = new StringBuilder();
    private ProductLabelModel read = new ProductLabelModel();
    private String total = null;

    void printLabels(String salesOrder, String model, String quantity) {
        total = quantity;
        // Create a variable for the connection string.

        try (Connection con = DriverManager.getConnection(Settings.DBCONNECTION); Statement stmt = con.createStatement()) {
            SQL.append("SELECT T1.[DocNum] AS 'Sales Order' ," +
                    "				    T1.[NumAtCard] AS 'Customer' ," +
                    "				    T0.[U_SSI_ModPart] AS 'Model' ," +
                    "				       T0.[Dscription] AS 'Description'," +
                    "					   T1.[U_MT_MFG_COMP_TGT] as 'Date'," +
                    "				       T3.[GroupCode] AS 'Code' " +
                    "				FROM [dbo].[RDR1] T0" +
                    "				INNER JOIN [dbo].[ORDR] T1 ON T0.[DocEntry] = T1.[DocEntry]" +
                    "				INNER JOIN OCRD T3 ON T1.[CardCode] = T3.[CardCode]" +
                    "      WHERE   T1.[DocNum] = " + salesOrder + " AND " + "T0.[U_SSI_ModPart] = '" + model + "'");
            String SQL1 = SQL.toString();
            ResultSet rs = stmt.executeQuery(SQL1);
            getLabelInformation(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getLabelInformation(ResultSet rs) throws SQLException {

        while (rs.next()) {

            String salesorder = rs.getString("Sales Order");
            String customer = rs.getString("Customer");
            String model = rs.getString("Model");
            String description = rs.getString("Description");
            Date date = rs.getDate("Date");
            String contract = rs.getString("Code");
            //    System.out.println(rs.getString("Code"));

            read.ReadDB(customer, salesorder, model, description, total, date, contract);
            break;

        }

    }

}