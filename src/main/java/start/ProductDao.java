package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public static List<SalesOrder> view() {
        String sqlQuery = "SELECT T1.[DocNum] AS 'Sales Order' ,\r\n" +
                "    T1.[NumAtCard] AS 'Customer Ref' ,\r\n" +
                "    T0.[U_SSI_ModPart] AS 'Model' ,\r\n" +
                "       T0.[Dscription] AS 'Description',\r\n" +
                "	   T1.[U_MT_MFG_COMP_TGT] as 'MGF TGT Date',\r\n" +
                "       T3.[GroupCode] AS 'Code' ,\r\n" +
                "       cast(T0.[Quantity] AS INTEGER) AS 'Quantity'\r\n" +
                "FROM [dbo].[RDR1] T0\r\n" +
                "INNER JOIN [dbo].[ORDR] T1 ON T0.[DocEntry] = T1.[DocEntry]\r\n" +
                "INNER JOIN OCRD T3 ON T1.[CardCode] = T3.[CardCode]\r\n" +
                "where T1.U_MT_MFG_COMP_TGT between DATEADD(day,-30,GETDATE()) and DATEADD(day,30,GETDATE())";

        List<SalesOrder> list = new ArrayList<SalesOrder>();
        try {
            Connection con = DriverManager.getConnection(Settings.DBCONNECTION);
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SalesOrder s = new SalesOrder();
                s.setSalesOrder(rs.getInt("Sales Order"));
                s.setModel(rs.getString("Model"));
                s.setDescription(rs.getString("Description"));
                s.setCode(rs.getInt("Code"));
                s.setQuantity(rs.getInt("Quantity"));
                s.setDate(rs.getString("MGF TGT Date"));
                s.setCustRef(rs.getString("Customer Ref"));

                list.add(s);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}

