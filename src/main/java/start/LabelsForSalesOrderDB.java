package start;


import java.sql.*;

public class LabelsForSalesOrderDB {

    String salesorder;
    String model;
    private String description;
    private String contractCode;
    private String quantity;
    StringBuilder SQL = new StringBuilder();
    ShippingFileReaderModel read = new ShippingFileReaderModel();
    // String connectionUrl = "jdbc:sqlserver://SSI-SQLDB:1433;databaseName=mediatechnologies2016;user=sa;password=ssIsq1";

    public void printLabels(String salesOrder) {

        // Create a variable for the connection string.

        try (Connection con = DriverManager.getConnection(Settings.DBCONNECTION); Statement stmt = con.createStatement()) {
            SQL.append("SELECT\r\n" + "         T1.[DocNum]                    AS 'Sales Order'\r\n"
                    + "       , T0.[U_SSI_ModPart]             AS 'Model'\r\n"
                    + "       , T0.[Dscription]                AS 'Description'\r\n"
                    + "       , T3.[GroupCode]                 AS 'Code'\r\n"
                    + "       , cast(T0.[Quantity] as INTEGER) as 'test'\r\n" + "     FROM\r\n"
                    + "         [dbo].[RDR1] T0\r\n" + "         INNER JOIN\r\n" + "             [dbo].[ORDR] T1\r\n"
                    + "             ON\r\n" + "                 T0.[DocEntry] = T1.[DocEntry]\r\n"
                    + "         INNER JOIN\r\n" + "             OWOR T2\r\n" + "             ON\r\n"
                    + "                 T0.[PoTrgNum] = T2.[DocNum]\r\n" + "         inner join\r\n"
                    + "             OCRD T3\r\n" + "             ON\r\n"
                    + "                 T1.[CardCode] = T3.[CardCode]\r\n" + "     WHERE\r\n"
                    + "         T1.[DocNum] = " + salesOrder);
            String SQL1 = SQL.toString();

            ResultSet rs = stmt.executeQuery(SQL1);
            // GetSalesOrderView get = new GetSalesOrderView();
            // get.printLabelInfo(rs);
            getLabelInformation(rs);
            // Iterate through the data in the result set and display it.

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printLabel(String salesOrder, String model) {

        try (Connection con = DriverManager.getConnection(Settings.DBCONNECTION); Statement stmt = con.createStatement()) {
            SQL.append("SELECT\r\n" + "         T1.[DocNum]                    AS 'Sales Order'\r\n"
                    + "       , T0.[U_SSI_ModPart]             AS 'Model'\r\n"
                    + "       , T0.[Dscription]                AS 'Description'\r\n"
                    + "       , T3.[GroupCode]                 AS 'Code'\r\n"
                    + "       , cast(T0.[Quantity] as INTEGER) as 'test'\r\n" + "     FROM\r\n"
                    + "         [dbo].[RDR1] T0\r\n" + "         INNER JOIN\r\n" + "             [dbo].[ORDR] T1\r\n"
                    + "             ON\r\n" + "                 T0.[DocEntry] = T1.[DocEntry]\r\n"
                    + "         INNER JOIN\r\n" + "             OWOR T2\r\n" + "             ON\r\n"
                    + "                 T0.[PoTrgNum] = T2.[DocNum]\r\n" + "         inner join\r\n"
                    + "             OCRD T3\r\n" + "             ON\r\n"
                    + "                 T1.[CardCode] = T3.[CardCode]\r\n" + "     WHERE\r\n"
                    + "         T1.[DocNum] = " + salesOrder + "AND T0.[U_SSI_ModPart] ='" + model + "'");
            String SQL1 = SQL.toString();

            ResultSet rs = stmt.executeQuery(SQL1);
            // GetSalesOrderView get = new GetSalesOrderView();
            // get.printLabelInfo(rs);
            getLabelInformation(rs);
            // Iterate through the data in the result set and display it.

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getLabelInformation(ResultSet rs) throws SQLException {

        while (rs.next()) {
            System.out.println("1 \t" + rs.getString("Sales Order") + "\t" + rs.getString("Model") + "\t"
                    + rs.getString("Description") + "\t" + rs.getString("Code") + "\t" + rs.getString("test"));
            salesorder = rs.getString("Sales Order");
            model = rs.getString("Model");
            description = rs.getString("Description");
            contractCode = rs.getString("Code");
            quantity = rs.getString("test");

            read.ReadDB(salesorder, model, description, contractCode, quantity);

        }
    }

    public void printOneLabel(String salesOrder, String model) {
        try (Connection con = DriverManager.getConnection(Settings.DBCONNECTION); Statement stmt = con.createStatement()) {
            SQL.append("SELECT \r\n" + "         T1.[DocNum]                    AS 'Sales Order'\r\n"
                    + "       , T0.[U_SSI_ModPart]             AS 'Model'\r\n"
                    + "       , T0.[Dscription]                AS 'Description'\r\n"
                    + "       , T3.[GroupCode]                 AS 'Code'\r\n" + "       ,  CASE \r\n"
                    + "    WHEN T0.[Quantity] = 0 then ''\r\n" + "    WHEN T0.[Quantity] = 1 then '1'\r\n"
                    + "    ELSE '1'\r\n" + "END as 'test'\r\n" + "     FROM\r\n" + "         [dbo].[RDR1] T0\r\n"
                    + "         INNER JOIN\r\n" + "             [dbo].[ORDR] T1\r\n" + "             ON\r\n"
                    + "                 T0.[DocEntry] = T1.[DocEntry]\r\n" + "         INNER JOIN\r\n"
                    + "             OWOR T2\r\n" + "             ON\r\n"
                    + "                 T0.[PoTrgNum] = T2.[DocNum]\r\n" + "         inner join\r\n"
                    + "             OCRD T3\r\n" + "             ON\r\n"
                    + "                 T1.[CardCode] = T3.[CardCode]\r\n" + "     WHERE\r\n"
                    + "         T1.[DocNum] = " + salesOrder + "AND T0.[U_SSI_ModPart] ='" + model + "'");
            String SQL1 = SQL.toString();

            ResultSet rs = stmt.executeQuery(SQL1);
            // GetSalesOrderView get = new GetSalesOrderView();
            // get.printLabelInfo(rs);
            getLabelInformation(rs);
            // Iterate through the data in the result set and display it.

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}