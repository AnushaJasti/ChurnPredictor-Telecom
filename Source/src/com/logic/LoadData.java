 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author RAMU
 */
public class LoadData
{
  String filename;
  public static String colnames;
  public static String stage;
  public static String typeds;
  public static String filtercolnames;
  
  
  public LoadData()
    {
     filename="";
    }
  public LoadData(String filename)
    {
      colnames="";
      filtercolnames="";
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
               //typeds=line.substring(1);
              // line=br.readLine();
               
               colnames=line;
               System.out.println("Col names " + colnames);
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
               System.out.println(colnames);
               return("Unable to Read");
    }
  }
}
