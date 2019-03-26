package com.beans;

import com.beans.DBConn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/purchase")
public class Purchase extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Purchase() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = DBConn.getConnection();
            int uid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid").toString());

            Statement statement = conn.createStatement();
            String symbol = request.getParameter("symbol");
            String price = request.getParameter("price");
            String qty = request.getParameter("qty");
            String amt = request.getParameter("amt");
            ResultSet rs = statement.executeQuery("SELECT * FROM purchase WHERE uid="+uid+" and stock_symbol='"+symbol+"';");
            if(rs.next()){
                statement.executeUpdate("UPDATE purchase SET qty="+(rs.getInt("qty")+qty)+", amt = "+(rs.getDouble("amt")+Double.parseDouble(amt))+" WHERE uid ="+uid+" and stock_symbol='"+symbol+"';");
                System.out.println("Updated stocks");
            }
            else{
            statement.executeUpdate("insert into purchase (`uid`,`stock_symbol`,`qty`,`price`,`amt`)	VALUES ("
                    +uid+",'" + symbol + "'," + qty + "," + price + "," + (amt) + ");");
            System.out.println("Inserted new stocks");
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
