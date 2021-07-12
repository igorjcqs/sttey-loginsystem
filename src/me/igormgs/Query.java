package me.igormgs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import me.igormgs.Main;

public class Query extends Thread{
	
	  private String sql;
	  private Logger log;
	  private Connection con;
	  
	  public Query(String sql, Logger log, Connection con, Plugin plugin){
	    setDaemon(false);
	    
	    this.sql = sql;
	    setLog(log);
	    this.con = con;
	  }
	  
	  public void run(){
	    Main.lock.lock();
	    try
	    {
	      Statement stmt = this.con.createStatement();
	      stmt.executeUpdate(this.sql);
	      stmt.close();
	    }
	    catch (SQLException ex){
	      Main.plugin.getLogger().warning("Error with following query: " + 
	        this.sql);
	      
	      Main.plugin.getLogger().warning(ex.getMessage());
	      Main.SQLdisconnect();
	    }
	    catch (NullPointerException ex){
	      Main.plugin.getLogger().warning("Error while performing a query. (NullPointerException)");
	    }
	    Main.lock.unlock();
	  }
	  
	  public Logger getLog(){
	    return this.log;
	  }
	  
	  public void setLog(Logger log){
	    this.log = log;
	  }
}