 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.auth;

import java.io.*;
/**
 *
 * @author RAMU
 */
public class LoadData
{
  String filename;
  public static String colnames;
  public static String stage;

  public static String yescount,nocount,result;
  public LoadData()
    {
     filename="";
    }
  public LoadData(String filename)
    {
      colnames="";
      this.filename=filename;
  }
  public static String getHeader()
    {
      return(colnames) ;
    }
  public String readData()
    {
           try
           {
               FileInputStream fis=new FileInputStream(filename);
               BufferedReader br=new BufferedReader(new InputStreamReader(fis));
               String line=br.readLine();
               colnames=line;
               String op="";
               do
               {
                   line=br.readLine();
                   if(line==null)
                        break;
                   op+=line +"\r\n";
               }while(line!=null);
               fis.close();
               return(op);
        }
           catch(Exception ex)
           {
               return("Unable to Read");
    }
  }
}
