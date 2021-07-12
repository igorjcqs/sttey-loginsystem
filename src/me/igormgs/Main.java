package me.igormgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public static ArrayList<Player> logando = new ArrayList<Player>();
	
	public static BlinkEffect scoreffect = new BlinkEffect();
	
	public static ReentrantLock lock = new ReentrantLock(true);
	public static String SQL_HOST = null;
	public static String SQL_PORT = null;
	public static String SQL_USER = null;
	public static String SQL_PASS = null;
	public static String SQL_DATA = null;
	public static Boolean SQL_DSC = Boolean.valueOf(false);
	private static Connection con;
	public MySQLManager mysql = new MySQLManager(this);
	public boolean database = false;
	  
	  public void onLoad() {
		  plugin = this;
		  saveDefaultConfig();
		  try {
			mysql.setupSQL();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
  
	  public static String getMeses() {
		Calendar cal = Calendar.getInstance();
		  if(cal.get(Calendar.MONTH) == 1) {
			  return "JANEIRO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 2) {
			  return "FEVEREIRO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 3) {
			  return "MARÇO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 4) {
			  return "ABRIL";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 5) {
			  return "MAIO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 6) {
			  return "JUNHO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 7) {
			  return "JULHO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 8) {
			  return "AGOSTO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 9) {
			  return "SETEMBRO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 10) {
			  return "OUTUBRO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 11) {
			  return "NOVEMBRO";
		  }
		  
		  if(cal.get(Calendar.MONTH) == 12) {
			  return "DEZEMBRO";
		  }
		return "NENHUM";
	  }
	  
	  public static int getDia() {
		  Calendar cal = Calendar.getInstance();
		  return cal.get(Calendar.DATE);
	  }
	  
	  
	  public static int getAno() {
		  Calendar cal = Calendar.getInstance();
		  return cal.get(Calendar.YEAR);
	  }
	  
	  public static String getDiaDaSemana() {
		  Calendar cal = Calendar.getInstance();
		  if(cal.get(Calendar.DAY_OF_WEEK) == 1) {
			  return "DOMINGO";
		  }
		  
		  if(cal.get(Calendar.DAY_OF_WEEK) == 2) {
			  return "SEGUNDA-FEIRA";
		  }
		  
		  if(cal.get(Calendar.DAY_OF_WEEK) == 3) {
			  return "TERÇA-FEIRA";
		  }
		  
		  if(cal.get(Calendar.DAY_OF_WEEK) == 4) {
			  return "QUARTA-FEIRA";
		  }
		  
		  if(cal.get(Calendar.DAY_OF_WEEK) == 5) {
			  return "QUINTA-FEIRA";
		  }
		  
		  if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
			  return "SEXTA-FEIRA";
		  }
		  
		  if(cal.get(Calendar.DAY_OF_WEEK) == 7) {
			  return "SÁBADO";
		  }
		  return "NENHUM";
	  }
	  
	  public static int getHora() {
		  Calendar cal = Calendar.getInstance();
		  return cal.get(Calendar.HOUR_OF_DAY);
	  }
	  
	  public static String getMinutos() {
		  Calendar cal = Calendar.getInstance();
		  if(cal.get(Calendar.MINUTE) == 1) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 2) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 3) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 4) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 5) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 6) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 7) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 8) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  
		  if(cal.get(Calendar.MINUTE) == 9) {
			  return "0" + cal.get(Calendar.MINUTE);
		  }
		  return "" + cal.get(Calendar.MINUTE);
	  }
	  
	  
  public static synchronized void SQLconnect(){
	    try{
	      plugin.getLogger().info("Conectando ao MySQL...");
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      String conn = "jdbc:mysql://" + SQL_HOST + ":" + SQL_PORT + "/" + 
	        SQL_DATA;
	      con = DriverManager.getConnection(conn, SQL_USER, SQL_PASS);
	    }
	    catch (ClassNotFoundException ex){
	      plugin.getLogger().warning("MySQL Driver nao encontrado!");
	    }
	    catch (SQLException ex){
	      plugin.getLogger().warning("Erro ao tentar conectar ao Mysql!");
	    }
	    catch (Exception ex){
	      plugin.getLogger().warning("Erro desconhecido enquanto tentava conectar ao MySQL.");
	    }
	  }
	  
	  public static void SQLdisconnect(){
	    EndDB end = new EndDB(plugin, Main.plugin.getLogger(), con);
	    ExecutorService executor = Executors.newCachedThreadPool();
	    executor.execute(end);
	    executor.shutdown();
	  }
	
	public void onEnable() {
		
		System.out.printf("[STTEY] - Login System ATIVADO!");
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		carregarComandos();
		carregarEventos();
		
	}
	
	public void onDisable() {
		
		System.out.printf("[STTEY] - Login System DESATIVADO/REINICNADO!");
		
	    if (this.database) {
	        mysql.closeDB();
	    }
		
	}
	
	
	private void carregarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Eventos(), this);
	}
	
	private void carregarComandos() {
		getCommand("registrar").setExecutor(new Comandos());
		getCommand("register").setExecutor(new Comandos());
		getCommand("login").setExecutor(new Comandos());
		getCommand("logar").setExecutor(new Comandos());
	}

}