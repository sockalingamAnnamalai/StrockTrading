package com.beans;

import com.beans.DBConn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/addstock")
public class addstock extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public addstock() {
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
            Statement statement = conn.createStatement();
            String symbol = request.getParameter("symbol");
            String price = request.getParameter("price");
           
            statement.executeUpdate("insert into watchlist (`uid`,`symbol`,`price`)	VALUES "
                    + "(1111,'" + symbol + "','" + price + "')");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
