 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;
/**
 *
 * @author saiketan
 */
public class DataOperations {
  Connection con;
  int rowcount;
  
  public DataOperations()
    {
    con=ConnectionObj.getConnection();
    }
  public boolean insertUser(User user)
  {
      try{
          Statement stmt=con.createStatement();
          String insertStr="insert into tblRegister values('"+user.Name+"','"+user.Uname+"','"+user.Pwd+"','"+user.PhoneNo+"')";
          System.out.print(insertStr);
          stmt.execute(insertStr);
          return true;
      }
      catch(Exception ex)
      {
          return false;
      }
  }
  public String getPwd(String Uname)
  {
      try{
          Statement stmt=con.createStatement();
          String cmdStr="select pwd from tblRegister where uname='"+Uname+"'";
          System.out.print(cmdStr);
          ResultSet rs=stmt.executeQuery(cmdStr);
          
          if(rs.next())
              return rs.getString(1);
          return null;
      }
      catch(Exception ex)
      {
          return null;
      }
  }
 
  
   public int getRowCount()
    {
               try
        {
                Statement stmt=con.createStatement();
                ResultSet   rs=stmt.executeQuery("select count(*) from tblBat");
                rs.next();
                int cnt=rs.getInt(1);
                return(cnt);
            }
        catch(Exception ex)
        {
            return(-1);
        }
    }
    public ResultSet getData()
    {
        try
        {
                Statement stmt=con.createStatement();
                ResultSet   rs=stmt.executeQuery("select *from tblbat");
                return(rs);
            }
        catch(Exception ex)
        {
            return(null);
        }
    }
  
