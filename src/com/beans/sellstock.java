package com.beans;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import com.beans.DBConn;
import static com.beans.StockApiBean.API_KEY;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class sellstock {

    private static final long serialVersionUID = 1L;
    static final String API_KEY = "FJV5D5SCSTOCYG9L";

    private String symbol;
    private double price;
    private int qty;
    private double amt;
    private String table1Markup;
    private String table2Markup;

    private String selectedSymbol;
    private List<SelectItem> availableSymbols;

    private List<SelectItem> purchasedSymbols;
    public String getPurchaseSymbol() {
        if (getRequestParameter("symbol") != null) {
            symbol = getRequestParameter("symbol");
        }
        return symbol;
    }
    
    public void setPurchaseSymbol(String purchaseSymbol) {
        System.out.println("func setPurchaseSymbol()");  //check
    }

    public double getPurchasePrice() {
        if (getRequestParameter("price") != null) {
            price = Double.parseDouble(getRequestParameter("price"));
            System.out.println("price: " + price);
        }
        return price;
    }

    public void setPurchasePrice(double purchasePrice) {
        System.out.println("func setPurchasePrice()");  //check
    }
    
    private String getRequestParameter(String name) {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);
    }

    @PostConstruct
    public void init() {
       List<String> list = new ArrayList<String>();
         try{
            Connection conn = DBConn.getConnection();
            Statement statement = conn.createStatement();
    int uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
    
       ResultSet rs=statement.executeQuery("SELECT * FROM `purchase` WHERE uid='"+uid+"';");
    while(rs.next()){
            list.add(rs.getString("stock_symbol"));
    }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       if(list.isEmpty())
           list.add("No purchased stocks");
       
        //initially populate stock list
        availableSymbols = new ArrayList<SelectItem>();
           for(String item: list){
            availableSymbols.add(new SelectItem(item, item));
           }

//initially populate intervals for stock api
        availableIntervals = new ArrayList<SelectItem>();
        availableIntervals.add(new SelectItem("1min", "1min"));
        availableIntervals.add(new SelectItem("5min", "5min"));
        availableIntervals.add(new SelectItem("15min", "15min"));
        availableIntervals.add(new SelectItem("30min", "30min"));
        availableIntervals.add(new SelectItem("60min", "60min"));
    
        purchasedSymbols = new ArrayList<SelectItem>();
        
    }

    private String selectedInterval;
    private List<SelectItem> availableIntervals;

    public String getSelectedInterval() {
        return selectedInterval;
    }

    public void setSelectedInterval(String selectedInterval) {
        this.selectedInterval = selectedInterval;
    }

    public List<SelectItem> getAvailableIntervals() {
        return availableIntervals;
    }

    public void setAvailableIntervals(List<SelectItem> availableIntervals) {
        this.availableIntervals = availableIntervals;
    }

    public String getSelectedSymbol() {
        return selectedSymbol;
    }

    public void setSelectedSymbol(String selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    public List<SelectItem> getAvailableSymbols() {
        return availableSymbols;
    }
    

    public void setAvailableSymbols(List<SelectItem> availableSymbols) {
        this.availableSymbols = availableSymbols;
    }
    public List<SelectItem> getPurchasedSymbols() {
        return purchasedSymbols;
    }
public void setPurchasedSymbols(List<SelectItem> purchasedSymbols ) {
        this.purchasedSymbols = purchasedSymbols;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getTable1Markup() {
        return table1Markup;
    }

    public void setTable1Markup(String table1Markup) {
        this.table1Markup = table1Markup;
    }

    public String getTable2Markup() {
        return table2Markup;
    }

    public void setTable2Markup(String table2Markup) {
        this.table2Markup = table2Markup;
    }

    public String createDbRecord(String symbol, double price, int qty, double amt) {
        double userStockQuantity=0, totalQuantity=0;
        //get the quantity
         try {
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            Connection conn = DBConn.getConnection();
            Statement statement = conn.createStatement();
            
            //get userid
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            ResultSet rs = statement.executeQuery("select qty from purchase where uid ="+uid+" and stock_symbol='"+symbol+"';");
            if(rs.next()){
                userStockQuantity = rs.getInt("qty");
            }
            statement.close();
            conn.close();
            System.out.println("Quantity of the stock is "+userStockQuantity);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
       totalQuantity=userStockQuantity-qty;
       if(totalQuantity>0){
        //selling table
        try {
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            Connection conn = DBConn.getConnection();
            Statement statement = conn.createStatement();
            
            //get userid
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            statement.executeUpdate("INSERT INTO `sell` (`stock_symbol`, `price`, `amt`, `qty`, `soldby`) "
                    + "VALUES ('" + symbol + "'," + price + ","+ amt + ","+qty+","+uid+");");
           
            statement.close();
            conn.close();
            System.out.println("Selling done ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double availibleBalance = 0, totalBalance=0;
        //avaible balance
        try {
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            Connection conn = DBConn.getConnection();
            Statement statement = conn.createStatement();
            
            //get userid
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            ResultSet rs = statement.executeQuery("select balance from user where uid ="+uid+";");
            if(rs.next())
                availibleBalance = rs.getDouble("balance");
           
            statement.close();
            conn.close();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        totalBalance = availibleBalance-amt;
        //update values
         try {
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            Connection conn = DBConn.getConnection();
            Statement statement = conn.createStatement();
            
            //get userid
            Integer uid = Integer.parseInt(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid").toString());
            
            statement.executeUpdate("update user u1, purchase p1 set u1.balance="+totalBalance+",p1.qty="+totalQuantity+" WHERE u1.uid=p1.uid="+uid+" and p1.stock_symbol='"+symbol+"';");
            System.out.println("values are updated");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       }
       else{
          FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Not enough stock quantity!",""));

       }
        return "sellstock";
    }

    public void installAllTrustingManager() {
        TrustManager[] trustAllCerts;
        trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return;
    }

    public void timeseries() throws MalformedURLException, IOException {

        installAllTrustingManager();

        //System.out.println("selectedItem: " + this.selectedSymbol);
        //System.out.println("selectedInterval: " + this.selectedInterval);
        String symbol = this.selectedSymbol;
        String interval = this.selectedInterval;
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

        this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
        InputStream inputStream = new URL(url).openStream();

        // convert the json string back to object
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject mainJsonObj = jsonReader.readObject();
        for (String key : mainJsonObj.keySet()) {
            if (key.equals("Meta Data")) {
                this.table1Markup = null; // reset table 1 markup before repopulating
                JsonObject jsob = (JsonObject) mainJsonObj.get(key);
                this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                this.table1Markup += "<table>";
                this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
                this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                this.table1Markup += "</table>";
            } else {
                this.table2Markup = null; // reset table 2 markup before repopulating
                JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                this.table2Markup += "<table class='table table-hover'>";
                this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                this.table2Markup += "<tbody>";
                int i = 0;
                for (String subKey : dataJsonObj.keySet()) {
                    JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                    this.table2Markup
                            += "<tr>"
                            + "<td>" + subKey + "</td>"
                            + "<td>" + subJsonObj.getString("1. open") + "</td>"
                            + "<td>" + subJsonObj.getString("2. high") + "</td>"
                            + "<td>" + subJsonObj.getString("3. low") + "</td>"
                            + "<td>" + subJsonObj.getString("4. close") + "</td>"
                            + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                    if (i == 0) {
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                        this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/faces/sell.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Sell Stock</a></td>";
                    }
                    this.table2Markup += "</tr>";
                    i++;
                }
                this.table2Markup += "</tbody></table>";
            }
        }
        return;
    }

    public void purchaseStock() {
        System.out.println("Calling functiona purchaseStock()");
        System.out.println("stockSymbol: " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockSymbol"));
        System.out.println("stockPrice" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockPrice"));
        return;
    }
    
}
