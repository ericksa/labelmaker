package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesOrderDao {

    public static List<SalesOrder> view() {
        String sqlQuery = "SELECT   T1.[DocNum]             AS 'Sales Order'\n" +
                "                  , T0.[U_SSI_ModPart] AS 'Model' , T0.[Dscription] AS 'Description'\n" +
                "                 , T3.[GroupCode] AS 'Code'  ,  cast(T0.[Quantity] as INTEGER) as 'Quantity'\n" +
                "                FROM   [dbo].[RDR1] T0   INNER JOIN       [dbo].[ORDR] T1\n" +
                "                        ON           T0.[DocEntry] = T1.[DocEntry]   INNER JOIN\n" +
                "                        OWOR T2       ON           T0.[PoTrgNum] = T2.[DocNum]\n" +
                "                \tinner join \t\tOCRD T3 \t\tON\t\n" +
                "                \t\t\tT1.[CardCode] = T3.[CardCode]  WHERE     (\n" +
                "                 \t\tT0.Dscription    LIKE '%HANNAH%' \n" +
                "                        OR T0.Dscription LIKE 'ARC%'\n" +
                "                        OR T0.Dscription LIKE 'Bella%'\n" +
                "                        OR T0.Dscription LIKE 'BOL%'\n" +
                "                        OR T0.Dscription LIKE 'Carlo%'\n" +
                "                        OR T0.Dscription LIKE 'GLIDE-BOLA%'\n" +
                "                        OR T0.Dscription LIKE 'HAN%'\n" +
                "                        OR T0.Dscription LIKE 'PUSH POP%'\n" +
                "                        OR T0.Dscription LIKE 'Romak%'\n" +
                "                        OR T0.Dscription LIKE 'SML%'\n" +
                "                        OR T0.Dscription LIKE 'Toad%'\n" +
                "                        OR T0.Dscription LIKE 'XBOL%'\n" +
                "                \t\tOR T0.Dscription LIKE 'Slide%')and    (\n" +
                "                        T1.DocDueDate between DATEADD(day,-30,GETDATE()) and DATEADD(day,30,GETDATE())\n" +
                "                    ) ORDER BY  T1.[DocStatus] , T1.[U_MT_MFG_COMP_TGT]\n" +
                "                  , T1.[DocNum] , T0.[VisOrder];";
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

                list.add(s);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