      public String getAttributeRowsCount()
    {
           try
           {
               Statement stmt=con.createStatement();
               ResultSet rs=stmt.executeQuery("select count(*) from tblbat");

               rs.next();
               String rows=rs.getInt(1)+"";
               rowcount=Integer.parseInt(rows);

               rows="";
               rs=stmt.executeQuery("Select * from tblbat");
               ResultSetMetaData rsmd=rs.getMetaData();
               //rows+=","+rsmd.getColumnCount();

               
               for(int i=1;i<=rsmd.getColumnCount();i++)
               {
                   if(i==rsmd.getColumnCount())
                        rows+=rsmd.getColumnName(i);
               else
               rows+=rsmd.getColumnName(i) + ",";
               }
               return(rows);
           }

           catch(Exception ex)
           {
             return("Not Found");
           }
    }
     public boolean convertDBText()
     {
         try
         {
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select * from tblcbat");
             ResultSetMetaData rsmd=rs.getMetaData();
             
             StringBuilder sb=new StringBuilder();
             
             for(int i=1;i<=rsmd.getColumnCount();i++)
             {
                 if(i==rsmd.getColumnCount())
                     sb.append(rsmd.getColumnName(i) + "\r\n");
                 else
                 sb.append(rsmd.getColumnName(i) + ",");
             }
             
             
             while(rs.next())
             {
                   for(int i=1;i<=rsmd.getColumnCount();i++)
                   {
                       if(i==rsmd.getColumnCount())
                            sb.append(rs.getString(i) + "\r\n");
                      else
                           sb.append(rs.getString(i) + ",");
                   }
             }
             rs.close();
             stmt.close();
             
             FileOutputStream fos=new FileOutputStream("input//datainput.txt");
             PrintWriter pw=new PrintWriter(fos);
             pw.println(sb.toString().trim());
             pw.close();
             fos.close();
             return(true);
         }
         catch(Exception ex)
         {
             return(false);
         }
     }
     
     
    public boolean createDyanamicTable(String Header)
    {
        try
        {
            StringTokenizer st=new StringTokenizer(Header,",");
            String CreateQuery="create table tblbat(";
            int max=st.countTokens();
            int cntr=1;
            while(st.hasMoreElements())
                {
                     
                CreateQuery+=st.nextElement().toString()+" varchar2(32),";
                
                }
            CreateQuery=CreateQuery.substring(0,CreateQuery.length()-1)+")";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from tab where tname like 'TBLBAT'");
            rs.next();
            int cnt=rs.getInt(1);

            if(cnt>0)
            {
            stmt=con.createStatement();
            stmt.execute("drop table TBLBAT");
            }
            System.out.println(CreateQuery);
            stmt=con.createStatement();
            stmt.execute(CreateQuery);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
    public boolean createCDyanamicTable(String Header)
    {
        try
        {
            StringTokenizer st=new StringTokenizer(Header,",");
            Statement stmt=con.createStatement();
            String CreateQuery="create table tblCbat(";
                int max=st.countTokens();
            int cntr=1;
            while(st.hasMoreElements())
                {
                      
                CreateQuery+=st.nextElement().toString()+" varchar2(32),";
                cntr++;
                    
                }
            CreateQuery=CreateQuery.substring(0,CreateQuery.length()-1)+")";
            
            ResultSet rs=stmt.executeQuery("select count(*) from tab where tname like 'TBLCBAT'");
            rs.next();
            int cnt=rs.getInt(1);

            if(cnt>0)
            {
            stmt=con.createStatement();
            stmt.execute("drop table TBLCBAT");
            }
            System.out.println(CreateQuery);
            stmt=con.createStatement();
            stmt.execute(CreateQuery);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    public boolean insertCData(String rows)
    {
        try
        {
        StringTokenizer st=new StringTokenizer(rows,"\r\n");
        Statement stmt=con.createStatement();
        while(st.hasMoreElements())
            {
            String row=st.nextElement().toString();
            String elements[]=row.split(",");
            String insertstmt="insert into tblCbat values(";
            for(int i=0;i<elements.length;i++)
                insertstmt+="'"+elements[i]+"',";
            insertstmt=insertstmt.substring(0,insertstmt.length()-1)+")";
            
            stmt.execute(insertstmt);
            }
        return true;
        }
        catch(Exception ex)
        {
            return false;
    }
        
    }
    public boolean insertData(String rows)
    {
        try
        {
        StringTokenizer st=new StringTokenizer(rows,"\r\n");
        Statement stmt=con.createStatement();
        while(st.hasMoreElements())
            {
            String row=st.nextElement().toString();
            String elements[]=row.split(",");
            String insertstmt="insert into tblbat values(";
            for(int i=0;i<elements.length;i++)
                insertstmt+="'"+elements[i]+"',";
            insertstmt=insertstmt.substring(0,insertstmt.length()-1)+")";
            
            stmt.execute(insertstmt);
            }
        return true;
        }
        catch(Exception ex)
        {
            return false;
    }
    }
    
    public boolean createOutputFile(String filename,String colnames)
    {
         try
         {
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select " + colnames + "  from tblcbat");
             ResultSetMetaData rsmd=rs.getMetaData();
             
             StringBuilder sb=new StringBuilder();
             
             for(int i=1;i<=rsmd.getColumnCount();i++)
             {
                 if(i==rsmd.getColumnCount())
                     sb.append(rsmd.getColumnName(i) + "\r\n");
                 else
                 sb.append(rsmd.getColumnName(i) + ",");
             }
             
             
             while(rs.next())
             {
                   for(int i=1;i<=rsmd.getColumnCount();i++)
                   {
                       if(i==rsmd.getColumnCount())
                            sb.append(rs.getString(i) + "\r\n");
                      else
                           sb.append(rs.getString(i) + ",");
                   }
             }
             rs.close();
             stmt.close();
             
             FileOutputStream fos=new FileOutputStream("input//" + filename + ".txt");
             PrintWriter pw=new PrintWriter(fos);
             pw.println(sb.toString().trim());
             pw.close();
             fos.close();
             return(true);
             
         }
         catch(Exception ex)
         {
             System.out.println("error " + ex.getMessage());
             return(false);
         }
    }
    public void dropTables()
    {
          try
          {
                Statement stmt=con.createStatement();
                stmt.execute("drop table tblbat");
                
                
                stmt=con.createStatement();
                stmt.execute("drop table tblcbat");
                
                stmt.close();
                
          }
        catch(Exception ex)
        {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
